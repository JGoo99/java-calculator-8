package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        try {
            System.out.println("덧셈할 문자열을 입력해 주세요.");
            String input = Console.readLine();

            if (input == null || input.isEmpty()) {
                System.out.println("결과 : 0");
                return;
            }

            if (input.startsWith("//")) {
                String customStr = input.substring(0, 5);
                Pattern pattern = Pattern.compile("^//(.)\\\\n$");
                Matcher matcher = pattern.matcher(customStr);

                if (matcher.matches()) {
                    printAnswer(input.substring(5), matcher.group(1));
                } else {
                    throw new IllegalArgumentException("커스텀 에러");
                }
            } else {
                printAnswer(input, ",|:");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private static void printAnswer(String input, String delim) {
        StringTokenizer st = new StringTokenizer(input, delim);
        long answer = 0;
        while (st.hasMoreTokens()) {
            int cur = Integer.parseInt(st.nextToken());
            if (cur < 0) {
                throw new IllegalArgumentException("음수 발생");
            }
            answer += cur;
        }
        System.out.println("결과 : " + answer);
    }
}
