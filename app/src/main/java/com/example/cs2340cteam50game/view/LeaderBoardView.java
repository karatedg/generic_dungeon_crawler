package com.example.cs2340cteam50game.view;

import android.widget.TextView;

import com.example.cs2340cteam50game.model.Score;
import com.example.cs2340cteam50game.model.Leaderboard;

public class LeaderBoardView {

    public static void renderLeaderboard(Leaderboard l, TextView text, Score score) {
        String render = String.format("%10s %10s %30s\n", "NAME", "SCORE", "DATE/TIME");


        Score latest = score;
        String time = latest.getTimestamp().toString();

        render += String.format("%10s %15d %45s\n\n\n", latest.getName(), latest.getScore(),
                time.replace('T', ' ').substring(0, time.length() - 7));

        int leaderBoardSize = Leaderboard.getLeaderboard().getSize();
        if (leaderBoardSize > 5) {
            leaderBoardSize = 5;
        }


        for (int i = 0; i < leaderBoardSize; i++) {
            if (i < l.getScores().size()) {
                Score s = l.getScores().get(i);
                String sTime = s.getTimestamp().toString();
                render += String.format("%10s %15d %45s\n", s.getName(), s.getScore(),
                        sTime.replace('T', ' ').substring(0, time.length() - 7));
            } else {
                render += String.format("%10s %10s\n", "-", "-");
            }
        }
        text.setText(render);
    }
}
