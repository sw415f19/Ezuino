package functionality;

import java.util.HashMap;

import ast.Type;
import ast.type.IdNode;
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

    public void addToList(String id, IdNode node) {
        if (typeCheck.containsKey(id)) {
            if (typeCheck.get(id) == node.getType())
                return;
        }
        ErrorHandler.wrongTypeToList(typeCheck.get(id), node);
    }

}
