package symboltable;

import ast.ITypeNode;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, ITypeNode> symbolMap = new HashMap<String, ITypeNode>();
    private SymbolTable parentTable;

    SymbolTable(SymbolTable previousTable) {
        this.parentTable = previousTable;
    }

    boolean enterSymbol(String key, ITypeNode node) {
        boolean result = !symbolMap.containsKey(key);
        symbolMap.put(key, node);
        return result;
    }

    boolean isGlobalScope() {
        return this.parentTable == null;
    }

    ITypeNode retrieveSymbol(String key) {
        if (symbolMap.containsKey(key)) {
            return symbolMap.get(key);
        }

        if (isGlobalScope()) {
            return null;
        }

        return this.parentTable.retrieveSymbol(key);
    }

    ITypeNode getSymbolCurrentScope(String id) {
        return symbolMap.get(id);
    }
}
