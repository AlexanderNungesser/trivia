package com.adaptionsoft.games.uglytrivia.player.state;

import com.adaptionsoft.games.uglytrivia.player.Player;

public interface PlayerState {
    boolean canMove(int roll);
    void onMove(Player player);
}

