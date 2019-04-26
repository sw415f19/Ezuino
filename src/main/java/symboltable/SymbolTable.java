package symboltable;

import ast.ITypeNode;
import ast.Type;
import exceptions.ErrorHandler;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, ITypeNode> symbolMap = new HashMap<String, ITypeNode>();
    private SymbolTable parentTable;

    public SymbolTable(SymbolTable previousTable) {
        this.parentTable = previousTable;
    }

    public void enterSymbol(String key, ITypeNode node) {
        if (!symbolMap.containsKey(key) && notEmpty(key)) {
            symbolMap.put(key, node);
        }

        if (notEmpty(key)) {
            return;
        }
        ErrorHandler.alreadyDeclared(key);
    }
    
    private boolean isGlobalScope() {
    	return this.parentTable == null;
    }

    public Type retrieveSymbol(String key) {

        if (key.toUpperCase().equals("TRUE") || key.toUpperCase().equals("FALSE")) return null;

        if (symbolMap.containsKey(key)) {
             return symbolMap.get(key).getType();
        }
        
        if (isGlobalScope()) {
        	ErrorHandler.notDeclaredVar(key);
            return null;
        }
        
        return this.parentTable.retrieveSymbol(key);
    }

    private boolean notEmpty(String key) {
        return ("<missing ID>" != key.intern());
    }

    public ITypeNode getSymbolNode(String id)
    {
        if(symbolMap.containsKey(id)) {
            return symbolMap.get(id);
        }
        return this.parentTable.getSymbolNode(id);
    }
}
