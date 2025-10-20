package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final String DEFAULT_DELIMITERS = "[,:]";
    private static final Pattern CUSTOM_HEADER_PATTERN =
            Pattern.compile("(?s)^//(.)\\\\n([\\s\\S]*)$");

    private StringCalculator() {
    }

    public static long add(String rawInput) {
        if (isNullOrEmpty(rawInput)) {
            return 0L;
        }

        final String input = normalize(rawInput);
        ParsedInput parsedInput = parse(input);

        if (parsedInput.getBody().isEmpty()) {
            return 0L;
        }

        String[] tokens = parsedInput.getBody().split(parsedInput.getDelimiterRegex(), -1);

        long sum = 0L;
        for (String t : tokens) {
            final long n = parseAndValidateNumber(t, parsedInput.getDelimiterRegex());
            sum = safeAdd(sum, n);
        }
        return sum;
    }

    private static String normalize(String s) {
        return s.replace("\r\n", "\n")
                .replace("\r", "\n");
    }

    private static long parseAndValidateNumber(String token, String delimiterRegex) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("구분자 사이에 값이 비어 있습니다.");
        }

        if (!delimiterRegex.equals("\\Q \\E") && !delimiterRegex.equals("\\Q\\t\\E")) {
            if (token.contains(" ") || token.contains("\t")) {
                throw new IllegalArgumentException("숫자 주변에 공백이 포함되어 있습니다.");
            }
        }

        long n = parseLongStrict(token);
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

        final String delimiterChar = m.group(1);

        final char ch = delimiterChar.charAt(0);
        if (Character.isDigit(ch)) {
            throw new IllegalArgumentException("숫자는 구분자로 사용할 수 없습니다.");
        }

        final String delimiterRegex = Pattern.quote(delimiterChar);
        final String body = m.group(2);

        if (body == null || body.isEmpty()) {
            return new ParsedInput(delimiterRegex, "");
        }
        return new ParsedInput(delimiterRegex, body);
    }

    private static long parseLongStrict(String s) {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다.");
        }
    }

    private static long safeAdd(long a, long b) {
        if (b > 0 && a > Long.MAX_VALUE - b) {
            throw new IllegalArgumentException("합계가 long 범위를 초과했습니다.");
        }
        if (b < 0 && a < Long.MIN_VALUE - b) {
            throw new IllegalArgumentException("합계가 long 범위를 초과했습니다.");
        }
        return a + b;
    }

}
