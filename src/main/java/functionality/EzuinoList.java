package functionality;

import java.util.HashMap;

import ast.BooleanLiteral;
import ast.Type;
import ast.type.IdNode;
import ast.type.ValNode;
import exceptions.ErrorHandler;

public class EzuinoList {
    private HashMap<String, Type> typeCheck = new HashMap<>();

    public void newList(String id, Type type) {
        if (typeCheck.containsKey(id)) {
            ErrorHandler.listAlreadyDeclared(id);
            return;
        }
        typeCheck.put(id, type);
    }

    public void addToList(String id, ValNode node) {
        if (typeCheck.containsKey(id)) {
            if (typeCheck.get(id) == node.getType())
            {System.out.println("OK" + id);
                return;}
        }
        ErrorHandler.wrongTypeToList(typeCheck.get(id), node);
    }
    public void addToList(String id, IdNode node) {
        if (typeCheck.containsKey(id)) {
            if (typeCheck.get(id) == node.getType())
            {System.out.println("OK" + id);
                return;}
        }
        ErrorHandler.wrongTypeToList(typeCheck.get(id), node);
    }
    public void addToList(String id, BooleanLiteral node) {
        if (typeCheck.containsKey(id)) {
            if (typeCheck.get(id) == node.getType())
            {System.out.println("OK" + id);
                return;}
        }
        ErrorHandler.wrongTypeToList(typeCheck.get(id), node);
    }

}
