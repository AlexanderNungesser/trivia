package com.adaptionsoft.games.uglytrivia.event;

import com.adaptionsoft.games.uglytrivia.event.events.GameEvent;

import java.util.ArrayList;
import java.util.List;

public class EventBus implements GameEventPublisher {

    private final List<GameEventSubscriber> subscribers = new ArrayList<>();

    @Override
    public void addSubscriber(GameEventSubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(GameEventSubscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    @Override
    public void publish(GameEvent event) {
        subscribers.forEach(s -> s.handle(event));
    }
}

