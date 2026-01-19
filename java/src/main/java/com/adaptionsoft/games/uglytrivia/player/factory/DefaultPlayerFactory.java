package com.adaptionsoft.games.uglytrivia.player.factory;

import com.adaptionsoft.games.uglytrivia.player.Player;

import java.util.List;

public class DefaultPlayerFactory implements PlayerFactory {
    
    @Override
    public List<Player> createPlayers() {
        return List.of(
                new Player("Chet"),
                new Player("Pat"),
                new Player("Sue")
        );
    }
}

