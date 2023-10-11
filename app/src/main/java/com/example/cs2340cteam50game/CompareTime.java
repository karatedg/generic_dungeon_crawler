package com.example.cs2340cteam50game;

import android.os.Build;

import java.util.Comparator;

public class CompareTime implements Comparator<Score> {
    @Override
    public int compare(Score s1, Score s2) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return s1.getTimestamp().compareTo(s2.getTimestamp());
        }
        return 0;
    }
}
