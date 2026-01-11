package com.adaptionsoft.games.uglytrivia.question;

public record Question(int number, Category category, String text) {
    public Question(int number, Category category) {
        this(number, category, category.value() + " Question " + number);
    }
}
