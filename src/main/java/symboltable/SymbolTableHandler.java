package symboltable;

import java.util.Stack;

import ast.ITypeNode;

public class SymbolTableHandler {
    private Stack<SymbolTable> symbolTableStack = new Stack<SymbolTable>();

    private boolean printDcl;

    public SymbolTableHandler(boolean printDcl) {
        this.printDcl = printDcl;
    }

    public void openScope() {
        if (symbolTableStack.empty()) {
            SymbolTable firstTable = new SymbolTable(null);
            symbolTableStack.push(firstTable);
        } else {
            // Finds the previous table relative to the newly opened scope
            SymbolTable symbolTable = new SymbolTable(getCurrentSymbolTable());
            symbolTableStack.push(symbolTable);
        }
    }

    public void closeScope() {
        if (symbolTableStack.isEmpty()) {
            System.err.println("Programming error in SymbolTableHandler");
        }
        symbolTableStack.pop();
    }

    private SymbolTable getCurrentSymbolTable() {
        return symbolTableStack.peek();
    }

    public boolean enterSymbol(String name, ITypeNode node) {
        if (printDcl) {
            System.out.println("Setting " + name + " to " + node.toString());
        }
        return getCurrentSymbolTable().enterSymbol(name, node);
    }

    public ITypeNode retrieveSymbol(String key) {
        return getCurrentSymbolTable().retrieveSymbol(key);
    }

    public ITypeNode getSymbolCurrentScope(String key) {
        return getCurrentSymbolTable().getSymbolCurrentScope(key);
    }

    public boolean isGlobalScope() {
        return getCurrentSymbolTable().isGlobalScope();
    }
}
