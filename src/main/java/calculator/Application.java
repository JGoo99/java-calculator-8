package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Pattern;

public class Application {
    private static final Pattern HEADER = Pattern.compile("^//(.)(?:\\\\n|\\r?\\n)([\\s\\S]*)$");

    public static void main(String[] args) {
        try {
            System.out.println("덧셈할 문자열을 입력해 주세요.");
            String input = Console.readLine();

            long result = StringCalculator.add(input);
            System.out.println("결과 : " + result);
        } catch (Exception e) {
            String msg = (e.getMessage() == null || e.getMessage().isEmpty()) ? "잘못된 입력" : e.getMessage();
            throw new IllegalArgumentException(msg);
        }
    }
}
