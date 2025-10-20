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
    void 커스텀에서_구분자가_공백인_경우_값을_반환한다() {
        assertSimpleTest(() -> {
            run("// \\n1 2 3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀에서_구분자가_부호인_경우_값을_반환한다1() {
        assertSimpleTest(() -> {
            run("//+\\n1+2+3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀에서_구분자가_부호인_경우_값을_반환한다2() {
        assertSimpleTest(() -> {
            run("//-\\n1-2-3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀에서_구분자가_탭인_경우_값을_반환한다() {
        assertSimpleTest(() -> {
            run("//\t\\n1\t2\t3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 본문이_없는_경우_0을_반환한다() {
        assertSimpleTest(() -> {
            run("\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 커스텀에서_본문이_없는_경우_0을_반환한다() {
        assertSimpleTest(() -> {
            run("//,\\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 커스텀에서_구분자가_슬래시인_경우_값을_반환한다() {
        assertSimpleTest(() -> {
            run("///\\n1/2/3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀에서_구분자가_이모지인_경우_값을_반환한다1() {
        assertSimpleTest(() -> {
            run("//\uD83D\uDE42\\n1\uD83D\uDE422\uD83D\uDE423");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀에서_구분자가_이모지인_경우_값을_반환한다2() {
        assertSimpleTest(() -> {
            run("//😃\\n1😃2😃3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀에서_구분자가_숫자인_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//5\\n15253"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("숫자는 구분자로 사용할 수 없습니다.")
        );
    }

    @Test
    void 커스텀에서_구분자가_백슬래시인_경우_값을_반환한다() {
        assertSimpleTest(() -> {
            run("//\\\\n1\\2\\3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 숫자_주변에_공백이_포함되어_있는_경우_예회를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1 "))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("숫자 주변에 공백이 포함되어 있습니다.")
        );
    }

    @Test
    void 커스텀에서_숫자_주변에_공백이_포함되어_있는_경우_예회를_던진다1() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n 1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("숫자 주변에 공백이 포함되어 있습니다.")
        );
    }

    @Test
    void 커스텀에서_숫자_주변에_공백이_포함되어_있는_경우_예회를_던진다2() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n1, 2"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("숫자 주변에 공백이 포함되어 있습니다.")
        );
    }

    @Test
    void 합계가_Long_범위를_초과할_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("9223372036854775807,1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("합계가 long 범위를 초과했습니다.")
        );
    }

    @Test
    void 커스텀에서_음수가_포함된_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n1;-2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("음수는 허용되지 않습니다.")
        );
    }

    @Test
    void 커스텀_구분자가_두글자_이상인_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;;\\n1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("커스텀 구분자 형식이 잘못되었습니다.")
        );
    }

    @Test
    void 커스텀_구분자_형식이_잘못_설정된_경우_예외를_던진다1() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("/;\\n1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("커스텀 구분자 형식이 잘못되었습니다.")
        );
    }

    @Test
    void 커스텀_구분자_형식이_잘못_설정된_경우_예외를_던진다2() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(";\\n1;2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("입력 형식이 잘못되었습니다.")
        );
    }

    @Test
    void 커스텀_구분자_형식이_잘못_설정된_경우_예외를_던진다3() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;n1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("커스텀 구분자 형식이 잘못되었습니다.")
        );
    }

    @Test
    void 숫자가_아닌_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,a,3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("숫자가 아닌 값이 포함되어 있습니다.")
        );
    }

    @Test
    void 숫자로_시작하지_않는_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(",1,3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("입력 형식이 잘못되었습니다.")
        );
    }

    @Test
    void 숫자가_구분자_사이에_없는_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,,3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("구분자 사이에 값이 비어 있습니다.")
        );
    }

    @Test
    void 숫자로_끝나지_않는_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,2,"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("구분자 사이에 값이 비어 있습니다.")
        );
    }

    @Test
    void 커스텀에서_숫자가_아닌_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n1;ㅁ;3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("숫자가 아닌 값이 포함되어 있습니다.")
        );
    }

    @Test
    void 커스텀에서_숫자로_시작하지_않는_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n;1;3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("구분자 사이에 값이 비어 있습니다.")
        );
    }

    @Test
    void 커스텀에서_숫자가_구분자_사이에_없는_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n1;;3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("구분자 사이에 값이 비어 있습니다.")
        );
    }

    @Test
    void 커스텀에서_숫자로_끝나지_않는_경우_예외를_던진다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n1;2;"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("구분자 사이에 값이 비어 있습니다.")
        );
    }

    @Test
    void 기본구분자_합계가_int_초과() {
        assertSimpleTest(() -> {
            run("2000000000,2000000000");
            assertThat(output()).contains("결과 : 4000000000");
        });
    }

    @Test
    void 경계값_케이스() {
        assertSimpleTest(() -> {
            run("2147483647,1"); // Integer.MAX_VALUE + 1
            assertThat(output()).contains("결과 : 2147483648");
        });
    }

    @Test
    void 커스텀구분자_합계가_int_초과() {
        assertSimpleTest(() -> {
            run("//;\\n2000000000;2000000000");
            assertThat(output()).contains("결과 : 4000000000");
        });
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
