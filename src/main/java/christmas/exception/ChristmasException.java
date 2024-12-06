package christmas.exception;

public class ChristmasException extends IllegalArgumentException {
    private static final String PREFIX = "[ERROR] ";

    public ChristmasException(ErrorMessage errorMessage) {
        super(PREFIX + errorMessage.getMessage());
    }
}
