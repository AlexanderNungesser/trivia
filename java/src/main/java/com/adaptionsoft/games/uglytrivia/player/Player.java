package com.adaptionsoft.games.uglytrivia.player;

import com.adaptionsoft.games.uglytrivia.Game;

public class Player {
    private String name;
    private int place = 0;
    private int coins = 0;
    private boolean inPenaltyBox = false;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlace() {
        return place;
    }

    public void move(int roll) {

        if (this.inPenaltyBox && roll % 2 == 0) {
            System.out.println(name + " is not getting out of the penalty box");
            return;
        }

        if (this.inPenaltyBox) {
            this.inPenaltyBox = false;
            System.out.println(name + " is getting out of the penalty box");
        }

        this.place = (this.place + roll) % Game.PLAYING_FIELDS;
        System.out.println(this.name + "'s new location is " + this.place);
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int add) {
        this.coins += add;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public boolean isWinner() {
        return coins == Game.WINNING_COINS;
    }
}