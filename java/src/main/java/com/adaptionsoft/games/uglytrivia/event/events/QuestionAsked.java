package com.adaptionsoft.games.uglytrivia.event.events;

public record QuestionAsked(String category, String question) implements GameEvent {
}
