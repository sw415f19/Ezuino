package exceptions;

public class AlreadyInTableException extends RuntimeException
{
    public AlreadyInTableException(String symbol)
    {
        super("Symbol : " + symbol + " is already defined in scope!");
    }
}
