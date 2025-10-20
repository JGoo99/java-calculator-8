package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final String DEFAULT_DELIMITERS = "[,:]";
    private static final Pattern CUSTOM_HEADER_PATTERN =
            Pattern.compile("^//(.)(?:\\n|\\r?\\n|\\\\n)([\\s\\S]*)$"); // \n, CRLF, literal "\\n"

    private StringCalculator() {
    }

    public static long add(String input) {
        if (isNullOrEmpty(input)) {
            return 0L;
        }

        ParsedInput parsedInput = parse(input);
        String[] tokens = parsedInput.getBody().split(parsedInput.getDelimiterRegex(), -1);

        long sum = 0L;
        for (String t : tokens) {
            sum += parseAndValidateNumber(t);
        }
        return sum;
    }

    private static long parseAndValidateNumber(String t) {
        if (t.isEmpty()) {
            throw new IllegalArgumentException("구분자 사이에 값이 비어 있습니다.");
        }
        long n = parseLongStrict(t);
        if (n < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다.");
        }
        return n;
    }

    private static boolean isNullOrEmpty(String input) {
        return input == null || input.isEmpty();
    }

    private static ParsedInput parse(String input) {
        if (!input.startsWith("/")) {
            if (!Character.isDigit(input.charAt(0)) && input.charAt(0) != '-' && input.charAt(0) != '+') {
                throw new IllegalArgumentException("입력 형식이 잘못되었습니다.");
            }
            return new ParsedInput(DEFAULT_DELIMITERS, input);
        }

        Matcher m = CUSTOM_HEADER_PATTERN.matcher(input);
        if (!m.matches()) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 잘못되었습니다.");
        }

        String delim = Pattern.quote(m.group(1));
        String body = m.group(2);
        return new ParsedInput(delim, body);
    }

    private static long parseLongStrict(String s) {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다.");
        }
    }

}
