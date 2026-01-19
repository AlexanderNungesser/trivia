package com.adaptionsoft.games.uglytrivia.event;

import com.adaptionsoft.games.uglytrivia.event.events.GameEvent;

public interface GameEventSubscriber {
    void handle(GameEvent event);
}
