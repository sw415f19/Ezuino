package symboltable;

import ast.ITypeNode;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, ITypeNode> symbolMap = new HashMap<String, ITypeNode>();
    private SymbolTable parentTable;

    public SymbolTable(SymbolTable previousTable) {
        this.parentTable = previousTable;
    }

    public boolean enterSymbol(String key, ITypeNode node) {
        boolean result = !symbolMap.containsKey(key);
        symbolMap.put(key, node);
        return result;
    }

    public boolean isGlobalScope() {
        return this.parentTable == null;
    }

    public ITypeNode retrieveSymbol(String key) {
        if (symbolMap.containsKey(key)) {
            return symbolMap.get(key);
        }

        if (isGlobalScope()) {
            return null;
        }

        return this.parentTable.retrieveSymbol(key);
    }

    public ITypeNode getSymbolCurrentScope(String id) {
        return symbolMap.get(id);
    }
}
