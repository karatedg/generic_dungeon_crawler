package com.example.cs2340cteam50game;

import android.widget.TextView;

public class LeaderBoardView {

    public static void renderLeaderboard(Leaderboard l, TextView text, Score score) {
        String render = String.format("%10s %10s\n", "NAME", "SCORE");

        Score latest = score;
        render += String.format("%10s %10d\n\n", latest.getName(), latest.getScore());

        int leaderBoardSize = Leaderboard.getLeaderboard().getSize();
        if(leaderBoardSize > 5) {
            leaderBoardSize = 5;
        }

        for (int i = 0; i < leaderBoardSize; i++) {
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
