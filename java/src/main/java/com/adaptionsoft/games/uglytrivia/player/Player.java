package com.adaptionsoft.games.uglytrivia.player;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.event.events.PlayerMoved;
import com.adaptionsoft.games.uglytrivia.event.events.PlayerStaysInPenaltyBox;
import com.adaptionsoft.games.uglytrivia.event.GameEventPublisher;
import com.adaptionsoft.games.uglytrivia.player.state.DefaultNormalState;
import com.adaptionsoft.games.uglytrivia.player.state.DefaultPenaltyBoxState;
import com.adaptionsoft.games.uglytrivia.player.state.PlayerState;

public class Player {
    private String name;
    private int position = 0;
    private int coins = 0;
    private PlayerState state = new DefaultNormalState();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public PlayerState move(int roll, GameEventPublisher eventPublisher) {
        if (!state.canMove(roll)) {
            eventPublisher.publish(new PlayerStaysInPenaltyBox(this.name));
            return this.state;
        }

        this.state.onMove(this, eventPublisher);

        this.position = (this.position + roll) % Game.PLAYING_FIELDS;

        eventPublisher.publish(new PlayerMoved(this.name, this.position));
        return this.state;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int add) {
        this.coins += add;
    }

    public boolean isWinner() {
        return (this.coins == Game.WINNING_COINS) && !(this.state instanceof DefaultPenaltyBoxState);
    }

    public void setState(PlayerState state) {
        this.state = state;
    }
}