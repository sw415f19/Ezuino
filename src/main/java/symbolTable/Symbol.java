package symbolTable;

import ast.AstNode;
import astvisitors.SymbolTableVisitor;

import java.util.Objects;

public class Symbol {
    private int level;
    private String identity;
    private AstNode node;

    public Symbol(String identity, AstNode node) {
        this.level = SymbolTableVisitor.symbolTableManager.getLevel();
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

    public void setNode(AstNode node)
    {
        this.node = node;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return identity.equals(symbol.identity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity);
    }
}
