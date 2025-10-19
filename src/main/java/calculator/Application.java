package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    private static final Pattern HEADER = Pattern.compile("^//(.)(?:\\\\n|\\r?\\n)([\\s\\S]*)$");

    public static void main(String[] args) {
        try {
            System.out.println("덧셈할 문자열을 입력해 주세요.");
            String input = Console.readLine();

            if (input == null || input.isEmpty()) {
                System.out.println("결과 : 0");
                return;
            }

            if (input.startsWith("//")) {
                Matcher m = HEADER.matcher(input);
                if (m.matches()) {
                    String delim = m.group(1);
                    String body = m.group(2);
                    printAnswer(body, Pattern.quote(delim));
                } else {
                    throw new IllegalArgumentException("커스텀 구분자 형식 오류");
                }
            } else {
                printAnswer(input, "[,:]");
            }
        } catch (Exception e) {
            String msg = (e.getMessage() == null || e.getMessage().isEmpty()) ? "잘못된 입력" : e.getMessage();
            throw new IllegalArgumentException(msg);
        }
    }

    private static void printAnswer(String input, String delim) {
        if (input == null || input.isEmpty()) {
            System.out.println("결과 : 0");
            return;
        }

        String[] tokens = input.split(delim, -1);
        long answer = 0;

        for (String t : tokens) {
            if (t.isEmpty()) {
                throw new IllegalArgumentException("잘못된 입력");
            }
            int cur = Integer.parseInt(t);
            if (cur < 0) {
                throw new IllegalArgumentException("음수 발생: " + cur);
            }
            answer += cur;
        }
        System.out.println("결과 : " + answer);
    }
}
