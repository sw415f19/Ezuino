package astvisitors;

import java.util.Arrays;

import ast.*;
import ast.expr.*;
import ast.type.*;
import exceptions.ErrorHandler;

public class Typechecker extends AstVisitor {

    private boolean elseStmtWithReturnExist = false;

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


        if (!elseStmtWithReturnExist && node.getReturnstmtNode() == null) {
            System.err.println("Return is not guaranteed, since there are no else block with return or an return outside nested scopes");
        }

        /* Checks type if returnStmt exists and ifStmt have an type, typechecks and sets type.
         *  If only either returnStmt or ifStmt have an type blocknode is set to that type.  */

        boolean ifStmtsReturnValue = node.getStmtsNode().getType() != null;
        boolean returnStmtReturnValue = node.getReturnstmtNode() != null;
        boolean ifElseStmtsReturnVoid = node.getStmtsNode().getType() == Type.VOID;

        if (returnStmtReturnValue && ifElseStmtsReturnVoid) {
            node.setType(Type.VOID);
        } else if (ifStmtsReturnValue && returnStmtReturnValue) {
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
        System.out.println(node.getType());
        checkType(node, node.getBlockNode());
        if (isReservedKeyword(node.getId())) ErrorHandler.reservedKeyword(node.getId());
        System.out.println("Checked return of func def!!");


    }

    public void visit(Print_lNode node) {
        node.getExprNode().accept(this);

    }

    public void visit(Return_stmtNode node) {
        if(node.getReturnExpr() != null){
            node.getReturnExpr().accept(this);
            node.setType(node.getReturnExpr().getType());
        }
        else {
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
            node.setType(Type.VOID);
        }
    }

    public void visit(StartNode node) {
        node.getDcls().accept(this);
        node.getStmts().accept(this);

    }

    public void visit(BooleantfNode node) {
        node.setType(Type.BOOL);
    }

    public void visit(StmtNode node) {


    }

    public void visit(StmtsNode node) {
        StmtNode firstIfStament = null;
        boolean ifStmtNodeExists = false;
        elseStmtWithReturnExist = false;
        for (int i = 0; i < node.getChildCount(); i++) {
            node.getChild(i).accept(this);
            if (node.getChild(i) instanceof If_stmtNode) {
                /* Checks whether there exist any else stmts. If there is none, it must chech that there are an return stmt in the main scope (blockNode). */
                If_stmtNode if_stmtNode = (If_stmtNode) node.getChild(i);
                boolean elseStmtExist = if_stmtNode.getElseBlock() != null;
                if(elseStmtExist) {
                    boolean elseStmtReturnValue = if_stmtNode.getElseBlock().getReturnstmtNode() != null;
                    if (elseStmtReturnValue) {
                        elseStmtWithReturnExist = true;
                    }
                }

                ifStmtNodeExists = true;
                if (i == 0) {
                    firstIfStament = node.getChild(i);
                } else {
                    checkType(firstIfStament, node.getChild(i));
                }
            }

        }
        if (ifStmtNodeExists) {
            node.setType(firstIfStament.getType());
        }
    }

    public void visit(DclNode node) {
        if (isReservedKeyword(node.getID())) ErrorHandler.reservedKeyword(node.getID());

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

    }

    public void visit(ExprNode node) {

    }

    public void visit(ParametersNode node) {

    }

    //One added assignment nodes.
    public void visit(Assign_stmtNode node) {
        node.getExprNode().accept(this);
        checkType(node, node.getExprNode());

    }

    @Override
    public void visit(Func_callExprNode node) {

    }

    @Override
    public void visit(Built_in_funcNode node) {

    }

    @Override
    public void visit(AdditiveExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        checkType(node.getLeftNode(), node.getRightNode());
        node.setType(node.getLeftNode().getType());
        System.out.println("Checked AdditiveExprNode type!!");
    }

    @Override
    public void visit(MultiplicativeExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        checkType(node.getLeftNode(), node.getRightNode());
        node.setType(node.getLeftNode().getType());
        System.out.println("Checked LogicalAndExprNode type!!");
    }

    @Override
    public void visit(LogicalAndExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        checkSpecificType(node.getLeftNode(), Type.BOOL);
        checkSpecificType(node.getRightNode(), Type.BOOL);
        node.setType(Type.BOOL);
        System.out.println("Checked LogicalAndExprNode type!!");
    }

    @Override
    public void visit(RelationalExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        checkType(node.getLeftNode(), node.getRightNode());
        node.setType(Type.BOOL);
        System.out.println("Checked RelationalExprNode type!!");
    }

    @Override
    public void visit(EqualityExprNode node) {
        node.getLeftNode().accept(this);
        node.getRelationalExprNode().accept(this);
        checkType(node.getLeftNode(), node.getRelationalExprNode());
        node.setType(Type.BOOL);
        System.out.println("Checked EqualityExprNode type!!");
    }

    private void checkType(AstNode leftNode, AstNode rightNode) {
        Type leftType = leftNode.getType();
        Type rightType = rightNode.getType();
        if (leftType == null) {
            System.err.println("left type null!");
            return;
        }
        if (rightType == null) {
            System.err.println("right type null!");
            return;
        }
        if (leftType != rightType) {
            System.err.println("Type mismatch! Left type: " + leftType.name() + " Right type: " + rightType.name() + "\nLeft node: " + leftNode + " Right node: " + rightNode);
        }
    }

    private void checkSpecificType(ITypeNode node, Type expectedType) {
        Type nodeType = node.getType();
        if (nodeType == null) {
            System.err.println("Null!! :( ");
        }
        if (nodeType != expectedType) {
            System.err.println("Unexpeced type! Expected: " + expectedType.name() + ", was " + nodeType.name() + " - Node: " + node);
        }
    }

    @Override
    public void visit(ParenthesisExprNode node) {
        node.getNode().accept(this);
        node.setType(node.getNode().getType());
        System.out.println("Checked ParenthesisExprNode type!!");
    }

    @Override
    public void visit(IntegerNode node) {
        node.setType(Type.INT);
    }

    @Override
    public void visit(DoubleNode node) {
        node.setType(Type.DOUBLE);
    }

    @Override
    public void visit(StringNode node) {
        node.setType(Type.STRING);
    }

    @Override
    public void visit(IdNode node) {
    }

    @Override
    public void visit(AstNode astNode) {
        super.visit(astNode);
    }

    private boolean isReservedKeyword(String word) {
        return (Arrays.binarySearch(keywords, word.toUpperCase()) >= 0);
    }
}
