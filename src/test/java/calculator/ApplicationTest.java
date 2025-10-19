package calculator;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 음수가_포함된_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀에서_음수가_포함된_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n1;-2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자가_두글자_이상인_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;;\\n1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자_형식이_잘못_설정된_경우_예외를_던진다1() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("/;\\n1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자_형식이_잘못_설정된_경우_예외를_던진다2() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(";\\n1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자_형식이_잘못_설정된_경우_예외를_던진다3() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;n1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 숫자가_아닌_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,a,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 숫자로_시작하지_않는_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(",1,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 숫자가_구분자_사이에_없는_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 숫자로_끝나지_않는_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,2,"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀에서_숫자가_아닌_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n1;ㅁ;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀에서_숫자로_시작하지_않는_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n;1;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀에서_숫자가_구분자_사이에_없는_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n1;;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀에서_숫자로_끝나지_않는_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n1;2;"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 기본_쉼표_구분자와_양수() {
        assertSimpleTest(() -> {
            run("1,2,3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 기본_콜론_구분자와_양수() {
        assertSimpleTest(() -> {
            run("1:2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자와_양수() {
        assertSimpleTest(() -> {
            run("//;\\n1;2;3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
