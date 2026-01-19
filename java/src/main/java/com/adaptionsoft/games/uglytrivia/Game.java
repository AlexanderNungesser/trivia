package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.uglytrivia.answer.AnswerResult;
import com.adaptionsoft.games.uglytrivia.answer.AnswerStrategy;
import com.adaptionsoft.games.uglytrivia.dice.DiceStrategy;
import com.adaptionsoft.games.uglytrivia.player.Player;
import com.adaptionsoft.games.uglytrivia.player.state.DefaultPenaltyBoxState;
import com.adaptionsoft.games.uglytrivia.player.state.PlayerState;
import com.adaptionsoft.games.uglytrivia.question.Category;
import com.adaptionsoft.games.uglytrivia.question.Question;
import com.adaptionsoft.games.uglytrivia.question.QuestionFactory;

import java.util.List;

public class Game {
    public static final int WINNING_COINS = 6;
    public static final int PLAYING_FIELDS = 12;

    private static final int MIN_PLAYERS = 2;

    private final DiceStrategy dice;
    private final List<Player> players;
    private Player currentPlayer;
    private final QuestionFactory questions;
    private final AnswerStrategy answers;

    public Game(DiceStrategy dice, List<Player> players, QuestionFactory questions, AnswerStrategy answers) {
        if (players == null || players.size() < MIN_PLAYERS) {
            throw new IllegalArgumentException("Game needs at least two players");
        }
        this.dice = dice;
        this.players = players;
        this.currentPlayer = players.getFirst();
        this.questions = questions;
        this.answers = answers;
    }

    public boolean roll() {
        int roll = dice.roll();
        currentPlayer = getNextPlayer(currentPlayer);

        System.out.println(currentPlayer.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        PlayerState state = currentPlayer.move(roll);

        if (state instanceof DefaultPenaltyBoxState){
            return true;
        }

        Question currentQuestion = getCurrentQuestion();

        System.out.println("The category is " + currentQuestion.category().value());
        System.out.println(currentQuestion.text());

        AnswerResult result = answer();

        System.out.println(result.message());
        return result.gameContinues();
    }

    private Question getCurrentQuestion() {
        Category category = getCurrentCategory();
        return questions.nextQuestion(category);
    }

    private Category getCurrentCategory() {
        return Category.values()[currentPlayer.getPlace() % Category.values().length];
    }

    private Player getNextPlayer(Player currentPlayer) {
        if (players.indexOf(currentPlayer) == players.size() - 1) {
            return players.getFirst();
        } else {
            return players.get(players.indexOf(currentPlayer) + 1);
        }
    }

    public AnswerResult answer() {
        return answers.handleAnswer(currentPlayer, getCurrentQuestion());
    }
}