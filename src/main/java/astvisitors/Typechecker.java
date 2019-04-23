package astvisitors;

import java.util.Arrays;

import ast.*;
import ast.expr.*;
import ast.type.*;
import exceptions.ErrorHandler;

public class Typechecker extends AstVisitor {

    private final String keywords[] = {"PRINT", "RETURN", "DEFAULT", "SWITCH"};

    public void visit(Func_callStmtNode node) {

    }

    public void visit(BlockNode node) {
        if (node.getDclsNode() != null) {
            node.getDclsNode().accept(this);
        }
        if (node.getStmtsNode() != null) {
            node.getStmtsNode().accept(this);
        }
        if (node.getReturnstmtNode() != null) {
            node.getReturnstmtNode().accept(this);
        }

        /* Checks type if returnStmt exists and ifStmt have an type, typechecks and sets type.
         *  If only either returnStmt or ifStmt have an type blocknode is set to that type.  */

        boolean ifStmtsReturnValue = node.getStmtsNode().getType() != null;
        boolean returnStmtReturnValue = node.getReturnstmtNode() != null;

        if (ifStmtsReturnValue && returnStmtReturnValue) {
            checkType(node.getStmtsNode(), node.getReturnstmtNode());
            node.setType(node.getReturnstmtNode().getType());
        } else if (ifStmtsReturnValue) {
            node.setType(node.getStmtsNode().getType());
        } else if (returnStmtReturnValue) {
            node.setType(node.getReturnstmtNode().getType());
        }
    }

    public void visit(Func_defNode node) {
        node.getBlockNode().accept(this);
        for (DclNode parameter : node.getParameters()) {
            parameter.accept(this);
        }

        /* Gets the if statements of the outermost block, and checks if there is an else with an return stmt */
        boolean elseStmtWithReturnExist = false;
        StmtsNode stmtsOfOutermostBlock = node.getBlockNode().getStmtsNode();
        for (int i = 0; i < stmtsOfOutermostBlock.getChildCount(); i++) {
            if (stmtsOfOutermostBlock.getChild(i) instanceof If_stmtNode) {
                If_stmtNode if_stmtNode = (If_stmtNode) stmtsOfOutermostBlock.getChild(i);
                if (if_stmtNode.getElseBlock() != null) {
                    if (if_stmtNode.getElseBlock().getReturnstmtNode() != null) {
                        elseStmtWithReturnExist = true;
                    }
                }
            }
        }

        /* If there are no else stmt with return in the outer most scope of the func def block,
           no other return stmt, and the method is not void, the func def is not
           guaranteed to reach an return stmt and throws an error */
        if (!elseStmtWithReturnExist && node.getBlockNode().getReturnstmtNode() == null) {
            if (node.getType() != Type.VOID) {
                ErrorHandler.returnNotGuaranteed();
            }
        }
        checkType(node, node.getBlockNode());

        if (isReservedKeyword(node.getId()))
            ErrorHandler.reservedKeyword(node.getId());
    }

    public void visit(Return_stmtNode node) {
        if (node.getReturnExpr() != null) {
            node.getReturnExpr().accept(this);
            node.setType(node.getReturnExpr().getType());
        } else {
            node.setType(Type.VOID);
        }
    }

    public void visit(If_stmtNode node) {
        node.getExpr().accept(this);
        node.getIfBlock().accept(this);
        if (node.getElseBlock() != null) {
            node.getElseBlock().accept(this);
        }
        checkSpecificType(node.getExpr(), Type.BOOL);

        boolean ifAndElseBothReturnValue = false;
        boolean ifReturnValue = false;
        boolean elseReturnValue = false;

        boolean ifStmtExist = node.getIfBlock() != null;
        if (ifStmtExist) {
            ifReturnValue = node.getIfBlock().getReturnstmtNode() != null;
        }

        boolean elseStmtExist = node.getElseBlock() != null;
        if (elseStmtExist) {
            elseReturnValue = node.getElseBlock().getReturnstmtNode() != null;
        }

        if (ifStmtExist && elseStmtExist) {
            ifAndElseBothReturnValue = node.getIfBlock().getReturnstmtNode() != null &&
                    node.getElseBlock().getReturnstmtNode() != null;
        }


        if (ifAndElseBothReturnValue) {
            checkType(node.getIfBlock().getReturnstmtNode(), node.getElseBlock().getReturnstmtNode());
            node.setType(node.getIfBlock().getReturnstmtNode().getType());
        } else if (ifReturnValue) {
            node.setType(node.getIfBlock().getReturnstmtNode().getType());
        } else if (elseReturnValue) {
            node.setType(node.getElseBlock().getReturnstmtNode().getType());
        } else {
            /* If it doesn't have a return, let the type be null */
            node.setType(null);
        }
    }

    public void visit(StartNode node) {
        node.getDcls().accept(this);
        node.getStmts().accept(this);

    }

    public void visit(BooleanLiteral node) {
        node.setType(Type.BOOL);
    }

    public void visit(StmtNode node) {

    }

