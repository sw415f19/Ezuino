package ast.funcallstmt;

import java.util.List;

import ast.ITypeNode;
import ast.StmtNode;
import ast.Type;
import ast.expr.aexpr.AExpr;

public abstract class Func_callStmtNode extends StmtNode implements ITypeNode {

	private List<AExpr> parameters;
	private Type type;
	private String Id;

	public Func_callStmtNode(String id, List<AExpr> parameters) {
		this.Id = id;
		this.parameters = parameters;
	}

	public List<AExpr> getParameters() {
		return parameters;
	}

	public String getId() {
		return Id;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

}
