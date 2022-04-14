package baseball;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import baseball.domain.BaseballNumbers;
import baseball.domain.GameResult;
import baseball.domain.input.InputNumbers;
import baseball.domain.score.ScorePredicate;
import baseball.domain.score.impl.BallScorePredicate;
import baseball.domain.score.impl.StrikeScorePredicate;
import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

public class BaseballScoreTest {
    private MockedStatic<Console> console;
    private BaseballNumbers baseballNumbers;
    private InputNumbers inputNumbers;

    @BeforeEach
    void before() {
        console = mockStatic(Console.class);
        baseballNumbers = mock(BaseballNumbers.class);
        inputNumbers = new InputNumbers();
    }

    @AfterEach
    void after() {
        console.close();
    }

    void strikePredicateMocking(String input, List<Integer> 컴퓨터가_생성한_난수) {
        when(baseballNumbers.getBaseballNumber())
                .thenReturn(컴퓨터가_생성한_난수);
        when(Console.readLine())
                .thenReturn(input);

        inputNumbers.createInputNumbers();
    }

    void ballPredicateMocking(String input, List<Integer> 컴퓨터가_생성한_난수) {
        when(baseballNumbers.getBaseballNumber())
                .thenReturn(컴퓨터가_생성한_난수);
        when(Console.readLine())
                .thenReturn(input);

        List<Integer> 유저가_입력한_값 = inputNumbers.createInputNumbers();

        for (int number : 유저가_입력한_값) {
            when(baseballNumbers.isContains(number))
                    .thenReturn(컴퓨터가_생성한_난수.contains(number));
        }
    }

    @Nested
    @DisplayName("사용자가 값을 입력시에 스트라이크가 몇개인지 리턴하라")
    class StrikeScoreTest {
        private ScorePredicate strikeScorePredicate;

        @BeforeEach
        void predicateInit() {
            strikeScorePredicate = new StrikeScorePredicate();
        }

        @DisplayName("스트라이크가 1인 값을 입력하면 스트라이크값이 1로 계산되서 리턴되어야 한다")
        @ParameterizedTest
        @ValueSource(strings = {"246", "789" , "351" , "513"})
        void zero_strike_test(String input) {
            List<Integer> 컴퓨터가_생성한_난수 = Arrays.asList(1,3,5);
            strikePredicateMocking(input, 컴퓨터가_생성한_난수);

            int result = strikeScorePredicate.execute(inputNumbers, baseballNumbers);
            assertEquals(0, result);
        }

        @DisplayName("스트라이크가 1인 값을 입력하면 스트라이크값이 1로 계산되서 리턴되어야 한다")
        @ParameterizedTest
        @ValueSource(strings = {"124", "153" , "245" , "234"})
        void one_strike_test(String input) {
            List<Integer> 컴퓨터가_생성한_난수 = Arrays.asList(1,3,5);
            strikePredicateMocking(input, 컴퓨터가_생성한_난수);

            int result = strikeScorePredicate.execute(inputNumbers, baseballNumbers);
            assertEquals(1, result);
        }

        @DisplayName("스트라이크가 2인 값을 입력하면 스트라이크값이 2로 계산되서 리턴되어야 한다")
        @ParameterizedTest
        @ValueSource(strings = {"134", "125" , "138" , "235"})
        void two_strike_test(String input) {
            List<Integer> 컴퓨터가_생성한_난수 = Arrays.asList(1,3,5);
            strikePredicateMocking(input, 컴퓨터가_생성한_난수);

            int result = strikeScorePredicate.execute(inputNumbers, baseballNumbers);
            assertEquals(2, result);
        }

        @DisplayName("스트라이크가 3인 값을 입력하면 스트라이크값이 3로 계산되서 리턴되어야 한다")
        @Test
        void three_strike_test() {
            String input = "135";

            List<Integer> 컴퓨터가_생성한_난수 = Arrays.asList(1,3,5);
            strikePredicateMocking(input, 컴퓨터가_생성한_난수);

            int result = strikeScorePredicate.execute(inputNumbers, baseballNumbers);
            assertEquals(3, result);
        }
    }

    @Nested
    @DisplayName("사용자가 값을 입력시에 볼이 몇개인지 리턴하라")
    class BallScoreTest {
        private ScorePredicate ballScorePredicate;

        @BeforeEach
        void predicateInit() {
            ballScorePredicate = new BallScorePredicate();
        }

        @DisplayName("볼이 0인 값을 입력하면 볼값이 0으로 계산되서 리턴되어야 한다")
        @ParameterizedTest
        @ValueSource(strings = {"246" , "135" , "137"})
        void zero_ball_test(String input) {
            List<Integer> 컴퓨터가_생성한_난수 = Arrays.asList(1,3,5);
            ballPredicateMocking(input, 컴퓨터가_생성한_난수);

            int result = ballScorePredicate.execute(inputNumbers, baseballNumbers);
            assertEquals(0, result);
        }

        @DisplayName("볼이 1인 값을 입력하면 볼값이 1로 계산되서 리턴되어야 한다")
        @ParameterizedTest
        @ValueSource(strings = {"324" , "567" , "398"})
        void one_ball_test(String input) {
            List<Integer> 컴퓨터가_생성한_난수 = Arrays.asList(1,3,5);
            ballPredicateMocking(input, 컴퓨터가_생성한_난수);

            int result = ballScorePredicate.execute(inputNumbers, baseballNumbers);
            assertEquals(1, result);
        }

        @DisplayName("볼이 2인 값을 입력하면 볼값이 2로 계산되서 리턴되어야 한다")
        @ParameterizedTest
        @ValueSource(strings = {"512", "514" , "312" , "359"})
        void two_ball_test(String input) {
            List<Integer> 컴퓨터가_생성한_난수 = Arrays.asList(1,3,5);
            ballPredicateMocking(input, 컴퓨터가_생성한_난수);

            int result = ballScorePredicate.execute(inputNumbers, baseballNumbers);
            assertEquals(2, result);
        }

        @DisplayName("볼이 3인 값을 입력하면 볼값이 3로 계산되서 리턴되어야 한다")
        @ParameterizedTest
        @ValueSource(strings = {"513" , "351"})
        void three_ball_test(String input) {
            List<Integer> 컴퓨터가_생성한_난수 = Arrays.asList(1,3,5);
            ballPredicateMocking(input, 컴퓨터가_생성한_난수);

            int result = ballScorePredicate.execute(inputNumbers, baseballNumbers);
            assertEquals(3, result);
        }
    }
    
    @Nested
    @DisplayName("사용자가 값을 입력시 낫싱인지 판단하라")
    class NothingTest {

        @DisplayName("strike , out 이 모두 0이면 상태는 nothing이 된다")
        @ParameterizedTest
        @ValueSource(strings = {"246" , "467" , "798" , "876"})
        void zz(String input) {
            List<Integer> 컴퓨터가_생성한_난수 = Arrays.asList(1,3,5);

            strikePredicateMocking(input, 컴퓨터가_생성한_난수);
            ballPredicateMocking(input, 컴퓨터가_생성한_난수);

            GameResult gameResult = new GameResult();
            gameResult.calculateScore(inputNumbers, baseballNumbers);

            assertEquals(0, gameResult.getStrikeCount());
            assertEquals(0, gameResult.getOutCount());
            assertTrue(gameResult.isNothing());
        }
    }
}
