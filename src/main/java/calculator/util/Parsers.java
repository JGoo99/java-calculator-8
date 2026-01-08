package calculator.util;

import calculator.domain.CalcString;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Parsers {
    private Parsers() {
    }

    public static String parseNonNull(String s) {
        if (s == null) {
            throw new IllegalArgumentException("[ERROR] 빈 값은 입력할 수 없습니다." + "(입력값: \"" + s + "\")");
        }
        return s.trim();
    }

    public static int parseIntStrict(String s) {
        try {
            if (s.isEmpty()) {
                return 0;
            }
            return Integer.parseInt(parseNonNull(s));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해 주세요." + "(입력값: \"" + s + "\")");
        }
    }

    public static int parseIntInRange(String s, int min, int max) {
        int n = parseIntStrict(s);
        if (n < min || n > max) {
            throw new IllegalArgumentException("[ERROR] 입력 값은 " + min + "~" + max + " 사이여야 합니다." + "(입력값: \"" + s + "\")");
        }
        return n;
    }

    public static long parseLongStrict(String s) {
        try {
            return Long.parseLong(parseNonNull(s));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해 주세요." + "(입력값: \"" + s + "\")");
        }
    }

    public static long parseLongInRange(String s, long min, long max) {
        long n = parseLongStrict(s);
        if (n < min || n > max) {
            throw new IllegalArgumentException("[ERROR] 입력 값은 " + min + "~" + max + " 사이여야 합니다." + "(입력값: \"" + s + "\")");
        }
        return n;
    }

    public static CalcString parseCalcString(String input) {
        // //;\n1;2;3
        if (input.startsWith("//")) {
            Pattern pattern = Pattern.compile("^//(.)\\\\n$");
            Matcher matcher = pattern.matcher(input.substring(0, 5));
            if (!matcher.matches()) {
                throw new IllegalArgumentException("[ERROR] 입력 형식이 잘못되었습니다." + "(입력값: " + input + ")");
            }
            String separator = matcher.group(1);
            String numberString = input.substring(5);

            Pattern numberStringPattern = Pattern.compile("^\\d*(?:" + separator + "\\d+)*$");
            Matcher numberStringMatcher = numberStringPattern.matcher(numberString);

            if (!numberStringMatcher.matches()) {
                throw new IllegalArgumentException("[ERROR] 입력 형식이 잘못되었습니다." + "(입력값: " + numberString + ")");
            }

            List<String> numberStrings = Arrays.asList(numberString.split(separator, -1));
            List<Integer> numbers = numberStrings.stream()
                .map(Parsers::parseIntStrict)
                .toList();
            return new CalcString(separator, numbers);
        }

        Pattern pattern = Pattern.compile("^\\d*(?:(,|:)\\d+)*$");
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("[ERROR] 입력 형식이 잘못되었습니다." + "(입력값: " + input + ")");
        }

        List<String> numberStrings = Arrays.asList(input.split("(,|:)", -1));
        List<Integer> numbers = numberStrings.stream()
            .map(Parsers::parseIntStrict)
            .toList();

        return new CalcString(",|:", numbers);
    }
}
