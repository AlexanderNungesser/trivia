package com.adaptionsoft.games.uglytrivia.dice;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ScriptedDiceStrategy implements DiceStrategy {
    private final Queue<Integer> rolls;

    public ScriptedDiceStrategy(List<Integer> rolls) {
        this.rolls = new LinkedList<>(rolls);
    }

    @Override
    public int roll() {
        return rolls.remove();
    }
}

