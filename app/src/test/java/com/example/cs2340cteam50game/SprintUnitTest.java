package com.example.cs2340cteam50game;

import static org.junit.Assert.assertEquals;

import android.widget.TextView;

import com.example.cs2340cteam50game.model.DefaultSpeed;
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
        PlayerClass player = PlayerClass.getPlayer();
        player.setDifficultyNum(1);
        assertEquals(player.getHealthPoints(), 150);
    }

    // Noah West
    @Test
    public void playerHealthMedium() {
        PlayerClass player = PlayerClass.getPlayer();
        player.setDifficultyNum(2);
        assertEquals(player.getHealthPoints(), 100);
    }

    // Noah West
    @Test
    public void playerHealthHard() {
        PlayerClass player = PlayerClass.getPlayer();
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

        ArrayList<Score> scoresOrdered = new ArrayList<>(5);
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

    ///////////////////////////
    // SPRINT 2 TESTS!!!!!  //
    /////////////////////////

    @Test
    public void playerMoveLeftOutOfBounds() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        player.setSpriteWidth(10);
        player.setSpriteHeight(10);
        GameScreenModel model = new GameScreenModel();
        player.setGameScreenModel(model);
        model.setCurrentWallSet(0);
        player.setMovementStrategy(new DefaultSpeed());
        player.setxPos(0);
        player.setyPos(0);

        player.moveLeft();

        assertEquals(0, (int) player.getxPos());

    }

    @Test
    public void playerMoveRightOutOfBounds() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        player.setSpriteWidth(5);
        player.setScreenWidth(5);
        GameScreenModel model = new GameScreenModel();
        player.setGameScreenModel(model);
        player.setMovementStrategy(new DefaultSpeed());
        player.setxPos(0.0);
        player.setyPos(0.0);

        player.moveRight();

        double pos2 = player.getyPos();

        assertEquals(0, (int) pos2);
    }

    @Test
    public void playerMoveUpOutOfBounds() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        player.setSpriteWidth(10);
        player.setSpriteHeight(10);
        GameScreenModel model = new GameScreenModel();
        player.setGameScreenModel(model);
        model.setCurrentWallSet(0);
        player.setMovementStrategy(new DefaultSpeed());
        player.setxPos(0);
        player.setyPos(0);

        player.moveUp();

        assertEquals(0, (int) player.getyPos());
    }

    @Test
    public void playerMoveDownOutOfBounds() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        player.setSpriteHeight(5);
        player.setScreenHeight(5);
        GameScreenModel model = new GameScreenModel();
        player.setGameScreenModel(model);
        player.setMovementStrategy(new DefaultSpeed());
        player.setxPos(0.0);
        player.setyPos(0.0);

        player.moveDown();

        double pos2 = player.getyPos();

        assertEquals(-60, (int) pos2);
    }

    @Test
    public void playerMoveLeft() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        GameScreenModel model = new GameScreenModel();
        player.setGameScreenModel(model);
        player.setSpriteWidth(5);
        player.setScreenWidth(200);
        player.setMovementStrategy(new DefaultSpeed());
        player.setxPos(100);
        System.out.println(player.getxPos());
        player.setyPos(100);
        player.moveLeft();
        System.out.println(player.getxPos());
        double pos2 = player.getxPos();

        assertEquals(90, (int) pos2);
    }

    @Test
    public void playerMoveRight() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        GameScreenModel model = new GameScreenModel();
        player.setGameScreenModel(model);
        player.setSpriteWidth(5);
        player.setScreenWidth(200);
        player.setMovementStrategy(new DefaultSpeed());
        player.setxPos(100);
        System.out.println(player.getxPos());
        player.setyPos(100);
        player.moveRight();
        System.out.println(player.getxPos());
        double pos2 = player.getxPos();

        assertEquals(110, (int) pos2);
    }

    @Test
    public void playerMoveUp() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        GameScreenModel model = new GameScreenModel();
        player.setGameScreenModel(model);
        player.setSpriteHeight(30);
        player.setScreenHeight(200);
        player.setSpriteWidth(5);
        player.setScreenWidth(200);
        player.setMovementStrategy(new DefaultSpeed());
        player.setxPos(100);
        System.out.println(player.getyPos());
        player.setyPos(100);
        player.moveUp();
        System.out.println(player.getyPos());
        double pos2 = player.getyPos();

        assertEquals(90, (int) pos2);
    }

    @Test
    public void playerMoveDown(){
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        GameScreenModel model = new GameScreenModel();
        player.setGameScreenModel(model);
        player.setSpriteHeight(30);
        player.setScreenHeight(200);
        player.setSpriteWidth(5);
        player.setScreenWidth(200);
        player.setMovementStrategy(new DefaultSpeed());
        player.setyPos(100);
        System.out.println(player.getyPos());
        player.setxPos(100);
        player.moveDown();
        System.out.println(player.getyPos());
        double pos2 = player.getyPos();

        assertEquals(110, (int) pos2);
    }

    @Test(expected = java.lang.NullPointerException.class)
    public void playerMoveNextRoom() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        player.setMovementStrategy(new DefaultSpeed());
        GameScreenModel model = new GameScreenModel();
        player.setGameScreenModel(model);
        player.setScreenHeight(3000);
        player.setScreenWidth(3000);
        player.setSpriteHeight(10);
        player.setSpriteWidth(10);
        player.setxPos(2146);
        player.setyPos(420);

        player.moveRight();
        player.moveRight();

        System.out.println(player.getxPos());
        System.out.println(player.getyPos());
        System.out.println("Room: " + model.getCurrentRoom());
    }

    @Test(expected = RuntimeException.class)
    public void playerMoveEndGame() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        player.setMovementStrategy(new DefaultSpeed());
        GameScreenModel model = new GameScreenModel();
        model.setCurrentWallSet(2);
        TextView text = new TextView(null);
        model.startScoreTimer(text);
        player.setGameScreenModel(model);
        player.setScreenHeight(3000);
        player.setScreenWidth(3000);
        player.setSpriteHeight(10);
        player.setSpriteWidth(10);
        player.setxPos(1810);
        player.setyPos(28);

        player.moveUp();

    }


}