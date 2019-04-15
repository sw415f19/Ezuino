package symbolTable;

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
        System.out.println("Setting " + key + " to " + node.toString());
        if (!symbolMap.containsKey(key) && notEmpty(key)) {
            symbolMap.put(key, node);
        }

        if (notEmpty(key)) {
            return;
        }
        ErrorHandler.alreadyDeclared(key);
    }

    public Boolean contains(String key) {
        return symbolMap.containsKey(key);
    }

    public Type retrieveSymbol(String key) {
        if (this.contains(key)) {
            return symbolMap.get(key).getType();
        } else {
            if (this.parentTable == null) {
                ErrorHandler.notDeclaredVar(key);
            }
            return this.parentTable.retrieveSymbol(key);
        }
    }

    private boolean notEmpty(String key) {
        return ("<missing ID>" != key.intern());
    }
}