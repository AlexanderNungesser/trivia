package com.adaptionsoft.games.uglytrivia.event;

import com.adaptionsoft.games.uglytrivia.event.events.*;

public class ConsoleGameObserver implements GameEventSubscriber {

    @Override
    public void handle(GameEvent event) {
        if (event instanceof PlayerJoined e){
            System.out.println(e.player() + " was added");
            System.out.println("They are player number " + e.number());
        }
        if (event instanceof PlayerChanged e){
            System.out.println(e.player() + " is the current player");
        }
        if (event instanceof PlayerRolled e){
            System.out.println("They have rolled a " + e.roll());
        }
        if (event instanceof PlayerMoved e) {
            System.out.println(e.player() + "'s new location is " + e.position());
        }
        if (event instanceof PlayerLeftPenaltyBox e){
            System.out.println(e.player() + " is getting out of the penalty box");
        }
        if (event instanceof PlayerStaysInPenaltyBox e) {
            System.out.println(e.player() + " is not getting out of the penalty box");
        }
        if (event instanceof QuestionAsked e) {
            System.out.println("The category is " + e.category());
            System.out.println(e.question());
        }
        if (event instanceof AnswerCorrect e){
            System.out.println("Answer was correct!!!!");
            System.out.println(e.player() + " now has " + e.coins() + " Gold Coins.");
        }
        if (event instanceof AnswerWrong e){
            System.out.println("Question was incorrectly answered");
            System.out.println(e.player() + " was sent to the penalty box");
        }
        if (event instanceof PlayerWon e){
            System.out.println(e.player() + " has won the game!");
        }
    }
}

