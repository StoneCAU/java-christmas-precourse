package christmas.controller;

import christmas.domain.Visit;
import christmas.exception.ChristmasException;
import christmas.validator.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    public void run() {
        OutputView.printWelcomeMessage();
        Visit visit = getVisitDay();
    }

    private Visit getVisitDay() {
        while (true) {
            try {
                String input = InputView.inputVisitDay();
                return InputValidator.validateVisitDay(input);
            } catch (ChristmasException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
