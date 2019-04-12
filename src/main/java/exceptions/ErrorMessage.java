package exceptions;

public class ErrorMessage {

    private String errorMessage;
    private ErrorType errorType;

    public ErrorMessage(ErrorType errorType, String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorType = errorType;
    }

    public String getTypeString()
    {
        switch (getType())
        {
            case ERROR:
                return "ERROR : ";
            case WARNING:
                return "WARNING : ";
            default:
                return "UNKNOWN ERROR";
        }
    }

    public ErrorType getType()
    {
        return this.errorType;
    }

    public String getErrorDescription()
    {
        return this.errorMessage;
    }



}