package christmas.controller;

import christmas.domain.Discount;
import christmas.domain.Orders;
import christmas.domain.Visit;
import christmas.exception.ChristmasException;
import christmas.validator.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    public void run() {
        OutputView.printWelcomeMessage();
        Visit visit = getVisitDay();
        Orders orders = getOrders();
        getResults(visit, orders);
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

    private Orders getOrders() {
        while (true) {
            try {
                String input = InputView.inputOrder();
                return InputValidator.validateOrders(input);
            } catch (ChristmasException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void getResults(Visit visit, Orders orders) {
        OutputView.printPromotionTitle(visit);
        getInfoBeforeDiscount(orders);
        getInfoAfterDiscount(visit, orders);
    }

    private void getInfoBeforeDiscount(Orders orders) {
        OutputView.printOrders(orders);
        OutputView.printTotalPriceBeforeDiscount(orders);
        OutputView.printPresentMenu(orders);
    }

    private void getInfoAfterDiscount(Visit visit, Orders orders) {
        Discount discount = new Discount(visit, orders);
        OutputView.printDiscounts(discount);
        OutputView.printTotalDiscount(discount);
        OutputView.printAfterDiscount(discount);
        OutputView.printBadge(discount);
    }
}
