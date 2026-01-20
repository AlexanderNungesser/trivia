package com.adaptionsoft.games.uglytrivia.event.events;

public record PlayerGotCoins(String player, int coins) implements GameEvent {
}
