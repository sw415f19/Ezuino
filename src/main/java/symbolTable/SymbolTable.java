package symbolTable;

import ast.AstNode;
import exceptions.AlreadyInTableException;

import java.util.ArrayList;

public class SymbolTable {
    public ArrayList<Symbol> symbolList = new ArrayList<Symbol>();

    public Symbol retrieveFirstSymbol() {
        return symbolList.get(0);
    }

    public void insert(Symbol symbol) throws AlreadyInTableException
    {
        if (!isAlreadyInTable(symbol)) {
            System.out.println("Added to Symbol Table : " + symbol.getIdentity());
            symbolList.add(symbol);
        } else
            throw new AlreadyInTableException(symbol.getIdentity());
    }

    /* If the symbol has been declared, set the node it has been assigned */
    public void IfDeclaredUpdate(Symbol symbol, AstNode assignedNode) {
        if (symbolList.contains(symbol)) {
            symbol.setNode(assignedNode);
            System.out.println("Updated Node" + assignedNode);
        }
    }


    private boolean isAlreadyInTable(Symbol symbol) {
        return symbolList.contains(symbol);
    }

    @Override
    public String toString() {
        return "## Symboltable List ##" +
                symbolList;
    }
}