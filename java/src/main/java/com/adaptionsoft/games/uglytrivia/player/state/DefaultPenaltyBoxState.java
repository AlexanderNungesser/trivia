package com.adaptionsoft.games.uglytrivia.player.state;

import com.adaptionsoft.games.uglytrivia.player.Player;

public class DefaultPenaltyBoxState implements PlayerState {
    public boolean canMove(int roll) {
        return roll % 2 != 0;
    }

    public void onMove(Player player) {
        player.setState(new DefaultNormalState());
        System.out.println(player.getName() + " is getting out of the penalty box");
    }
}

