package com.adaptionsoft.games.uglytrivia.event;

import com.adaptionsoft.games.uglytrivia.event.events.GameEvent;

public interface GameEventPublisher {
    void addSubscriber(GameEventSubscriber subscriber);
    void removeSubscriber(GameEventSubscriber subscriber);
    void publish(GameEvent event);
}