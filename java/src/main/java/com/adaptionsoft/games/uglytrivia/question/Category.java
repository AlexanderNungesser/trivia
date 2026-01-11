package com.adaptionsoft.games.uglytrivia.question;

public enum Category {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
