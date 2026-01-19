package com.adaptionsoft.games.uglytrivia.event.events;

public record PlayerJoined(String player, int number) implements GameEvent {}

