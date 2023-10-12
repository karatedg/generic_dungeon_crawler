package com.example.cs2340cteam50game.model;

import java.util.Comparator;

public class CompareScore implements Comparator<Score> {
    @Override
    public int compare(Score s1, Score s2) {
        return s2.getScore() - s1.getScore();
    }
}
