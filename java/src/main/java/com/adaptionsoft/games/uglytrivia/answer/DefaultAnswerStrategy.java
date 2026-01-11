package com.adaptionsoft.games.uglytrivia.answer;

import com.adaptionsoft.games.uglytrivia.player.Player;
import com.adaptionsoft.games.uglytrivia.question.Question;

import java.util.Random;

public class DefaultAnswerStrategy implements AnswerStrategy {
    private final Random random = new Random();
    private static final int COINS_PER_CORRECT_ANSWER = 1;

    @Override
    public AnswerResult handleAnswer(Player player, Question question) {

        if (random.nextInt(9) != 7) {
            player.addCoins(COINS_PER_CORRECT_ANSWER);
            return new AnswerResult(!player.isWinner(), "Answer was correct!!!!\n" + player.getName() + " now has " + player.getCoins() + " Gold Coins.");
        } else {
            player.setInPenaltyBox(true);
            return new AnswerResult(true, "Question was incorrectly answered\n" + player.getName() + " was sent to the penalty box");
        }

    }
}