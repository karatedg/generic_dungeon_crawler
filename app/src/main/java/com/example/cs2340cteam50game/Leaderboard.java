package com.example.cs2340cteam50game;

import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard {

    private ArrayList<Score> scores;
    private CompareScore comparatorScore;
    private CompareTime comparatorTime;

    private static Leaderboard leaderboard = null;

    private Leaderboard() {
        scores = new ArrayList<>();
        comparatorScore = new CompareScore();
        comparatorTime = new CompareTime();
    }

    public static Leaderboard getLeaderboard() {
        if (leaderboard == null) {
            synchronized (Leaderboard.class) {
                if (leaderboard == null) {
                    leaderboard = new Leaderboard();
                }
            }
        }
        return leaderboard;
    }

    public void addScore(Score s) {
        scores.add(s);
        Collections.sort(scores, comparatorScore);
    }

    public void addScore(String name, int score) {
        addScore(new Score(name, score));
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public Score getLatestScore() {
        Score latest = scores.get(0);

        for (Score s : scores) {
            latest = (comparatorTime.compare(latest, s) > 1) ? latest : s;
        }

        return latest;
    }

    public int getSize() {
        return scores.size();
    }

    public static void clear() {
        leaderboard = null;
    }
}
