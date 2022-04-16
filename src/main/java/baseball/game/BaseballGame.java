package baseball.game;

import baseball.domain.BaseballNumbers;
import baseball.domain.GameResult;
import baseball.domain.input.InputNumbers;
import baseball.domain.score.impl.BallScorePredicate;
import baseball.domain.score.impl.StrikeScorePredicate;
import baseball.view.PrintGameResult;
import baseball.view.PrintUserInput;

public class BaseballGame {
    public static final int BASEBALL_GAME_NUMBER_SIZE = 3;

    public static void start() {
        BaseballNumbers baseballNumbers = new BaseballNumbers();
        InputNumbers inputNumbers = new InputNumbers();
        GameResult gameResult = new GameResult(new StrikeScorePredicate(), new BallScorePredicate());

        while (true) {
            PrintUserInput.printMessage();
            inputNumbers = inputNumbers.receiveInputNumbers();

            gameResult.calculateScore(inputNumbers, baseballNumbers);
            PrintGameResult.printMessage(gameResult);
        }
    }
}
