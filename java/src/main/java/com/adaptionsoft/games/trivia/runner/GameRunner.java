
package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.answer.AnswerStrategy;
import com.adaptionsoft.games.uglytrivia.answer.DefaultAnswerStrategy;
import com.adaptionsoft.games.uglytrivia.dice.DiceStrategy;
import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.dice.RandomDiceStrategy;
import com.adaptionsoft.games.uglytrivia.player.factory.DefaultPlayerFactory;
import com.adaptionsoft.games.uglytrivia.player.factory.PlayerFactory;
import com.adaptionsoft.games.uglytrivia.question.DefaultQuestionFactory;
import com.adaptionsoft.games.uglytrivia.question.QuestionFactory;

public class GameRunner {

    public static void main(String[] args) {
		DiceStrategy dice = new RandomDiceStrategy(6);
		PlayerFactory players = new DefaultPlayerFactory();
		QuestionFactory questions = new DefaultQuestionFactory();
		AnswerStrategy answers = new DefaultAnswerStrategy();

		Game game = new Game(dice, players.createPlayers(), questions, answers);

        boolean hasWinner;
        do {
			hasWinner = !game.roll();
		} while (!hasWinner);
	}
}
