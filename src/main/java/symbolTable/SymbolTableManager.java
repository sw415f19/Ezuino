package symbolTable;

import java.util.Stack;

public class SymbolTableManager {
    //private ArrayList<SymbolTable> symbolTables = new ArrayList<SymbolTable>();

    private int level = 0;
    private Stack<SymbolTable> symbleTableStack = new Stack();

    public void openScope(){
        SymbolTable symbolTable = new SymbolTable();
        level += 1;
        symbleTableStack.push(symbolTable);
    }

    public void closeScope() {
        level -= 1;
        symbleTableStack.pop();
    }

    public SymbolTable getLatestSymbolTable(){
        return symbleTableStack.peek();
    }

    public void addSymbolTable(SymbolTable symbolTable){
        symbleTableStack.add(symbolTable);
    }

    public int getSymbolTableSize(){
        return symbleTableStack.size();
    }

    public int getLevel()
    {
        return level;
    }



    public Stack<SymbolTable> getSymbleTableStack() {
        return symbleTableStack;
    }
}
