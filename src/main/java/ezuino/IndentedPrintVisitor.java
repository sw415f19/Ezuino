package ezuino;

import ast.*;

public class IndentedPrintVisitor {
	
	public void print(AstNode node, int level) {
		String resultString = "";
		resultString += level > 0 ? "   ".repeat(level) : "";
		resultString += "+- " + node.toString();
		System.out.println(resultString);
	}
	
	public void visit(StartNode s) {
		System.out.println("Printing AST:");
		print(s, 0);
		visit(s.getDcls(), 1);
		visit(s.getStmts(), 1);
	}
	
	public void visit(DclsNode d, int level) {
		int childCount = d.getChildCount();
		print(d, level);
		for(int i = 0;i < childCount;i++) {
			DclNode child = d.getChild(i);
			visit(child, level + 1);
		}
	}
	
	public void visit(DclNode d, int level) {
		print(d, level);
	}
	
	public void visit(StmtsNode s, int level) {
		int childCount = s.getChildCount();
		print(s, level);
		for(int i = 0; i< childCount; i++) {
			visit(s.getChild(i), level + 1);
		}
	}
	
	public void visit(StmtNode s, int level) {
		print(s, level);
	}
	

}
