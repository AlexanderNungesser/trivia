
package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.answer.AnswerStrategy;
import com.adaptionsoft.games.uglytrivia.answer.DefaultAnswerStrategy;
import com.adaptionsoft.games.uglytrivia.dice.DiceStrategy;
import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.dice.RandomDiceStrategy;
import com.adaptionsoft.games.uglytrivia.event.ConsoleGameObserver;
import com.adaptionsoft.games.uglytrivia.event.EventBus;
import com.adaptionsoft.games.uglytrivia.event.GameEventPublisher;
import com.adaptionsoft.games.uglytrivia.event.GameEventSubscriber;
import com.adaptionsoft.games.uglytrivia.player.factory.DefaultPlayerFactory;
import com.adaptionsoft.games.uglytrivia.player.factory.PlayerFactory;
import com.adaptionsoft.games.uglytrivia.question.DefaultQuestionFactory;
import com.adaptionsoft.games.uglytrivia.question.QuestionFactory;

import java.util.List;

public class GameRunner {

    public static void main(String[] args) {
        DiceStrategy dice = new RandomDiceStrategy(6);
        PlayerFactory players = new DefaultPlayerFactory();
        QuestionFactory questions = new DefaultQuestionFactory();
        AnswerStrategy answers = new DefaultAnswerStrategy();
        GameEventSubscriber console = new ConsoleGameObserver();
        GameEventPublisher eventBus = new EventBus();
        eventBus.addSubscriber(console);

        Game game = new Game(dice, players.createPlayers(), questions, answers, eventBus);

        do {
            game.roll();
        }
        while (!game.hasWinner());
    }
}
