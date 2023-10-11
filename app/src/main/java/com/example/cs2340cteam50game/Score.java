package com.example.cs2340cteam50game;

import java.time.LocalDateTime;

public class Score {
    private String name;
    private int score;
    private LocalDateTime timestamp;

    public Score() {
        this.name = "Unnamed Player";
        this.score = -1;
        timestamp = LocalDateTime.now();
    }

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
}
