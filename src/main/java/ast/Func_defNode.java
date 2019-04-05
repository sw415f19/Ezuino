package ast;

import java.util.List;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class Func_defNode extends StmtNode {

	private String readType;
	private List<DclNode> parameters;
	private BlockNode blockNode;
	private String ID;
	
	public Func_defNode(String readType, String ID, List<DclNode> parameters, BlockNode blockNode){
		this.readType = readType;
		this.ID = ID;
		this.parameters = parameters;
		this.blockNode = blockNode;
	}
	
	public String getType() {
		return readType;
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
}
