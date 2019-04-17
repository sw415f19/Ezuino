package functionality;

import java.util.ArrayList;
import java.util.List;

public class EzuinoList 
{
    private List<String> arguments = new ArrayList<>();

    public EzuinoList() {
        System.out.println("new empty list");
    }

    public EzuinoList(List<String> arguments) {
        System.out.println("new list with " + arguments);
    }


    
}
