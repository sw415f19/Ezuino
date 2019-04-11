package ast;

import java.util.List;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class Func_defNode extends StmtNode implements ITypeNode {

	private List<DclNode> parameters;
	private BlockNode blockNode;
	private String ID;
	private Type type;
	
	
	public Func_defNode(String ID, Type type, List<DclNode> parameters, BlockNode blockNode){
		this.ID = ID;
		this.type = type;
		this.parameters = parameters;
		this.blockNode = blockNode;
	}
	
	public String getId() {
		return ID;
	}
	
	public List<DclNode> getParameters() {
		return parameters;
	}

	public BlockNode getBlockNode() {
		return blockNode;
	}
	
	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
	
	@Override
	public void acceptLevel(AstLevelVisitor v, int level) {
		v.visitLevel(this, level);
	}

	@Override
	public String toString() {
		return "Func_defNode{" +
				"ID='" + ID + '\'' +
				'}';
	}

	@Override
	public void setType(Type type) {
		this.type = type;
		
	}

	@Override
	public Type getType() {
		return this.type;
	}
}