    /* For if stmts and while stmts check that they have the same type and set block node to that type. */
    public void visit(StmtsNode node) {
        StmtNode firstIfStament = null;
        StmtNode firstWhileStmt = null;
        boolean ifStmtExist = false;
        boolean whileStmtExist = false;
        int ifCount = 0;
        int whileCount = 0;

        for (int i = 0; i < node.getChildCount(); i++) {
            node.getChild(i).accept(this);
            if (node.getChild(i) instanceof If_stmtNode) {
                If_stmtNode if_stmtNode = (If_stmtNode) node.getChild(i);
                /* If it is a stmt without a type, ie. it has no return type, do not use that stmt's
                 *  type for type checking */
                if (if_stmtNode.getType() != null) {
                    ifCount += 1;
                    ifStmtExist = true;
                    if (ifCount == 1) {
                        firstIfStament = node.getChild(i);
                    } else {
                        checkType(firstIfStament, node.getChild(i));
                    }
                }
            }
            if (node.getChild(i) instanceof While_stmtNode) {
                While_stmtNode while_stmtNode = (While_stmtNode) node.getChild(i);
                if (while_stmtNode.getType() != null) {
                    whileCount += 1;
                    if (whileCount == 1) {
                        whileStmtExist = true;
                        firstWhileStmt = node.getChild(i);
                    } else {
                        checkType(firstWhileStmt, node.getChild(i));
                    }
                }
            }
        }

        if (ifStmtExist && whileStmtExist) {
            checkType(firstIfStament, firstWhileStmt);
        } else if (ifStmtExist) {
            node.setType(firstIfStament.getType());
        } else if (whileStmtExist) {
            node.setType(firstWhileStmt.getType());
        }
    }

    public void visit(DclNode node) {
        if (isReservedKeyword(node.getID()))
            ErrorHandler.reservedKeyword(node.getID());
    }

    public void visit(TypeNode node) {

    }

    public void visit(DclsNode node) {
        for (int i = 0; i < node.getChildCount(); i++) {
            node.getChild(i).accept(this);
        }

    }

    public void visit(ValNode node) {
    }

    public void visit(While_stmtNode node) {
        node.getExprNode().accept(this);
        node.getBlockNode().accept(this);
        checkSpecificType(node.getExprNode(), Type.BOOL);
        node.setType(node.getBlockNode().getType());
    }

    public void visit(ExprNode node) {

    }

    public void visit(ParametersNode node) {

    }

    // One added assignment nodes.
    public void visit(Assign_stmtNode node) {
        node.getExprNode().accept(this);
        checkType(node, node.getExprNode());

    }

    @Override
    public void visit(Func_callExprNode node) {

    }

    @Override
    public void visit(AdditiveExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        checkType(node.getLeftNode(), node.getRightNode());
        node.setType(node.getLeftNode().getType());
    }

    @Override
    public void visit(MultiplicativeExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        checkType(node.getLeftNode(), node.getRightNode());
        node.setType(node.getLeftNode().getType());
    }

    @Override
    public void visit(LogicalAndExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        checkSpecificType(node.getLeftNode(), Type.BOOL);
        checkSpecificType(node.getRightNode(), Type.BOOL);
        node.setType(Type.BOOL);
    }

    @Override
    public void visit(RelationalExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        checkType(node.getLeftNode(), node.getRightNode());
        node.setType(Type.BOOL);
    }

    @Override
    public void visit(EqualityExprNode node) {
        node.getLeftNode().accept(this);
        node.getRelationalExprNode().accept(this);
        checkType(node.getLeftNode(), node.getRelationalExprNode());
        node.setType(Type.BOOL);
    }

    private void checkType(AstNode leftNode, AstNode rightNode) {
        Type leftType = leftNode.getType();
        Type rightType = rightNode.getType();
        if (leftType == null) {
            System.err.println("Left type null!");
            return;
        }
        if (rightType == null) {
            System.err.println("Right type null!");
            return;
        }
        if (leftType != rightType) {
            ErrorHandler.typeMismatch(leftNode, rightNode);

        }
    }

    private void checkSpecificType(ITypeNode node, Type expectedType) {
        Type nodeType = node.getType();
        if (nodeType == null) {
            System.err.println("node null in 184 :(");
        }
        if (nodeType != expectedType) {
            ErrorHandler.unexpectedType(node, nodeType);
        }
    }

    @Override
    public void visit(ParenthesisExprNode node) {
        node.getNode().accept(this);
        node.setType(node.getNode().getType());
    }

    @Override
    public void visit(IntegerLiteral node) {
        node.setType(Type.INT);
    }

    @Override
    public void visit(DoubleLiteral node) {
        node.setType(Type.DOUBLE);
    }

    @Override
    public void visit(StringLiteral node) {
        node.setType(Type.STRING);
    }

    @Override
    public void visit(IdNode node) {
        if (node.getVal().toUpperCase().equals("TRUE") ||
                node.getVal().toUpperCase().equals("FALSE"))
            ErrorHandler.invalidTF();
    }

    @Override
    public void visit(AstNode astNode) {
        super.visit(astNode);
    }

    private boolean isReservedKeyword(String word) {
        return (Arrays.binarySearch(keywords, word.toUpperCase()) >= 0);
    }

    @Override
    public void visit(UnaryExprNode node) {
        node.getNode().accept(this);
        node.setType(node.getNode().getType());
    }

    @Override
    public void visit(LogicalOrExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        checkSpecificType(node.getLeftNode(), Type.BOOL);
        checkSpecificType(node.getRightNode(), Type.BOOL);
        node.setType(Type.BOOL);
    }
}
