package com.adaptionsoft.games.uglytrivia.player.state;

import com.adaptionsoft.games.uglytrivia.event.GameEventPublisher;
import com.adaptionsoft.games.uglytrivia.event.events.PlayerLeftPenaltyBox;
import com.adaptionsoft.games.uglytrivia.player.Player;

public class DefaultPenaltyBoxState implements PlayerState {
    public boolean canMove(int roll) {
        return roll % 2 != 0;
    }

    public void onMove(Player player, GameEventPublisher eventPublisher) {
        player.setState(new DefaultNormalState());
        eventPublisher.publish(new PlayerLeftPenaltyBox(player.getName()));
    }
}

