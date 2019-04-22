package exceptions;

import java.util.ArrayList;
import java.util.List;

import ast.AstNode;
import ast.ITypeNode;
import ast.Type;

public class ErrorHandler {

    private static final List<ErrorMessage> messageList = new ArrayList<>();

    public static boolean hasErrors() {
        if (messageList.size() > 0)
            return true;
        return false;
    }

    public static void reset(){
        messageList.clear();
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

    public static void typeMismatch(AstNode leftNode, AstNode rightNode)
    {
        Type leftType = leftNode.getType();
        Type rightType = rightNode.getType();
        messageList.add(new GeneralError(ErrorType.ERROR, "Type mismatch! \n -- Left type: " + leftType.name() + " Right type: " + rightType.name() + " \n -- Left node: " + leftNode + " Right node: " + rightNode));
    }

    public static void returnNotGuaranteed(){
        messageList.add(new GeneralError(ErrorType.ERROR, "Return is not guaranteed since there are no return or an else block in the outer scope with return"));
    }

    public static void invalidTF()
    {
        messageList.add(new SyntaxError(ErrorType.ERROR, "Invalid spelling of TRUE / FALSE - mistype?"));
    }

    public static void ParameterLengthError(String functionname)
    {
        messageList.add(new GeneralError(ErrorType.ERROR, "The number of arguments does not fit in the function \"" + functionname + "\""));
    }

    public static void ParameterTypeError(String functionname)
    {
        messageList.add(new GeneralError(ErrorType.ERROR, "The type of the parameters does not fit in the invokation of function \""  + functionname + "\""));
    }


}