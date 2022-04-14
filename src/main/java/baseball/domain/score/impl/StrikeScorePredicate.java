package baseball.domain.score.impl;

import baseball.domain.BaseballNumbers;
import baseball.domain.input.InputNumbers;
import baseball.domain.score.ScorePredicate;
import baseball.game.BaseballGame;
import java.util.List;

public class StrikeScorePredicate implements ScorePredicate {

    @Override
    public int execute(InputNumbers inputNumbers, BaseballNumbers baseballNumbers) {
        int count = 0;

        List<Integer> inputNumberList = inputNumbers.getInputNumbers();
        List<Integer> baseballNumberList = baseballNumbers.getBaseballNumber();

        for (int i = 0; i < BaseballGame.BASEBALL_GAME_NUMBER_SIZE; i++) {
            count += getStrikeCount(inputNumberList.get(i), baseballNumberList.get(i));
        }

        return count;
    }

    private int getStrikeCount(int source, int target) {
        return source == target ? 1 : 0;
    }
}
