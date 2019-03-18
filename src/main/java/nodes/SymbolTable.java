package nodes;
import java.util.ArrayList;

public class SymbolTable {
    private ArrayList<Integer> levelList = new ArrayList<Integer>();
    private ArrayList<String> identList = new ArrayList<String>();
    private ArrayList<String> attrList = new ArrayList<String>();

    public SymbolTable() {
    }

    public void addSymbol(int level, String ident, String attr) {
        levelList.add(level);
        identList.add(ident);
        attrList.add(attr);
        System.out.println("Added to Symbol Table : (" + level + ", " + ident + ", " + attr + ")");
    }

    public void removeSymbol(int level, String ident, String attr) {
        int placement = getPlacement(level, ident, attr);
        System.out.println(placement);
        if (placement != -1) {
            levelList.remove(placement);
            identList.remove(placement);
            attrList.remove(placement);
            System.out.println("Removed from Symbol Table : (" + level + ", " + ident + ", " + attr + ")");
            return;
        }
        System.out.println("Not in Symbol Table");
    }

    private int getPlacement(int level, String ident, String attr) {
        if (getIndexOfLevel(level) == getIndexOfAttr(attr) && getIndexOfAttr(attr) == getIndexOfIdent(ident)
        && getIndexOfLevel(level) == getIndexOfIdent(ident))return getIndexOfIdent(ident);
        return -1;
    }

    private int getIndexOfLevel(int item) {
        for (int i = 0; i < levelList.size(); i++) {
            int a = levelList.get(i);
            if (a == item) {
                return i;
            }
        }
        return 0;
    }

    private int getIndexOfIdent(String item) {
        for (int i = 0; i < identList.size(); i++) {
            String a = identList.get(i);
            if (a.equals(item)) {
                return i;
            }
        }
        return 0;
    }

    private int getIndexOfAttr(String item) {
        for (int i = 0; i < attrList.size(); i++) {
            String a = attrList.get(i);
            if (a.equals(item)) {
                return i;
            }
        }
        return 0;
    }

}

