package ast;

import astvisitors.AstVisitor;
import generated.EzuinoParser;

public class Built_in_funcNode extends AstNode {

	EzuinoParser.Print_lContext asd;


    @Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}

}
