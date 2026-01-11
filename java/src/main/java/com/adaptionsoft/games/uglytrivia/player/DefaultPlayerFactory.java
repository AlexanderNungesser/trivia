package com.adaptionsoft.games.uglytrivia.player;

import java.util.List;

public class DefaultPlayerFactory implements PlayerFactory {
    @Override
    public List<Player> createPlayers() {
         List<Player> players = List.of(
                new Player("Chet"),
                new Player("Pat"),
                new Player("Sue")
        );
         for (Player player : players) {
             System.out.println(player.getName() + " was added");
             System.out.println("They are player number " + (players.indexOf(player) + 1));
         }
         return players;
    }
}

