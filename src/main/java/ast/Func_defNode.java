package ast;

import java.util.List;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class Func_defNode extends AstNode {

	List<ParamNode> parameters;
	BlockNode blockNode;
	
	public Func_defNode(List<ParamNode> parameters, BlockNode blockNode){
		this.parameters = parameters;
		this.blockNode = blockNode;
	}
	
	public List<ParamNode> getParameters() {
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

}
