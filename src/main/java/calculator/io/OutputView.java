package calculator.io;

public class OutputView {
    public void askCalcString() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
    }

    public void printResult(long result) {
        System.out.println("결과 : " + result);
    }
}
