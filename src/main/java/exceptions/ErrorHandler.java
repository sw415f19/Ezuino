package exceptions;

import java.util.ArrayList;
import java.util.List;

import ast.ITypeNode;
import ast.Type;
import ast.funcallstmt.Func_callStmtNode;

public class ErrorHandler {

    private List<ErrorMessage> messageList = new ArrayList<>();
    private int errorCount = 0;

    public boolean hasErrors() {
        return errorCount != 0;
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

    private void addError(ErrorMessage e) {
        if (e.getType().equals(ErrorType.ERROR)) {
            errorCount += 1;
        }
        messageList.add(e);
    }

    public void varAlreadyDeclared(String character) {
        addError(new SyntaxError(ErrorType.ERROR, "Variable " + character + " is already defined in this scope."));
    }

    public void funcAlreadyDeclared(String character) {
        addError(new SyntaxError(ErrorType.ERROR, "Function " + character + " is already defined in this scope."));
    }

    public void undeclaredVariable(String character) {
        addError(new SyntaxError(ErrorType.ERROR, "Variable " + character + " has not been declared."));
    }

    public void undeclaredFunction(String character) {
        addError(new SyntaxError(ErrorType.ERROR, "Function " + character + " has not been declared."));
    }

    public void reservedKeyword(String Id, String Keyword) {
        addError(new GeneralError(ErrorType.ERROR, "\"" + Id + "\"" + " is too similar to \"" + Keyword + "\" which is a reserved keyword."));
    }

    public void compatibilityKeyword(String Keyword) {
        addError(new GeneralError(ErrorType.ERROR, "\"" + Keyword + "\"" + " is a Java specified keyword and therefore unavailable."));
    }

    public void unexpectedType(ITypeNode node, Type type) {
        addError(new GeneralError(ErrorType.ERROR, "Unexpeced type! Expected: " + type.name() + ", was " + node.getType().name() + " - Node: " + node));
    }

    public void emptyStack() {
        addError(new GeneralError(ErrorType.WARNING, "Tried to close a scope and pop a symbol table, however, the symbol table stack was empty!"));
    }

    public void typeMismatch(ITypeNode leftNode, ITypeNode rightNode) {
        Type leftType = leftNode.getType();
        Type rightType = rightNode.getType();
        addError(new GeneralError(ErrorType.ERROR, "Type mismatch! \n -- Left type: " + leftType.name() + " Right type: " + rightType.name() + " \n -- Left node: " + leftNode + " Right node: " + rightNode));
    }

    public void returnNotGuaranteed() {
        addError(new GeneralError(ErrorType.ERROR, "Return is not guaranteed since there are no return or an else block in the outer scope with return"));
    }

    public void parameterLengthError(String functionName) {
        addError(new GeneralError(ErrorType.ERROR, "The number of arguments does not fit in the function \"" + functionName + "\""));
    }

    public void parameterTypeError(String functionName) {
        addError(new GeneralError(ErrorType.ERROR, "The type of the parameters does not fit in the invokation of function \"" + functionName + "\""));
    }

    public void listNotSameType(ITypeNode firstParam, Func_callStmtNode node) {
        addError(new GeneralError(ErrorType.ERROR, String.valueOf(node.getParameters().get(1)) + " is type " + node.getParameters().get(1).getType() + " - tried to add it to an " + firstParam.getType() + " list."));
    }

    public void invalidKeyword() {
        addError(new SyntaxError(ErrorType.ERROR, "Invalid syntax - Conditional syntax error."));
    }

    public void syntaxError(int errorCount, String msg, int line, int charPositionInLine) {
        String errorMsg = String.format("#" + errorCount + " - " + "Error parsing expression: '%s' on line %s, position %s", msg, line, charPositionInLine);
        addError(new SyntaxError(ErrorType.ERROR, errorMsg));
    }

    public void invalidOperatorForType(String operator, Type type) {
        addError(new GeneralError(ErrorType.ERROR, "Invalid operator \"" + operator + "\" for type " + type.toString()));

    }

    public void invalidFunctionParameterError(String nodeName) {
        addError(new SyntaxError(ErrorType.ERROR, "Invalid parameter in function: " + nodeName));
    }

    public void arduinoIntRangeError(int val) {
        addError(new SyntaxError(ErrorType.ERROR, "Int out of Arduino value range: " + val));
    }

    public void nestedFuncDef(String id) {
        addError(new GeneralError(ErrorType.ERROR, "The function \"" + id + "\" is defined within a function. This not possible."));

    }

    public void missingEssentialFunction(String nodeid, boolean essential) {
        ErrorType err = essential ? ErrorType.ERROR : ErrorType.WARNING;
        addError(new GeneralError(err, "The function \"" + nodeid + "\" is missing."));

    }

    public void invalidCastException(Type castType, Type argumentType) {
        addError(new GeneralError(ErrorType.ERROR, "Invalid cast. Cannot cast " + argumentType + " to " + castType));

    }

    public void stmtInGlobalScope(String key) {
        addError(new GeneralError(ErrorType.ERROR, "The stmt " + key + " must be within a function block"));

    }
}