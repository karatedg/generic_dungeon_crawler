package com.example.cs2340cteam50game;

import android.widget.TextView;

public class LeaderBoardView {

    public static void renderLeaderboard(Leaderboard l, TextView text) {
        String render = String.format("%10s %10s\n", "NAME", "SCORE");

        Score latest = l.getLatestScore();
        render += String.format("%10s %10d\n\n", latest.getName(), latest.getScore());

        for (int i = 0; i < 5; i++) {
            if (i < l.getScores().size()) {
                Score s = l.getScores().get(i);
                render += String.format("%10s %10d\n", s.getName(), s.getScore());
            } else {
                render += String.format("%10s %10s\n", "-", "-");
            }
        }
        text.setText(render);
    }
}
