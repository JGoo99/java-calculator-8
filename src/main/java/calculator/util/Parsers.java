package calculator.util;

public final class Parsers {
    private Parsers() {
    }

    public static String parseNonBlank(String s) {
        if (s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 빈 값은 입력할 수 없습니다." + "(입력값: \"" + s + "\")");
        }
        return s.trim();
    }

    public static int parseIntStrict(String s) {
        try {
            return Integer.parseInt(parseNonBlank(s));
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
            return Long.parseLong(parseNonBlank(s));
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
}
