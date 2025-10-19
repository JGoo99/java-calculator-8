package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.StringTokenizer;

public class Application {
    public static void main(String[] args) {
        try {
            System.out.println("덧셈할 문자열을 입력해 주세요.");
            String input = Console.readLine();
            StringTokenizer st = new StringTokenizer(input, ",|:");
            long answer = 0;
            while (st.hasMoreTokens()) {
                int cur = Integer.parseInt(st.nextToken());
                answer += cur;
            }
            System.out.println("결과 : " + answer);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
