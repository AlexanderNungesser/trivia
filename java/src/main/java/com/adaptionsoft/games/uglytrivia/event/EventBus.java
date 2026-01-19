package com.adaptionsoft.games.uglytrivia.event;

import com.adaptionsoft.games.uglytrivia.event.events.GameEvent;

import java.util.List;

public class EventBus implements GameEventPublisher {

    private final List<GameEventSubscriber> subscribers;

    public EventBus(List<GameEventSubscriber> subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public void publish(GameEvent event) {
        subscribers.forEach(s -> s.handle(event));
    }
}

