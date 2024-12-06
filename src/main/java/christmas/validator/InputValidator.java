package christmas.validator;

import christmas.domain.Visit;
import christmas.exception.ChristmasException;
import christmas.exception.ErrorMessage;

public class InputValidator {
    public static Visit validateVisitDay(String input) {
        int day = parseNumber(input);
        validateDay(day);

        return new Visit(day);
    }

    private static int parseNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ChristmasException(ErrorMessage.INVALID_DAY);
        }
    }

    private static void validateDay(int day) {
        if (day < 1 || day > 31) {
            throw new ChristmasException(ErrorMessage.INVALID_DAY);
        }
    }
}
