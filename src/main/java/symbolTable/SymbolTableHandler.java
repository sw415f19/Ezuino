package symbolTable;

import java.util.Stack;

import ast.ITypeNode;
import ast.Type;

public class SymbolTableHandler {
    private Stack<SymbolTable> symbolTableStack = new Stack<SymbolTable>();

    public void openScope() {
        if(symbolTableStack.empty()) {
            SymbolTable firstTable = new SymbolTable(null);
            symbolTableStack.push(firstTable);
        }
        else {
            //Finds the previous table relative to the newly opened scope
            SymbolTable symbolTable = new SymbolTable(symbolTableStack.peek());
            symbolTableStack.push(symbolTable);
        }
    }

    public void closeScope() {
    	if(symbolTableStack.isEmpty()) {
    		System.err.println("Stack is empty! There is nothing to close! - Programming error");
    	}
        symbolTableStack.pop();
    }

	public void enterSymbol(String name, ITypeNode node) {
		symbolTableStack.peek().enterSymbol(name, node);
	}
	
	public Type retrieveSymbol(String name) {
		return symbolTableStack.peek().retrieveSymbol(name);
	}
}
