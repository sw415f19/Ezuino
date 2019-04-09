package exceptions;

public class AlreadyInTableException extends Exception
{
    public AlreadyInTableException(String symbol)
    {
        super("Symbol : " + symbol + " is already defined in scope!");
    }
}
