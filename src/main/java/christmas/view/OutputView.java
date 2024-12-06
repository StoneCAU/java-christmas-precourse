package christmas.view;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();

    public static void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    private static void printNewLine() {
        System.out.printf(NEW_LINE);
    }

    public static void printErrorMessage(String message) {
        printNewLine();
        System.out.println(message);
    }
}
