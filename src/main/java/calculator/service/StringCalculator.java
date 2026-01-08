package calculator.service;

import calculator.domain.CalcString;

public class StringCalculator {
    public static long run(CalcString calcString) {
        return calcString.sum();
    }
}
