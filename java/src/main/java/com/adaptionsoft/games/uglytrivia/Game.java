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
        this.eventPublisher = eventPublisher;
        if (players == null || players.size() < MIN_PLAYERS) {
            throw new IllegalArgumentException("Game needs at least two players");
        }
        this.dice = dice;
        this.players = players;
        for (Player player : this.players) {
            this.eventPublisher.publish(new PlayerJoined(player.getName(), players.indexOf(player) + 1));
        }
        this.questions = questions;
        this.answers = answers;
    }

    public void roll() {
        this.currentPlayer = getNextPlayer(this.currentPlayer);

        this.eventPublisher.publish(new PlayerChanged(this.currentPlayer.getName()));

        int roll = this.dice.roll();

        this.eventPublisher.publish(new PlayerRolled(roll));

        PlayerState state = this.currentPlayer.move(roll, this.eventPublisher);

        if (state instanceof DefaultPenaltyBoxState) {
            return;
        }

        Question currentQuestion = getCurrentQuestion();

        this.eventPublisher.publish(new QuestionAsked(currentQuestion.category().value(), currentQuestion.text()));

        boolean answerResult = answer();

        if (answerResult) {
            this.eventPublisher.publish(new AnswerCorrect(this.currentPlayer.getName()));
            this.eventPublisher.publish(new PlayerGotCoins(this.currentPlayer.getName(), this.currentPlayer.getCoins()));
        }else {
            this.eventPublisher.publish(new AnswerWrong(this.currentPlayer.getName()));
            this.eventPublisher.publish(new PlayerGoesToPenaltyBox(this.currentPlayer.getName()));
        }
    }

    public boolean hasWinner() {
        if (this.currentPlayer == null) {
            return false;
        }
        boolean winner = this.currentPlayer.isWinner();
        if (winner) {
            this.eventPublisher.publish(new PlayerWon(this.currentPlayer.getName()));
        }
        return winner;
    }

    private Question getCurrentQuestion() {
        Category category = getCurrentCategory();
        return this.questions.nextQuestion(category);
    }

    private Category getCurrentCategory() {
        return Category.values()[this.currentPlayer.getPosition() % Category.values().length];
    }

    private Player getNextPlayer(Player currentPlayer) {
        if (currentPlayer == null || this.players.indexOf(currentPlayer) == this.players.size() - 1) {
            return this.players.getFirst();
        } else {
            return this.players.get(this.players.indexOf(currentPlayer) + 1);
        }
    }

    private boolean answer() {
        return this.answers.handleAnswer(this.currentPlayer, getCurrentQuestion());
    }
}