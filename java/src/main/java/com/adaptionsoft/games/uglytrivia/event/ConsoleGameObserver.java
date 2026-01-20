package com.adaptionsoft.games.uglytrivia.event;

import com.adaptionsoft.games.uglytrivia.event.events.*;

public class ConsoleGameObserver implements GameEventSubscriber {

    @Override
    public void handle(GameEvent event) {
        if (event instanceof PlayerJoined e) {
            System.out.println(e.player() + " was added");
            System.out.println("They are player number " + e.number());

        } else if (event instanceof PlayerChanged e) {
            System.out.println(e.player() + " is the current player");

        } else if (event instanceof PlayerRolled e) {
            System.out.println("They have rolled a " + e.roll());

        } else if (event instanceof PlayerMoved e) {
            System.out.println(e.player() + "'s new location is " + e.position());

        } else if (event instanceof PlayerGoesToPenaltyBox e) {
            System.out.println(e.player() + " was sent to the penalty box");

        } else if (event instanceof PlayerLeftPenaltyBox e) {
            System.out.println(e.player() + " is getting out of the penalty box");

        } else if (event instanceof PlayerStaysInPenaltyBox e) {
            System.out.println(e.player() + " is not getting out of the penalty box");

        } else if (event instanceof QuestionAsked e) {
            System.out.println("The category is " + e.category());
            System.out.println(e.question());

        } else if (event instanceof AnswerCorrect e) {
            System.out.println("Answer was correct!!!!");

        } else if (event instanceof AnswerWrong e) {
            System.out.println("Question was incorrectly answered");

        } else if (event instanceof PlayerGotCoins e) {
            System.out.println(e.player() + " now has " + e.coins() + " Gold Coins.");

        } else if (event instanceof PlayerWon e) {
            System.out.println(e.player() + " has won the game!");
        }
    }
}

