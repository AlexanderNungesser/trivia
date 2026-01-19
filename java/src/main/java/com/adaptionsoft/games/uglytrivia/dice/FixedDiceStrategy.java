package com.adaptionsoft.games.uglytrivia.dice;

public class FixedDiceStrategy implements DiceStrategy {
    private final int value;

    public FixedDiceStrategy(int value) {
        this.value = value;
    }

    @Override
    public int roll() {
        return value;
    }
}

