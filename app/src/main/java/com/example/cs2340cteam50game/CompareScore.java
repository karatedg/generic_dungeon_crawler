package com.example.cs2340cteam50game;

import java.util.Comparator;

public class CompareScore implements Comparator<Score> {
    @Override
    public int compare(Score s1, Score s2) {
        return s1.getScore() - s2.getScore();
    }
}
