package symbolTable;

import java.util.Stack;

public class SymbolTableManager {
    //private ArrayList<SymbolTable> symbolTables = new ArrayList<SymbolTable>();

    private int level = 0;
    Stack<SymbolTable> symbleTableStack = new Stack();

    public void openScope(){
        SymbolTable symbolTable = new SymbolTable();
        level += 1;
        symbleTableStack.push(symbolTable);
        showpush(symbleTableStack, symbolTable);
    }

    public void closeScope() {
        level -= 1;
        showpop(symbleTableStack);
    }

    public void showpush(Stack st, SymbolTable a) {
        st.push(a);
        System.out.println("push(" + a + ")");
        //System.out.println("stack: " + st);
    }

    public void showpop(Stack st) {
        System.out.print("pop -> ");
        SymbolTable a = (SymbolTable) st.pop();
        System.out.println(a);
        System.out.println("stack: " + st);
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

}
