package exceptions;

import ast.AstNode;

public class SemanticError extends ErrorMessage {

    public SemanticError(ErrorType errorType , String errorMessage, AstNode leftNode, AstNode rightNode) {
        super(errorType, errorMessage);
    }

    @Override
    public String toString()
    {
        return String.format("SEMANTIC %s %s", this.getTypeString().toUpperCase(), this.getErrorDescription());
    }
    
    
}