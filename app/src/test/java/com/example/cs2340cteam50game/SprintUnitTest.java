package com.example.cs2340cteam50game;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.example.cs2340cteam50game.model.Leaderboard;
import com.example.cs2340cteam50game.model.PlayerClass;
import com.example.cs2340cteam50game.model.Score;
import com.example.cs2340cteam50game.viewmodel.GameScreenModel;

import org.junit.Test;
import java.util.ArrayList;

public class SprintUnitTest {
    // Player Unit Tests

    // Joshua Buchsbaum
    @Test
    public void playerSingleton() {
        PlayerClass player1 = PlayerClass.getPlayer();
        player1.setUsername("Testing123");
        PlayerClass player2 = PlayerClass.getPlayer();
        assertEquals(player1, player2);
    }

    // Devan Gandhi
    @Test(expected = java.lang.IllegalArgumentException.class)
    public void playerNameEmpty() {
        PlayerClass player = PlayerClass.getPlayer();
        player.setUsername(" ");
        player.setUsername("");
    }

    // Devan Gandhi
    @Test(expected = java.lang.IllegalArgumentException.class)
    public void playerNameNull() {
        PlayerClass player = PlayerClass.getPlayer();
        player.setUsername(null);
    }

    // David Martinez
    @Test(expected = java.lang.IllegalArgumentException.class)
    public void playerNameLength() {
        PlayerClass player = PlayerClass.getPlayer();
        player.setUsername("abcdefghijklmnopqrstuvwxyz");
    }

    // David Martinez
    @Test
    public void playerHealthEasy() {
        PlayerClass player  = PlayerClass.getPlayer();
        player.setDifficultyNum(1);
        assertEquals(player.getHealthPoints(), 150);
    }

    // Noah West
    @Test
    public void playerHealthMedium() {
        PlayerClass player  = PlayerClass.getPlayer();
        player.setDifficultyNum(2);
        assertEquals(player.getHealthPoints(), 100);
    }

    // Noah West
    @Test
    public void playerHealthHard() {
        PlayerClass player  = PlayerClass.getPlayer();
        player.setDifficultyNum(3);
        assertEquals(player.getHealthPoints(), 75);
    }

    // Sathwik Toduru
    @Test
    public void leaderboardSingleton() {
        Leaderboard board1 = Leaderboard.getLeaderboard();
        Leaderboard board2 = Leaderboard.getLeaderboard();
        board1.addScore(new Score("Test", 25));
        assertEquals(board1, board2);
    }

    // Sathwik Toduru
    @Test
    public void leaderboardSorting() {
        Leaderboard.clear();
        Leaderboard board1 = Leaderboard.getLeaderboard();
        board1.addScore("1", 10);
        board1.addScore("2", 1);
        board1.addScore("3", 32);
        board1.addScore("4", 20);
        board1.addScore("5", 2);

        ArrayList<Score> scores = board1.getScores();

        ArrayList<Score> scoresOrdered = new ArrayList<Score>(5);
        scoresOrdered.add(new Score("3", 32));
        scoresOrdered.add(new Score("4", 20));
        scoresOrdered.add(new Score("1", 10));
        scoresOrdered.add(new Score("5", 2));
        scoresOrdered.add(new Score("2", 1));

        for (int i = 0; i < scores.size(); i++) {
            assertEquals(scores.get(i).getScore(), scoresOrdered.get(i).getScore());
        }
    }

    //Joshua Buchsbaum
    @Test
    public void scoreMin() {
        GameScreenModel model = new GameScreenModel();
        model.setScoreVal(-100);
        assertEquals(model.getScoreVal(), 0);
    }

}
