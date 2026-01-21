package com.adaptionsoft.games.uglytrivia.answer;

import com.adaptionsoft.games.uglytrivia.player.Player;
import com.adaptionsoft.games.uglytrivia.question.Question;

public class SameAnswerStrategy implements AnswerStrategy{

    private final boolean value;

    public SameAnswerStrategy(boolean value){
        this.value = value;
    }

    @Override
    public boolean handleAnswer(Player player, Question question) {
        return this.value;
    }
}
