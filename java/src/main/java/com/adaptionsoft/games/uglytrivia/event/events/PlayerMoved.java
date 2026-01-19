package com.adaptionsoft.games.uglytrivia.event.events;

public record PlayerMoved(String player, int position) implements GameEvent {}
