package symbolTable;

import ast.AstNode;
import exceptions.AlreadyInTableException;
import exceptions.NotInTableException;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, AstNode> symbolMap = new HashMap<String, AstNode>();
    private SymbolTable parentTable;

    public SymbolTable(SymbolTable previousTable) {
        this.parentTable = previousTable;
    }

    public void insertNode(String key, AstNode node) throws AlreadyInTableException {
        if (!symbolMap.containsKey(key)) {
            symbolMap.put(key, node);
        } else {
            throw new AlreadyInTableException(key + " is already in table: " + this);
        }
    }

    public Boolean contains(String key) {
        return symbolMap.containsKey(key);
    }

    public AstNode getNode(String key) throws NotInTableException {
        if (this.contains(key)) {
            return symbolMap.get(key);
        }
        else {
            if (this.parentTable == null) {
                throw new NotInTableException();
            }
            return this.parentTable.getNode(key);
        }
    }

    @Override
    public String toString() {
        return "## Symboltable map ##" +
                symbolMap;
    }
}