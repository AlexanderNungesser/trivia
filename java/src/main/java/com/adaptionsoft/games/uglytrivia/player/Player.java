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

    public void move(int roll){
        if(this.inPenaltyBox){
            if (roll % 2 != 0) {
                this.isGettingOutOfPenaltyBox = true;

                System.out.println(this.name + " is getting out of the penalty box");
                if (this.place >= Game.PLAYING_FIELDS)
                    this.place -= Game.PLAYING_FIELDS;

                System.out.println(this.name
                        + "'s new location is "
                        + this.place);
            } else {
                System.out.println(this.name + " is not getting out of the penalty box");
                this.isGettingOutOfPenaltyBox = false;
            }
        }
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int add){
        this.coins += add;
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