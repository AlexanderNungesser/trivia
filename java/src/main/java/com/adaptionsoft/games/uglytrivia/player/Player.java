package com.adaptionsoft.games.uglytrivia.player;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.player.state.DefaultNormalState;
import com.adaptionsoft.games.uglytrivia.player.state.PlayerState;

public class Player {
    private String name;
    private int place = 0;
    private int coins = 0;
    private PlayerState state = new DefaultNormalState();

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

    public PlayerState move(int roll) {

        if (!state.canMove(roll)) {
            System.out.println(name + " is not getting out of the penalty box");
            return this.state;
        }

        this.state.onMove(this);

        this.place = (this.place + roll) % Game.PLAYING_FIELDS;
        System.out.println(this.name + "'s new location is " + this.place);

        return this.state;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int add) {
        this.coins += add;
    }

    public boolean isWinner() {
        return coins == Game.WINNING_COINS;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }
}