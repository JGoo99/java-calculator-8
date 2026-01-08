package calculator.io;

import calculator.util.Parsers;
import calculator.util.Retry;
import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String readNonBlankLine() {
        return Retry.untilSuccess(() -> Parsers.parseNonBlank(readTrimmedLineOrThrow()));
    }

    public int readInt() {
        return Retry.untilSuccess(() -> Parsers.parseIntStrict(readTrimmedLineOrThrow()));
    }

    public int readIntInRange(int min, int max) {
        return Retry.untilSuccess(() -> Parsers.parseIntInRange(readTrimmedLineOrThrow(), min, max));
    }

    public long readLong() {
        return Retry.untilSuccess(() -> Parsers.parseLongStrict(readTrimmedLineOrThrow()));
    }

    public long readLongInRange(long min, long max) {
        return Retry.untilSuccess(() -> Parsers.parseLongInRange(readTrimmedLineOrThrow(), min, max));
    }

    private String readTrimmedLineOrThrow() {
        String line = Console.readLine();
        if (line == null) {
            throw new IllegalArgumentException("[ERROR] 입력이 비어있습니다." + "(입력값: \"" + line + "\")");
        }
        return line.trim();
    }
}
