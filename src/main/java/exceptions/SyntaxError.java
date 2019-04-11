package exceptions;

public class SyntaxError extends ErrorMessage{

    public SyntaxError(ErrorType errorType , String errorMessage) {
        super(errorType, errorMessage);
    }

    @Override
    public String toString()
    {
        return String.format("SYNTAX %s %s", this.getTypeString().toUpperCase(), this.getErrorDescription());
    }
    
    
}