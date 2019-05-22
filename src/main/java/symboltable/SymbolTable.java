package symboltable;

import ast.ITypeNode;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, ITypeNode> symbolMap = new HashMap<String, ITypeNode>();
    private SymbolTable parentTable;

    protected SymbolTable(SymbolTable previousTable) {
        this.parentTable = previousTable;
    }

    protected boolean enterSymbol(String key, ITypeNode node) {
        boolean result = !symbolMap.containsKey(key);
        symbolMap.put(key, node);
        return result;
    }

    protected boolean isGlobalScope() {
        return this.parentTable == null;
    }

    protected ITypeNode retrieveSymbol(String key) {
        if (symbolMap.containsKey(key)) {
            return symbolMap.get(key);
        }

        if (isGlobalScope()) {
            return null;
        }

        return this.parentTable.retrieveSymbol(key);
    }

    protected ITypeNode getSymbolCurrentScope(String id) {
        return symbolMap.get(id);
    }

	public boolean isKeyInGlobalScope(String id) {
		if(symbolMap.containsKey(id)) {
			return isGlobalScope();
		}
		else if (parentTable != null){
			return this.parentTable.isKeyInGlobalScope(id);
		}
		else {
			return false;
		}
	}
}
