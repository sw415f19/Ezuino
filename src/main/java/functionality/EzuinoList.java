package functionality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ast.Type;
import ast.type.IdNode;
import exceptions.ErrorHandler;

public class EzuinoList 
{
    private HashMap<String, Type> typeCheck = new HashMap<>();
    private String id;
    private Type type;
    private String value;

    public void newList(String id, Type type){
        if(typeCheck.containsKey(id))
        {
            ErrorHandler.listAlreadyDeclared(id);
            return;
        }
        typeCheck.put(id, type);
    }

    public void addToList(String id, IdNode node){
        if (typeCheck.containsKey(id)){
            System.err.println("IM HERE YEAH");
            if (typeCheck.get(id) == node.getType()){
            System.err.println("ADDED TO LIST " + node.getVal());
            return;
             }
        }
        ErrorHandler.wrongTypeToList(typeCheck.get(id), node);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public EzuinoList id(String id) {
        this.id = id;
        return this;
    }

    public EzuinoList value(String value) {
        this.value = value;
        return this;
    }
    
}
