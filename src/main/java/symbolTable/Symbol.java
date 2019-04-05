package symbolTable;

import ast.AstNode;
import astvisitors.SymbolTableFillingVisitor;

public class Symbol {
    private int level;
    private String identity;
    private AstNode node;

    public Symbol(String identity, AstNode node) {
        this.level = SymbolTableFillingVisitor.symbolTableManager.getLevel();
        this.identity = identity;
        this.node = node;
    }

    public int getLevel() {
        return level;
    }


    public String getIdentity() {
        return identity;
    }

    public AstNode getNode() {
        return node;
    }

    @Override
    public String toString()
    {
        return "\nSYMBOL OBJECT " +
                " level : ( " + level + " ) " +
                " identity : ( " + identity + " ) " +
                " node : ( "  + node.getClass().getSimpleName() + " ) " +
                " ";
    }
}
