package symbolTable;

import java.util.Stack;

import ast.AstNode;
import exceptions.AlreadyInTableException;

public class SymbolTableCasper {
    //private ArrayList<SymbolTable> symbolTables = new ArrayList<SymbolTable>();

    private Stack<SymbolTable> symbolTableStack = new Stack<SymbolTable>();
    public SymbolTableCasper() {
		SymbolTable firstTable = new SymbolTable(null);
		symbolTableStack.push(firstTable);
	}

    public void openScope(){
    	//Finds the previous table relative to the newly opened scope
        SymbolTable symbolTable = new SymbolTable(symbolTableStack.peek());
        
        symbolTableStack.push(symbolTable);
    }

    public void closeScope() {
        symbolTableStack.pop();
    }
    
    public void enterSymbol(String name, AstNode value) {
    	SymbolTable latestSymbolTable = symbolTableStack.peek();
    	try {
    		latestSymbolTable.insert(name, value);
    	} catch(AlreadyInTableException e) {
    		throw e;
    	}
    }
    
   public AstNode retreiveSymbol(String name) {
	   SymbolTable currentTable = symbolTableStack.peek();
	   
   }

    public int getSymbolTableSize(){
        return symbolTableStack.size();
    }
}
