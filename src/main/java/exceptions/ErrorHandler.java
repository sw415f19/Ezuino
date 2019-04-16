package exceptions;

import java.util.ArrayList;
import java.util.List;

import ast.ITypeNode;
import ast.Type;

public class ErrorHandler {

    private static final List<ErrorMessage> messageList = new ArrayList<>();

    private static boolean hasErrors() {
        if (messageList.size() > 0) return true;
        return false;
    }

    public static void printErrorList()
    {
        if (hasErrors()){
            System.err.println(" -- ## ERROR OUTPUT CONSOLE ## -- ");
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

    public static void reservedKeyword(String character)
    {
        messageList.add(new GeneralError(ErrorType.ERROR,"\" " +  character + " \"" + " is a reserved keyword and can not be used."));
    }

    public static void unexpectedType(ITypeNode node, Type type)
    {
        messageList.add(new GeneralError(ErrorType.ERROR,"\" " + "Unexpeced type! Expected: " + type.name() + ", was " + node.getType().name() + " - Node: " + node));
    }

    public static void emptyStack()
    {
        messageList.add(new GeneralError(ErrorType.WARNING, "Tried to close a scope and pop a symbol table, however, the symbol table stack was empty!"));
    }

    public static void typeMismatch(ITypeNode leftNode, ITypeNode rightNode)
    {
        Type leftType = leftNode.getType();
        Type rightType = rightNode.getType();
        messageList.add(new GeneralError(ErrorType.ERROR, "Type mismatch! \n -- Left type: " + leftType.name() + " Right type: " + rightType.name() + " \n -- Left node: " + leftNode + " Right node: " + rightNode));
    }


}