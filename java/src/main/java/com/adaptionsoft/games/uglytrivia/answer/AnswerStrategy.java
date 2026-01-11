package com.adaptionsoft.games.uglytrivia.answer;

import com.adaptionsoft.games.uglytrivia.player.Player;
import com.adaptionsoft.games.uglytrivia.question.Question;

public interface AnswerStrategy {
    AnswerResult handleAnswer(Player player, Question question);
}
