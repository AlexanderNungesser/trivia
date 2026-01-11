package com.adaptionsoft.games.uglytrivia.dice;

public class FixedDice implements Dice {
    private final int value;

    public FixedDice(int value) {
        this.value = value;
    }

    @Override
    public int roll() {
        return value;
    }
}

