package symbolTable;

import ast.AstNode;
import exceptions.AlreadyInTableException;
import exceptions.NotInTableException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, AstNode> symbolMap = new HashMap<String, AstNode>();
    private int level;
    private SymbolTable previousTable;

    public SymbolTable(SymbolTable previousTable) {
        this.previousTable = previousTable;
        // If previous table is null, current table is global scope
        if(previousTable == null)
        {
        	level = 0;
        }
        else // otherwise use reference to previous table to determine level
        {
        	level = previousTable.level + 1;
        }
    }

    public int getLevel() {
    	return level;
    }
    
    public void insert(String key, AstNode node) throws AlreadyInTableException  {
    	if(!symbolMap.containsKey(key)) {
    		symbolMap.put(key, node);
    	}
    	else {
    		throw new AlreadyInTableException(key + " is already in table: " + this);
    	}
    }
    public Boolean contains(String key) {
    	return symbolMap.containsKey(key);
    }
    
    public AstNode getValue(String key) throws NotInTableException{
    	if(this.contains(key)) {
    		return symbolMap.get(key);
    	}
    	else {
    		if(this.previousTable == null) {
    			throw new NotInTableException();
    		}
    		return this.previousTable.getValue(key);
    	}
    }

    @Override
    public String toString() {
        return "## Symboltable map ##" +
                symbolMap;
    }
}