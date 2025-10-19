package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final String DEFAULT_DELIMITERS = "[,:]";
    private static final Pattern CUSTOM_HEADER_PATTERN =
            Pattern.compile("^//(.)(?:\\n|\\r?\\n|\\\\n)([\\s\\S]*)$"); // \n, CRLF, literal "\\n"

    public static long add(String input) {
        if (input == null || input.isEmpty()) {
            return 0L;
        }

        ParsedInput parsedInput = parse(input);
        String[] tokens = parsedInput.getBody().split(parsedInput.getDelimiterRegex(), -1);

        long sum = 0L;
        for (String t : tokens) {
            if (t.isEmpty()) {
                throw new IllegalArgumentException("잘못된 입력");
            }
            long n = parseLongStrict(t);
            if (n < 0) {
                throw new IllegalArgumentException("음수 발생");
            }
            sum += n;
        }
        return sum;
    }

    private static ParsedInput parse(String input) {
        if (!input.startsWith("//")) {
            return new ParsedInput(DEFAULT_DELIMITERS, input);
        }

        Matcher m = CUSTOM_HEADER_PATTERN.matcher(input);
        if (!m.matches()) {
            throw new IllegalArgumentException("커스텀 구분자 형식 오류");
        }

        String delim = Pattern.quote(m.group(1));
        String body = m.group(2);
        return new ParsedInput(delim, body);
    }

    private static long parseLongStrict(String s) {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 입력");
        }
    }

}
