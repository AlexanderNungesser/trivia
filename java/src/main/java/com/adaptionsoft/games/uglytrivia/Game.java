package com.adaptionsoft.games.uglytrivia;

import java.util.List;
import java.util.ArrayList;

public class Game {
    private static final int PLAYING_FIELDS = 12;
    private static final int COINS_PER_CORRECT_ANSWER = 1;
    private static final int MIN_PLAYERS = 2;
    private final List<Player> players;
    private final QuestionFactory questionFactory;
    private Player currentPlayer;

    public Game() {
        players = new ArrayList<>();
        questionFactory = new QuestionFactory();
    }

    public boolean isPlayable() {
        return players.size() >= MIN_PLAYERS;
    }

    public void addPlayer(String name) {
        Player player = new Player(name);
        players.add(player);
        if (players.size() == 1) currentPlayer = players.getFirst();
        System.out.println(name + " was added");
        System.out.println("They are player number " + players.size());
    }

    public void roll(int roll) {
        System.out.println(currentPlayer.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (roll % 2 != 0) {
                currentPlayer.setGettingOutOfPenaltyBox(true);

                System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
                processRoll(roll);
            } else {
                System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
                currentPlayer.setGettingOutOfPenaltyBox(false);
            }

        } else {
            processRoll(roll);
        }
    }

    private void processRoll(int roll) {
        currentPlayer.setPlace(currentPlayer.getPlace() + roll);
        if (currentPlayer.getPlace() >= PLAYING_FIELDS)
            currentPlayer.setPlace(currentPlayer.getPlace() - PLAYING_FIELDS);

        System.out.println(currentPlayer.getName()
                + "'s new location is "
                + currentPlayer.getPlace());
        Category currentCategory = Category.getCurrent(currentPlayer.getPlace());
        System.out.println("The category is " + currentCategory.value());
        System.out.println(questionFactory.getQuestion(currentCategory).text());
    }

    public boolean correctAnswer() {
        if (currentPlayer.isInPenaltyBox()) {
            if (currentPlayer.isGettingOutOfPenaltyBox()) {
                return answerIsCorrect();
            } else {
                currentPlayer = getNextPlayer(currentPlayer);
                return false;
            }
        } else {
            return answerIsCorrect();
        }
    }

    private Player getNextPlayer(Player currentPlayer) {
        if (players.indexOf(currentPlayer) == players.size() - 1) {
            return players.getFirst();
        } else {
            return players.get(players.indexOf(currentPlayer) + 1);
        }
    }

    private boolean answerIsCorrect() {
        System.out.println("Answer was correct!!!!");
        currentPlayer.setCoins(currentPlayer.getCoins() + COINS_PER_CORRECT_ANSWER);
        System.out.println(currentPlayer.getName()
                + " now has "
                + currentPlayer.getCoins()
                + " Gold Coins.");

        currentPlayer = getNextPlayer(currentPlayer);

        return currentPlayer.isWinner();
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.getName() + " was sent to the penalty box");
        currentPlayer.setInPenaltyBox(true);

        currentPlayer = getNextPlayer(currentPlayer);
        return false;
    }
}