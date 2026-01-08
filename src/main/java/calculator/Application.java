package calculator;

import calculator.io.InputView;
import calculator.io.OutputView;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        try {
            new AppRunner(new InputView(), new OutputView()).run();
        } finally {
            Console.close();
        }
    }
}
