package astvisitors;

import ast.*;
import ast.expr.AdditiveExprNode;
import ast.expr.ExprNode;
import ast.expr.MultiplicativeExprNode;
import ast.expr.iexpr.IExpr;
import ast.type.DoubleNode;
import ast.type.IdNode;
import ast.type.IntegerNode;
import ast.type.StringNode;

public class IndentedPrintVisitor extends AstLevelVisitor {
  
  public void print(AstNode node, int level) {
    String resultString = "";
    resultString += level > 0 ? new String(new char[level]).replace("\0", "   ") : "";
    resultString += "+- " + node.toString();
    System.out.println(resultString);
  }
  /*
   * public void visit(StartNode s) { System.out.println("Printing AST:");
   * //print(s, 0); visit(s.getDcls(), 1); visit(s.getStmts(), 1); }
   * 
   * public void visit(DclsNode d, int level) { int childCount =
   * d.getChildCount(); print(d, level); for(int i = 0;i < childCount;i++) {
   * DclNode child = d.getChild(i); visit(child, level + 1); } }
   * 
   * public void visit(DclNode d, int level) { print(d, level); }
   * 
   * public void visit(StmtsNode s, int level) { int childCount =
   * s.getChildCount(); print(s, level); for(int i = 0; i< childCount; i++) {
   * visit(s.getChild(i), level + 1); } }
   * 
   * public void visit(StmtNode s, int level) { print(s, level); }
   */



  @Override
  public void visitLevel(Func_callNode node, int level) {
	  print(node, level);
	  Func_Call_ParamNode parameters = node.getParamNode();
	  if (parameters != null) {
		  parameters.acceptLevel(this, level + 1);
	  }    
  }

  @Override
  public void visitLevel(BlockNode node, int level) {
	  print(node, level);
	  if(node.getDclsNode() != null) {
		  node.getDclsNode().acceptLevel(this, level + 1);
	  }
	  if (node.getStmtsNode() != null) {
		  node.getStmtsNode().acceptLevel(this, level + 1); 
	  }
	  if (node.getReturn_stmtNode() != null) {
		  node.getReturn_stmtNode().acceptLevel(this, level + 1);		  
	  }
    
  }

  @Override
  public void visitLevel(Func_defNode node, int level) {
  System.out.println("In Func_defNode");
    
  }

    @Override
    public void visitLevel(Func_Call_ParamNode node, int level) {
        print(node, level);
        for(IExpr child: node.getExpr()){
            ((AstNode)child).acceptLevel(this, level+1);
        }
    }

    @Override
  public void visitLevel(Print_lNode node, int level) {
      print(node, level);
      ((AstNode) node.getExprNode()).acceptLevel(this, level+1);
    
  }


  @Override
  public void visitLevel(Return_stmtNode node, int level) {
	  print(node, level);
	  ((AstNode)node.getReturnExpr()).acceptLevel(this, level + 1);
  }

  @Override
  public void visitLevel(If_stmtNode node, int level) {
	  print(node, level);
	  ((AstNode) node.getExpr()).acceptLevel(this, level + 1);
	  node.getIfBlock().acceptLevel(this, level + 1);
	  BlockNode elseBlock = node.getElseBlock();
	  if(elseBlock != null) {
		  elseBlock.acceptLevel(this, level + 1);
	  }
    
  }

  @Override
  public void visitLevel(StartNode node, int level) {
    print(node, level);
    node.getDcls().acceptLevel(this, level + 1);
    node.getStmts().acceptLevel(this, level + 1);
    
  }

  @Override
  public void visitLevel(BooleantfNode node, int level) {
	  print(node, level);
  }


  @Override
  public void visitLevel(StmtsNode node, int level) {
    print(node, level);
    int childCount = node.getChildCount();
    for(int i = 0; i < childCount; i++) {
      node.getChild(i).acceptLevel(this, level + 1);
    }
    
  }


  @Override
  public void visitLevel(DclNode node, int level) {
    print(node, level);
    
  }


  @Override
  public void visitLevel(TypeNode node, int level) {
  System.out.println("In TypeNode");
    
  }

  @Override
  public void visitLevel(DclsNode node, int level) {
    print(node, level);
    int childCount = node.getChildCount();
    for(int i = 0; i < childCount;i++) {
      node.getChild(i).acceptLevel(this, level + 1);
    }
    
  }

  @Override
  public void visitLevel(While_stmtNode node, int level) {
  System.out.println("In While_stmtNode");
    
  }

  @Override
  public void visitLevel(ExprNode node, int level) {
  System.out.println("In ExprNode");
    
  }

  @Override
  public void visitLevel(ParametersNode node, int level) {
  System.out.println("In ParametersNode");
    
  }

  @Override
  public void visitLevel(Assign_stmtNode node, int level) {
	  print(node, level);
	  ((AstNode) node.getExprNode()).acceptLevel(this, level + 1);
    
  }
	@Override
	public void visitLevel(IntegerNode node, int level) {
		print(node, level);
	}
	
	@Override
	public void visitLevel(DoubleNode node, int level) {
		print(node, level);	
	}
	
	@Override
	public void visitLevel(StringNode node, int level) {
		print(node, level);	
	}
	
	@Override
	public void visitLevel(IdNode node, int level) {
		print(node, level);	
	}

	@Override
	public void visitLevel(Built_in_funcNode node, int level) {
		print(node, level);
		if (node.getPrint_lNode() != null) {
			node.getPrint_lNode().acceptLevel(this, level + 1);
		}
		
	}

	@Override
	public void visitLevel(AdditiveExprNode node, int level) {
		print(node, level);
		((AstNode)node.getLeftNode()).acceptLevel(this, level + 1);
		((AstNode)node.getRightNode()).acceptLevel(this, level + 1);
		
	}

	@Override
	public void visitLevel(MultiplicativeExprNode node, int level) {
		print(node, level);
		((AstNode)node.getLeftNode()).acceptLevel(this, level + 1);
		((AstNode)node.getRightNode()).acceptLevel(this, level + 1);
		
	}
  

}
