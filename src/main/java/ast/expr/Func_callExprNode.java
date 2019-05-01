package ast.expr;

import ast.expr.aexpr.AExpr;
import ast.expr.aexpr.APrimaryExpr;
import astvisitors.AstVisitor;

import java.util.ArrayList;

public class Func_callExprNode extends APrimaryExpr {

    private String ID;
    private ArrayList<AExpr> parameters = new ArrayList<AExpr>();

    public Func_callExprNode(String ID, ArrayList<AExpr> parameters) {
        this.ID = ID;
        this.parameters = parameters;
    }

    public String getID() {
        return ID;
    }

    public ArrayList<AExpr> getParameters() {
        return parameters;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return super.toString() + " { ID: " + this.ID + " type: " + type + " }";
    }

}