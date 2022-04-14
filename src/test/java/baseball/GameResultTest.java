package baseball;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import baseball.domain.GameResult;
import baseball.domain.score.ScorePredicate;
import baseball.domain.score.impl.BallScorePredicate;
import baseball.domain.score.impl.StrikeScorePredicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    @Nested
    @DisplayName("입력한값에 맞는 게임결과가 정상적으로 리턴되어야 한다")
    class GameOverTest {
        ScorePredicate strikeScorePredicate = mock(StrikeScorePredicate.class);
        ScorePredicate ballScorePredicate = mock(BallScorePredicate.class);

        @Test
        @DisplayName("스트라이크가 3개라면 정상적으로 종료되는 상태를 리턴해야 한다")
        void game_over_success() {
            GameResult gameResult = new GameResult(strikeScorePredicate, ballScorePredicate);

            when(strikeScorePredicate.execute(any(), any()))
                    .thenReturn(3);
            when(ballScorePredicate.execute(any(), any()))
                    .thenReturn(0);

            gameResult.calculateScore(any(), any());

            assertEquals(3, gameResult.getStrikeCount());
            assertEquals(0,gameResult.getBallCount());
            assertTrue(gameResult.isGameOver());
        }

        @Test
        @DisplayName("스트라이크가 3개 아니라면 종료되는 않는 상태를 리턴해야 한다")
        void game_over_failed() {
            GameResult gameResult = new GameResult(strikeScorePredicate, ballScorePredicate);

            when(strikeScorePredicate.execute(any(), any()))
                    .thenReturn(1);
            when(ballScorePredicate.execute(any(), any()))
                    .thenReturn(1);

            gameResult.calculateScore(any(), any());

            assertEquals(1, gameResult.getStrikeCount());
            assertEquals(1,gameResult.getBallCount());
            assertFalse(gameResult.isGameOver());
        }

        @Test
        @DisplayName("상태가 낫싱이라면 종료되지 않는 상태를 리턴해야 한다")
        void game_over_failed_v2() {
            GameResult gameResult = new GameResult(strikeScorePredicate, ballScorePredicate);

            when(strikeScorePredicate.execute(any(), any()))
                    .thenReturn(0);
            when(ballScorePredicate.execute(any(), any()))
                    .thenReturn(0);

            assertEquals(0, gameResult.getStrikeCount());
            assertEquals(0,gameResult.getBallCount());
            assertTrue(gameResult.isNothing());
            assertFalse(gameResult.isGameOver());
        }
    }
}
