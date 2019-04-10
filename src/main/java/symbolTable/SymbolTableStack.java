package symbolTable;

import java.util.Stack;

public class SymbolTableStack {
    private Stack<SymbolTable> symbolTableStack = new Stack<SymbolTable>();

    public SymbolTableStack() {
        SymbolTable firstTable = new SymbolTable(null);
        symbolTableStack.push(firstTable);
    }

    public void openScope() {
        //Finds the previous table relative to the newly opened scope
        SymbolTable symbolTable = new SymbolTable(symbolTableStack.peek());
        symbolTableStack.push(symbolTable);
    }

    public void closeScope() {
        symbolTableStack.pop();
    }

    public SymbolTable getSymbolTable() {
        return symbolTableStack.peek();
    }
}
