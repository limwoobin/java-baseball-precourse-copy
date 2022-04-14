package baseball.domain;

import baseball.domain.input.InputNumbers;
import baseball.domain.score.ScorePredicate;
import baseball.domain.score.impl.BallScorePredicate;
import baseball.domain.score.impl.StrikeScorePredicate;

public class GameResult {
    private final ScorePredicate strikeScorePredicate;
    private final ScorePredicate ballScorePredicate;
    private int strikeCount;
    private int outCount;

    public GameResult() {
        this.strikeCount = 0;
        this.outCount = 0;
        this.strikeScorePredicate = new StrikeScorePredicate();
        this.ballScorePredicate = new BallScorePredicate();
    }

    public void strike() {
        this.strikeCount++;
    }

    public void out() {
        this.outCount++;
    }

    public boolean isGameOver() {
        return strikeCount == 3;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public int getOutCount() {
        return outCount;
    }

    public boolean isNothing() {
        return strikeCount == 0 && outCount == 0;
    }

    public void calculateScore(InputNumbers inputNumbers, BaseballNumbers baseballNumbers) {
        this.clear();
        this.strikeCount = strikeScorePredicate.execute(inputNumbers, baseballNumbers);
        this.outCount = ballScorePredicate.execute(inputNumbers, baseballNumbers);
    }

    private void clear() {
        this.strikeCount = 0;
        this.outCount = 0;
    }
}
