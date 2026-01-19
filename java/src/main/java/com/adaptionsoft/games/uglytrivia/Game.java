package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.uglytrivia.answer.AnswerStrategy;
import com.adaptionsoft.games.uglytrivia.dice.DiceStrategy;
import com.adaptionsoft.games.uglytrivia.event.*;
import com.adaptionsoft.games.uglytrivia.event.events.*;
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
    private Player currentPlayer = null;
    private final QuestionFactory questions;
    private final AnswerStrategy answers;
    private final GameEventPublisher eventPublisher;

    public Game(DiceStrategy dice, List<Player> players, QuestionFactory questions, AnswerStrategy answers, GameEventPublisher eventPublisher) {
        if (players == null || players.size() < MIN_PLAYERS) {
            throw new IllegalArgumentException("Game needs at least two players");
        }
        this.dice = dice;
        this.players = players;
        for (Player player : this.players) {
            eventPublisher.publish(new PlayerJoined(player.getName(), players.indexOf(player) + 1));
        }
        this.questions = questions;
        this.answers = answers;
        this.eventPublisher = eventPublisher;
    }

    public boolean roll() {
        int roll = dice.roll();
        currentPlayer = getNextPlayer(currentPlayer);

        eventPublisher.publish(new PlayerChanged(currentPlayer.getName()));
        eventPublisher.publish(new PlayerRolled(roll));

        PlayerState state = currentPlayer.move(roll, eventPublisher);

        if (state instanceof DefaultPenaltyBoxState) {
            return true;
        }

        Question currentQuestion = getCurrentQuestion();

        eventPublisher.publish(new QuestionAsked(currentQuestion.category().value(), currentQuestion.text()));

        boolean answerResult = answer();

        if (answerResult) {
            eventPublisher.publish(new AnswerCorrect(currentPlayer.getName(), currentPlayer.getCoins()));
        }else {
            eventPublisher.publish(new AnswerWrong(currentPlayer.getName()));
        }

        boolean winner = currentPlayer.isWinner();
        if (winner) {
            eventPublisher.publish(new PlayerWon(currentPlayer.getName()));
        }

        return !winner;
    }

    private Question getCurrentQuestion() {
        Category category = getCurrentCategory();
        return questions.nextQuestion(category);
    }

    private Category getCurrentCategory() {
        return Category.values()[currentPlayer.getPosition() % Category.values().length];
    }

    private Player getNextPlayer(Player currentPlayer) {
        if (currentPlayer == null || players.indexOf(currentPlayer) == players.size() - 1) {
            return players.getFirst();
        } else {
            return players.get(players.indexOf(currentPlayer) + 1);
        }
    }

    public boolean answer() {
        return answers.handleAnswer(currentPlayer, getCurrentQuestion());
    }
}