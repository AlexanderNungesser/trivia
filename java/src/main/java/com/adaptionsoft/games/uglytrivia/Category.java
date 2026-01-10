package com.adaptionsoft.games.uglytrivia;

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

    // was currentCategory()
    public static Category getCurrent(int place) {
        return Category.values()[place % Category.values().length];
    }
}
