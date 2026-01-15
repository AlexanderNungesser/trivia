package com.adaptionsoft.games.uglytrivia.player.factory;

import com.adaptionsoft.games.uglytrivia.player.Player;

import java.util.List;

public interface PlayerFactory {
    List<Player> createPlayers();
}
