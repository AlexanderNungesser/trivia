package com.adaptionsoft.games.uglytrivia.question;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class QuestionFactory {
    private final Map<Category, List<Question>> questions = new HashMap<>();

    public QuestionFactory() {
        for (Category category : Category.values()) {
            questions.put(category, new LinkedList<>());
            for (int i = 0; i < 50; i++) {
                questions.get(category).addLast(new Question(i, category));
            }
        }
    }

    // was askQuestion()
    public Question getQuestion(Category category) {
        return questions.get(category).removeFirst();
    }
}
