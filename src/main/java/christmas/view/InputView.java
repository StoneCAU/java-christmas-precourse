package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final static String NEW_LINE = System.lineSeparator();

    public static String inputVisitDay() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return input();
    }

    private static void printNewLine() {
        System.out.printf(NEW_LINE);
    }

    private static String input() {
        return Console.readLine();
    }
}
