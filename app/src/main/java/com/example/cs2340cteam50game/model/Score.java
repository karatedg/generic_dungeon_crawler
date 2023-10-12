package com.example.cs2340cteam50game.model;

import java.time.LocalDateTime;

public class Score {
    private String name;
    private int score;
    private LocalDateTime timestamp;

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
        timestamp = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        String s = "Name: " +  name + "   Score: " + score;
        return s;
    }

}
