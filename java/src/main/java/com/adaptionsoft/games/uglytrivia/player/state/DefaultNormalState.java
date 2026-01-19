package com.adaptionsoft.games.uglytrivia.player.state;

import com.adaptionsoft.games.uglytrivia.event.GameEventPublisher;
import com.adaptionsoft.games.uglytrivia.player.Player;

public class DefaultNormalState implements PlayerState{

    @Override
    public boolean canMove(int roll) {
        return true;
    }

    @Override
    public void onMove(Player player, GameEventPublisher eventPublisher) {
        // no state change, only if answer was incorrect (see DefaultAnswerStrategy)
    }
}
