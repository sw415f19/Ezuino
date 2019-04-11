package exceptions;

import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {

    private static final List<ErrorMessage> messageList = new ArrayList<>();

    private static boolean hasErrors() {
        if (messageList.size() > 0) return true;
        return false;
    }

    public static void printErrorList()
    {
        if (hasErrors()){
            System.out.println(" -- ## ERROR OUTPUT CONSOLE ## -- ");
            for (ErrorMessage message : messageList) {
                System.out.println(message);
            }
        }
    }

    public static void alreadyDeclared(String character)
    {
        messageList.add(new SyntaxError(ErrorType.ERROR, character + " is already defined in this scope."));
    }

    public static void notDeclaredVar(String character)
    {
        messageList.add(new SyntaxError(ErrorType.ERROR, character + " has not been declared."));
    }

}