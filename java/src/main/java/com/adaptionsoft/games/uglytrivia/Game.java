package com.adaptionsoft.games.uglytrivia;

import java.util.List;
import java.util.ArrayList;

public class Game {
    List<Player> players = new ArrayList<>();
    QuestionFactory questionFactory;
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        questionFactory = new QuestionFactory();
    }

    public boolean isPlayable() {
        return players.size() >= 2;
    }

    public void addPlayer(String name) {
        players.add(new Player(name));

        System.out.println(name + " was added");
        System.out.println("They are player number " + players.size());
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer).getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (players.get(currentPlayer).isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(players.get(currentPlayer).getName() + " is getting out of the penalty box");
                extractedRoll(roll);
            } else {
                System.out.println(players.get(currentPlayer).getName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {
            extractedRoll(roll);
        }
    }

    private void extractedRoll(int roll) {
        players.get(currentPlayer).setPlace(players.get(currentPlayer).getPlace() + roll);
        if (players.get(currentPlayer).getPlace() > 11)
            players.get(currentPlayer).setPlace(players.get(currentPlayer).getPlace() - 12);

        System.out.println(players.get(currentPlayer).getName()
                + "'s new location is "
                + players.get(currentPlayer).getPlace());
        Category currentCategory = Category.getCurrent(players.get(currentPlayer).getPlace());
        System.out.println("The category is " + currentCategory.value());
        System.out.println(questionFactory.getQuestion(currentCategory).text());
    }

    public boolean wasCorrectlyAnswered() {
        if (players.get(currentPlayer).isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                return extractedWasCorrectlyAnswered();
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }
        } else {
            return extractedWasCorrectlyAnswered();
        }
    }

    private boolean extractedWasCorrectlyAnswered() {
        System.out.println("Answer was correct!!!!");
        players.get(currentPlayer).setCoins(players.get(currentPlayer).getCoins() + 1);
        System.out.println(players.get(currentPlayer).getName()
                + " now has "
                + players.get(currentPlayer).getCoins()
                + " Gold Coins.");

        boolean winner = didPlayerWin();
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;

        return winner;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer).getName() + " was sent to the penalty box");
        players.get(currentPlayer).setInPenaltyBox(true);

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        return !(players.get(currentPlayer).getCoins() == 6);
    }
}
