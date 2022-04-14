package baseball.domain;

import baseball.domain.input.InputNumbers;
import baseball.domain.score.ScorePredicate;

public class GameResult {
    private final ScorePredicate strikeScorePredicate;
    private final ScorePredicate ballScorePredicate;
    private int strikeCount;
    private int ballCount;

    public GameResult(ScorePredicate strikeScorePredicate, ScorePredicate ballScorePredicate) {
        this.strikeCount = 0;
        this.ballCount = 0;
        this.strikeScorePredicate = strikeScorePredicate;
        this.ballScorePredicate = ballScorePredicate;
    }

    public boolean isGameOver() {
        return strikeCount == 3;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public int getBallCount() {
        return ballCount;
    }

    public boolean isNothing() {
        return strikeCount == 0 && ballCount == 0;
    }

    public void calculateScore(InputNumbers inputNumbers, BaseballNumbers baseballNumbers) {
        this.clear();
        this.strikeCount = strikeScorePredicate.execute(inputNumbers, baseballNumbers);
        this.ballCount = ballScorePredicate.execute(inputNumbers, baseballNumbers);
    }

    private void clear() {
        this.strikeCount = 0;
        this.ballCount = 0;
    }
}
