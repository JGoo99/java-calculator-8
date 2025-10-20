package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        try {
            System.out.println("덧셈할 문자열을 입력해 주세요.");
            String input = Console.readLine();

            long result = StringCalculator.add(input);
            System.out.println("결과 : " + result);
        } catch (Exception e) {
            String msg = (e.getMessage() == null || e.getMessage().isEmpty()) ? "잘못된 입력입니다." : e.getMessage();
            throw new IllegalArgumentException(msg);
        }
    }
}
