package exceptions;

public class GeneralError extends ErrorMessage{

    public GeneralError(ErrorType errorType , String errorMessage) {
        super(errorType, errorMessage);
    }

    @Override
    public String toString()
    {
        return String.format("GENERAL %s %s", this.getTypeString().toUpperCase(), this.getErrorDescription());
    }
    
    
}