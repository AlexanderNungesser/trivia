package com.adaptionsoft.games.uglytrivia.event.events;

public record AnswerCorrect(String player, int coins) implements GameEvent {
}
