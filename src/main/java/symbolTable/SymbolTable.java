package symbolTable;

import java.util.ArrayList;

public class SymbolTable {
    public ArrayList<Symbol> symbolList = new ArrayList<Symbol>();

    public SymbolTable()
    {
        this.symbolList = symbolList;
    }

    public Symbol retrieveFirstSymbol(){
        return symbolList.get(0);
    }

    public void insert(Symbol symbol){
        symbolList.add(symbol);
    }


    @Override
    public String toString()
    {
        return "## Symboltable List ##" +
                 symbolList;
    }
}