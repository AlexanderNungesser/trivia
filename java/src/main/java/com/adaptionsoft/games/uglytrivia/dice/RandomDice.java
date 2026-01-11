package com.adaptionsoft.games.uglytrivia.dice;

import java.util.Random;

public class RandomDice implements Dice {
    private final int sides;
    private final Random random = new Random();

    public RandomDice(int sides) {
        this.sides = sides;
    }

    @Override
    public int roll() {
        return random.nextInt(sides) + 1;
    }
}
