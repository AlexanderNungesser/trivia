package com.adaptionsoft.games.uglytrivia.dice;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ScriptedDice implements Dice {
    private final Queue<Integer> rolls;

    public ScriptedDice(List<Integer> rolls) {
        this.rolls = new LinkedList<>(rolls);
    }

    @Override
    public int roll() {
        return rolls.remove();
    }
}

