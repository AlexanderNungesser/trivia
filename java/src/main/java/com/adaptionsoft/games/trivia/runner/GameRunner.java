
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.dice.Dice;
import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.dice.RandomDice;
import com.adaptionsoft.games.uglytrivia.player.DefaultPlayerFactory;
import com.adaptionsoft.games.uglytrivia.player.PlayerFactory;
import com.adaptionsoft.games.uglytrivia.question.DefaultQuestionFactory;
import com.adaptionsoft.games.uglytrivia.question.QuestionFactory;


public class GameRunner {

    public static void main(String[] args) {
		Dice dice = new RandomDice(6);
		PlayerFactory players = new DefaultPlayerFactory();
		QuestionFactory questions = new DefaultQuestionFactory();
		Game aGame = new Game(dice, players.createPlayers(), questions);

		Random rand = new Random();

        boolean hasWinner;
        do {
			aGame.roll();
			
			if (rand.nextInt(9) == 7) {
				hasWinner = aGame.wrongAnswer();
			} else {
				hasWinner = aGame.correctAnswer();
			}
		} while (!hasWinner);
	}
}
