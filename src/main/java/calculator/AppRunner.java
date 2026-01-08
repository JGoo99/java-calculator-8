package calculator;

import calculator.domain.CalcString;
import calculator.io.InputView;
import calculator.io.OutputView;
import calculator.service.StringCalculator;

public class AppRunner {
    private final InputView in;
    private final OutputView out;

    public AppRunner(InputView in, OutputView out) {
        this.in = in;
        this.out = out;
    }

    public void run() {
        out.askCalcString();
        CalcString calcString = in.readCalcString();

        long result = StringCalculator.run(calcString);
        out.printResult(result);
    }
}
