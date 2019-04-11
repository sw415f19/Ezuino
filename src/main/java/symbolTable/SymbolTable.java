package symbolTable;


import ast.ITypeNode;
import ast.Type;
import exceptions.AlreadyInTableException;
import exceptions.NotInTableException;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, ITypeNode> symbolMap = new HashMap<String, ITypeNode>();
    private SymbolTable parentTable;

    public SymbolTable(SymbolTable previousTable) {
        this.parentTable = previousTable;
    }

    public void enterSymbol(String key, ITypeNode node) throws AlreadyInTableException {
    	System.out.println("Setting " + key + " to " + node.toString());
        if (!symbolMap.containsKey(key)) {
            symbolMap.put(key, node);
        } else {
        	System.err.println("Double declared var: " + key);
        }
    }

    public Boolean contains(String key) {
        return symbolMap.containsKey(key);
    }

    public Type retrieveSymbol(String key) throws NotInTableException {
        if (this.contains(key)) {
            return symbolMap.get(key).getType();
        }
        else {
            if (this.parentTable == null) {
            	System.err.println("Undeclared var: " + key );
            }
            return this.parentTable.retrieveSymbol(key);
        }
    }
}