package com.adaptionsoft.games.uglytrivia.answer;

import com.adaptionsoft.games.uglytrivia.player.Player;
import com.adaptionsoft.games.uglytrivia.question.Question;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ScriptedAnswerStrategy implements AnswerStrategy{

    private final Queue<Boolean> answers;

    public ScriptedAnswerStrategy(List<Boolean> answers) {
        this.answers = new LinkedList<Boolean>(answers);
    }

    @Override
    public boolean handleAnswer(Player player, Question question) {
        return answers.remove();
    }
}
