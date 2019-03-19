package ezuino;

import generated.EzuinoBaseVisitor;
import ast.*;
import generated.EzuinoParser;
import org.antlr.v4.runtime.RuleContext;

public class BuildAstVisitor extends EzuinoBaseVisitor<AstNode> {

    @Override
    public AstNode visitStart(EzuinoParser.StartContext ctx) {

        StartNode StartNode = new StartNode();
        System.out.println("Made Start node");
        StartNode.setDcls((DclsNode) visit(ctx.dcls()));
        StartNode.setStmts((StmtsNode) visit(ctx.stmts()));
        return StartNode;
    }

    @Override
    public AstNode visitDcls(EzuinoParser.DclsContext ctx) {
        System.out.println("Made Dcls node");
        DclsNode dclsNode = new DclsNode();
        for(RuleContext child: ctx.dcl()) {
           dclsNode.addChild((DclNode) visit(child));
        }
        return dclsNode;
    }

    @Override
    public AstNode visitStmts(EzuinoParser.StmtsContext ctx) {
        System.out.println("Made Stmts node");
        return new StmtsNode();
    }

    @Override
    public AstNode visitDcl(EzuinoParser.DclContext ctx) {
        System.out.println("Made dcl node");
        return new DclNode();
    }


}
