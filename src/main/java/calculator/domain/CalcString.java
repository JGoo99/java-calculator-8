package calculator.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalcString {
    private final String separator;
    private final List<Integer> numbers;

    public CalcString(String separator, List<Integer> numbers) {
        this.separator = separator;
        this.numbers = numbers;
    }

    public long sum() {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public String toString() {
        return "CalcString{" +
            "separator='" + separator + '\'' +
            ", numbers=" + numbers +
            '}';
    }
}
