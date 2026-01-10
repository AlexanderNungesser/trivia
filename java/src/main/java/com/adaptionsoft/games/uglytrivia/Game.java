package com.adaptionsoft.games.uglytrivia;

import java.util.List;
import java.util.ArrayList;

public class Game {
    List<Player> players = new ArrayList<>();
    QuestionFactory questionFactory;
    Player currentPlayer;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        questionFactory = new QuestionFactory();
    }

    public boolean isPlayable() {
        return players.size() >= 2;
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
                isGettingOutOfPenaltyBox = true;

                System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
                extractedRoll(roll);
            } else {
                System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {
            extractedRoll(roll);
        }
    }

    private void extractedRoll(int roll) {
        currentPlayer.setPlace(currentPlayer.getPlace() + roll);
        if (currentPlayer.getPlace() > 11)
            currentPlayer.setPlace(currentPlayer.getPlace() - 12);

        System.out.println(currentPlayer.getName()
                + "'s new location is "
                + currentPlayer.getPlace());
        Category currentCategory = Category.getCurrent(currentPlayer.getPlace());
        System.out.println("The category is " + currentCategory.value());
        System.out.println(questionFactory.getQuestion(currentCategory).text());
    }

    public boolean wasCorrectlyAnswered() {
        if (currentPlayer.isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                return extractedWasCorrectlyAnswered();
            } else {
                currentPlayer = getNextPlayer(currentPlayer);
                return true;
            }
        } else {
            return extractedWasCorrectlyAnswered();
        }
    }

    private Player getNextPlayer(Player currentPlayer) {
        if (players.indexOf(currentPlayer) == players.size() - 1) {
            return players.getFirst();
        } else {
            return players.get(players.indexOf(currentPlayer) + 1);
        }
    }

    private boolean extractedWasCorrectlyAnswered() {
        System.out.println("Answer was correct!!!!");
        currentPlayer.setCoins(currentPlayer.getCoins() + 1);
        System.out.println(currentPlayer.getName()
                + " now has "
                + currentPlayer.getCoins()
                + " Gold Coins.");

        boolean winner = didPlayerWin();
        currentPlayer = getNextPlayer(currentPlayer);

        return winner;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.getName() + " was sent to the penalty box");
        currentPlayer.setInPenaltyBox(true);

        currentPlayer = getNextPlayer(currentPlayer);
        return true;
    }


    private boolean didPlayerWin() {
        return !(currentPlayer.getCoins() == 6);
    }
}
