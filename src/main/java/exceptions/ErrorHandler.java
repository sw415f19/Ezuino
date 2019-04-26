package exceptions;

import java.util.ArrayList;
import java.util.List;

import ast.AstNode;
import ast.ITypeNode;
import ast.Type;
import ast.funcallstmt.Func_callStmtNode;

public class ErrorHandler {

    private List<ErrorMessage> messageList = new ArrayList<>();

    public boolean hasErrors() {
        if (messageList.size() > 0)
            return true;
        return false;
    }

    public void reset() {
        messageList.clear();
    }

    public void printErrorList() {
        if (hasErrors()) {
            System.err.println(" -- ## ERROR OUTPUT CONSOLE ## -- ");
            for (ErrorMessage message: messageList) {
                System.out.println(message);
            }
        }
    }

    public void alreadyDeclared(String character) {
        messageList.add(new SyntaxError(ErrorType.ERROR, character + " is already defined in this scope."));
    }

    public void notDeclaredVar(String character) {
        messageList.add(new SyntaxError(ErrorType.ERROR, character + " has not been declared."));
    }

    public void reservedKeyword(String character) {
        messageList.add(new GeneralError(ErrorType.ERROR, "\" " + character + " \"" + " is a reserved keyword and can not be used."));
    }

    public void unexpectedType(ITypeNode node, Type type) {
        messageList.add(new GeneralError(ErrorType.ERROR, "\" " + "Unexpeced type! Expected: " + type.name() + ", was " + node.getType().name() + " - Node: " + node));
    }

    public void emptyStack() {
        messageList.add(new GeneralError(ErrorType.WARNING, "Tried to close a scope and pop a symbol table, however, the symbol table stack was empty!"));
    }

    public void typeMismatch(ITypeNode leftNode, ITypeNode rightNode) {
        Type leftType = leftNode.getType();
        Type rightType = rightNode.getType();
        messageList.add(new GeneralError(ErrorType.ERROR, "Type mismatch! \n -- Left type: " + leftType.name() + " Right type: " + rightType.name() + " \n -- Left node: " + leftNode + " Right node: " + rightNode));
    }

    public void returnNotGuaranteed() {
        messageList.add(new GeneralError(ErrorType.ERROR, "Return is not guaranteed since there are no return or an else block in the outer scope with return"));
    }

    public void invalidTF() {
        messageList.add(new SyntaxError(ErrorType.ERROR, "Invalid spelling of TRUE / FALSE - mistype?"));
    }

    public void ParameterLengthError(String functionName) {
        messageList.add(new GeneralError(ErrorType.ERROR, "The number of arguments does not fit in the function \"" + functionName + "\""));
    }

    public void ParameterTypeError(String functionName) {
        messageList.add(new GeneralError(ErrorType.ERROR, "The type of the parameters does not fit in the invokation of function \"" + functionName + "\""));
    }

    public void invalidParamLength(String s) {
        messageList.add(new GeneralError(ErrorType.ERROR, "Invalid list usage - correct example listAdd(myList, a) \"" + s + "\" ."));
    }

    public void listNotSameType(ITypeNode firstParam, Func_callStmtNode node) {
        messageList.add(new GeneralError(ErrorType.ERROR, String.valueOf(node.getParameters().get(1)) + " is type " + node.getParameters().get(1).getType() + " - tried to add it to an " + firstParam.getType() + " list."));
    }

    public void invalidKeyword() {
        messageList.add(new SyntaxError(ErrorType.ERROR, "Invalid syntax - Conditional syntax error."));
    }

}