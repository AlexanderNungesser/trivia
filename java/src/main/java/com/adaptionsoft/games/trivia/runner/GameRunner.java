
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.dice.Dice;
import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.dice.RandomDice;


public class GameRunner {

    public static void main(String[] args) {
		Dice dice = new RandomDice(6);
		Game aGame = new Game(dice);
		
		aGame.addPlayer("Chet");
		aGame.addPlayer("Pat");
		aGame.addPlayer("Sue");
		
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
