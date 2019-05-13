package exceptions;

import java.util.ArrayList;
import java.util.List;

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

    public void printErrors(String reason) {
        if (hasErrors()) {
            StringBuilder sb = new StringBuilder();
            String errmsg = "##  " + reason + "\n -- ## ERROR OUTPUT CONSOLE ## -- ";
            sb.append(errmsg + "\n");
            for (ErrorMessage message : messageList) {
                sb.append(message + "\n");
            }
            System.err.println(sb.toString());
        }
    }

    public void varAlreadyDeclared(String character) {
        messageList.add(new SyntaxError(ErrorType.ERROR, "Variable " + character + " is already defined in this scope."));
    }

    public void funcAlreadyDeclared(String character) {
        messageList.add(new SyntaxError(ErrorType.ERROR, "Function " + character + " is already defined in this scope."));
    }

    public void undeclaredVariable(String character) {
        messageList.add(new SyntaxError(ErrorType.ERROR, "Variable " + character + " has not been declared."));
    }

    public void undeclaredFunction(String character) {
        messageList.add(new SyntaxError(ErrorType.ERROR, "Function " + character + " has not been declared."));
    }

    public void reservedKeyword(String character) {
        messageList.add(new GeneralError(ErrorType.ERROR, "\" " + character + " \"" + " is a reserved keyword and can not be used."));
    }

    public void unexpectedType(ITypeNode node, Type type) {
        messageList.add(new GeneralError(ErrorType.ERROR, "Unexpeced type! Expected: " + type.name() + ", was " + node.getType().name() + " - Node: " + node));
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

    public void parameterLengthError(String functionName) {
        messageList.add(new GeneralError(ErrorType.ERROR, "The number of arguments does not fit in the function \"" + functionName + "\""));
    }

    public void parameterTypeError(String functionName) {
        messageList.add(new GeneralError(ErrorType.ERROR, "The type of the parameters does not fit in the invokation of function \"" + functionName + "\""));
    }

    public void listNotSameType(ITypeNode firstParam, Func_callStmtNode node) {
        messageList.add(new GeneralError(ErrorType.ERROR, String.valueOf(node.getParameters().get(1)) + " is type " + node.getParameters().get(1).getType() + " - tried to add it to an " + firstParam.getType() + " list."));
    }

    public void invalidKeyword() {
        messageList.add(new SyntaxError(ErrorType.ERROR, "Invalid syntax - Conditional syntax error."));
    }

    public void invalidCastException() {
        messageList.add(new GeneralError(ErrorType.ERROR, "Invalid Cast Error - tried to cast a Double to Integer"));
    }

    public void syntaxError(int errorCount, String msg, int line, int charPositionInLine) {
        String errorMsg = String.format("#" + errorCount + " - " + "Error parsing expression: '%s' on line %s, position %s", msg, line, charPositionInLine);
        messageList.add(new SyntaxError(ErrorType.ERROR, errorMsg));
    }

    public void invalidFunctionParameterError(String nodeName) {
        messageList.add(new SyntaxError(ErrorType.ERROR, "Invalid parameter in function: " + nodeName));
    }

    public void arduinoIntRangeError(int val) {
        messageList.add(new SyntaxError(ErrorType.ERROR, "Int out of Arduino value range: " + val));
    }

    public void nestedFuncDef(String id) {
        messageList.add(new GeneralError(ErrorType.ERROR, "The function \"" + id + "\" is defined within a function. This not possible."));

    }
}