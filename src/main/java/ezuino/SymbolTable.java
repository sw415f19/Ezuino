package ezuino;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable {
    private ArrayList<Integer> levelList = new ArrayList<Integer>();
    private ArrayList<String> identList = new ArrayList<String>();
    private ArrayList<String> attrList = new ArrayList<String>();

    public void addSymbol(int level, String ident, String attr) {

        int index = alreadyInTable(level, ident);
        if (index!=-1){
            attrList.set(index, attr);
            System.out.println("Updated Symbol Table : (" + level + ", " + ident + ", " + attr + ")");
        }
        else {
            levelList.add(level);
            identList.add(ident);
            attrList.add(attr);
            System.out.println("Added to Symbol Table : (" + level + ", " + ident + ", " + attr + ")");
        }
    }

    public void removeSymbol(int level, String ident, String attr) {
        int placement = getPlacement(level, ident, attr);
        if (placement != -1) {
            levelList.remove(placement);
            identList.remove(placement);
            attrList.remove(placement);
            System.out.println("Removed from Symbol Table : (" + level + ", " + ident + ", " + attr + ")");
            return;
        }
        System.out.println("Not in Symbol Table");
    }

    public void getSymbolTable(){
        System.out.println("Priting Symbol Table....");
        for (int i = 0; i < identList.size(); i++) {
            System.out.println("Entry : " + i + 
            " Level : " + levelList.get(i) + 
            " Identity : " + identList.get(i) + 
            " Attribute : " + attrList.get(i) 
            );
        }
    }

    private int alreadyInTable(int level, String ident){

        for (int i = 0; i < levelList.size(); i++) {
            if (levelList.get(i).equals(level) 
            && identList.get(i).equals(ident)){
                return i;
            }
        }
        return -1;
    }

    private int getPlacement(int level, String ident, String attr) {
        List<Integer> llist = getIndexOfLevel(level);
        List<Integer> ilist = getIndexOfIdent(ident);
        List<Integer> alist = getIndexOfAttr(attr);

        List<Integer> result = new ArrayList<Integer>();

        // Null check, if not found.
        if (llist.isEmpty() || ilist.isEmpty()|| alist.isEmpty()) return -1;

        for (int a : alist) {
            if (ilist.contains(a) && llist.contains(a)) result.add(a);
        }

        if (result.size() == 1) return result.get(0);
        return -1;
    }

    private List getIndexOfLevel(int item) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < levelList.size(); i++) {
            int a = levelList.get(i);
            if (a == item) {
                list.add(i);
            }
        }
        return list;
    }

    private List getIndexOfIdent(String item) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < identList.size(); i++) {
            String a = identList.get(i);
            if (a.equals(item)) {
                list.add(i);
            }
        }
        return list;
    }

    private List getIndexOfAttr(String item) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < attrList.size(); i++) {
            String a = attrList.get(i);
            if (a.equals(item)) {
                list.add(i);
            }
        }
        return list;
    }

}