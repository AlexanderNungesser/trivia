package com.adaptionsoft.games.uglytrivia.player;

import com.adaptionsoft.games.uglytrivia.Game;

public class Player {
    private String name;
    private int place = 0;
    private int coins = 0;
    private boolean inPenaltyBox = false;
    private boolean isGettingOutOfPenaltyBox = false;

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

    public void setPlace(int place) {
        this.place = place;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public boolean isGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }

    public void setGettingOutOfPenaltyBox(boolean gettingOutOfPenaltyBox) {
        this.isGettingOutOfPenaltyBox = gettingOutOfPenaltyBox;
    }

    public boolean isWinner() {
        return coins == Game.WINNING_COINS;
    }
}