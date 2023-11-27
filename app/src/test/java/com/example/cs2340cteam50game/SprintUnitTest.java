package com.example.cs2340cteam50game;

import static org.junit.Assert.assertEquals;

import android.widget.TextView;

import com.example.cs2340cteam50game.model.BeastEnemy;
import com.example.cs2340cteam50game.model.ConfusionMovement;
import com.example.cs2340cteam50game.model.DefaultSpeed;
import com.example.cs2340cteam50game.model.DemonEnemy;
import com.example.cs2340cteam50game.model.Enemy;
import com.example.cs2340cteam50game.model.FireSkullEnemy;
import com.example.cs2340cteam50game.model.GhostEnemy;
import com.example.cs2340cteam50game.model.HealthPowerup;
import com.example.cs2340cteam50game.model.Leaderboard;
import com.example.cs2340cteam50game.model.PlayerClass;
import com.example.cs2340cteam50game.model.Powerup;
import com.example.cs2340cteam50game.model.Rectangle;
import com.example.cs2340cteam50game.model.Score;
import com.example.cs2340cteam50game.model.ShieldPowerup;
import com.example.cs2340cteam50game.model.SpeedBoost;
import com.example.cs2340cteam50game.model.SpeedPowerup;
import com.example.cs2340cteam50game.model.Sword;
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
//        model.startScoreTimer(text);
        player.setGameScreenModel(model);
        player.setScreenHeight(3000);
        player.setScreenWidth(3000);
        player.setSpriteHeight(10);
        player.setSpriteWidth(10);
        player.setxPos(1810);
        player.setyPos(28);

        player.moveUp();

    }

    ///////////////////////////
    // SPRINT 4 TESTS!!!!!  //
    /////////////////////////

    @Test
    public void testMoveHorizontal() {
        // Test horizontal movement to the right
        BeastEnemy beastEnemy = new BeastEnemy();
        beastEnemy.setHitBox(new Rectangle(5, 5, 5, 5));
        beastEnemy.setxPos(0.0);
        beastEnemy.setyPos(0.0);
        beastEnemy.move(5, 0);
        assertEquals(5.0, beastEnemy.getxPos(), 0);

        // Test horizontal movement to the left
        beastEnemy.move(-5, 0);
        assertEquals(0, beastEnemy.getxPos(), 0);
    }

    @Test
    public void testMoveVertical() {
        // Test vertical movement to the up
        BeastEnemy beastEnemy = new BeastEnemy();
        beastEnemy.setHitBox(new Rectangle(5, 5, 5, 5));
        beastEnemy.setxPos(0.0);
        beastEnemy.setyPos(0.0);
        beastEnemy.move(0, 5);
        assertEquals(5.0, beastEnemy.getyPos(), 0);

        // Test vertical movement to the down
        beastEnemy.move(0, -5);
        assertEquals(0, beastEnemy.getyPos(), 0);
    }

    @Test
    public void testDamageTakenEasy() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        player.setSpriteHeight(15);
        player.setSpriteWidth(15);
        player.setDifficultyNum(1);
        player.setGameScreenModel(new GameScreenModel());

        Rectangle playerHitBox = new Rectangle(0, 0,
                0 + player.getSpriteWidth(),
                0 + player.getSpriteHeight());

        BeastEnemy beastEnemy = new BeastEnemy();
        beastEnemy.setHitBox(new Rectangle(0, 0, 5, 5));

        if (playerHitBox.intersects(beastEnemy.getHitBox())) {
            player.takeDamage();
        }

        int expectedHealthAfterCollision = 140;
        assertEquals(expectedHealthAfterCollision, player.getHealthPoints(), 0);
    }

    @Test
    public void testDamageTakenMedium() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        player.setSpriteHeight(15);
        player.setSpriteWidth(15);
        player.setDifficultyNum(2);
        player.setGameScreenModel(new GameScreenModel());

        Rectangle playerHitBox = new Rectangle(0, 0,
                0 + player.getSpriteWidth(),
                0 + player.getSpriteHeight());

        BeastEnemy beastEnemy = new BeastEnemy();
        beastEnemy.setHitBox(new Rectangle(0, 0, 5, 5));

        if (playerHitBox.intersects(beastEnemy.getHitBox())) {
            player.takeDamage();
        }

        int expectedHealthAfterCollision = 85;
        assertEquals(expectedHealthAfterCollision, player.getHealthPoints(), 0);
    }

    @Test
    public void testDamageTakenHard() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        player.setSpriteHeight(15);
        player.setSpriteWidth(15);
        player.setDifficultyNum(3);
        player.setGameScreenModel(new GameScreenModel());

        Rectangle playerHitBox = new Rectangle(0, 0,
                0 + player.getSpriteWidth(),
                0 + player.getSpriteHeight());

        BeastEnemy beastEnemy = new BeastEnemy();
        beastEnemy.setHitBox(new Rectangle(0, 0, 5, 5));

        if (playerHitBox.intersects(beastEnemy.getHitBox())) {
            player.takeDamage();
        }

        int expectedHealthAfterCollision = 55;
        assertEquals(expectedHealthAfterCollision, player.getHealthPoints(), 0);
    }

    @Test
    public void testPlayerEnemyCollision() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        player.setSpriteHeight(15);
        player.setSpriteWidth(15);
        player.setDifficultyNum(3);

        Rectangle playerHitBox = new Rectangle(0, 0,
                0 + player.getSpriteWidth(),
                0 + player.getSpriteHeight());

        GhostEnemy ghostEnemy = new GhostEnemy();
        ghostEnemy.setHitBox(new Rectangle(0, 0, 5, 5));

        assertEquals(playerHitBox.intersects(ghostEnemy.getHitBox()), true);
    }

    @Test
    public void testPlayerDeath() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        player.setSpriteHeight(15);
        player.setSpriteWidth(15);
        player.setDifficultyNum(3);
        player.setGameScreenModel(new GameScreenModel());

        Rectangle playerHitBox = new Rectangle(0, 0,
                0 + player.getSpriteWidth(),
                0 + player.getSpriteHeight());

        BeastEnemy beastEnemy = new BeastEnemy();
        beastEnemy.setHitBox(new Rectangle(0, 0, 5, 5));

        player.setHealthPoints(5);

        if (playerHitBox.intersects(beastEnemy.getHitBox())) {
            player.takeDamage();
        }

        assertEquals(player.getCheckDead(), true);
    }

    @Test
    public void testEnemyCreationSet1() {
        GameScreenModel model = new GameScreenModel();
        model.createEnemySet1();

        ArrayList<Enemy> enemies = model.getCurrentEnemies();
        assertEquals(enemies.get(0) instanceof FireSkullEnemy, true);
        assertEquals(enemies.get(1) instanceof BeastEnemy, true);
    }

    @Test
    public void testEnemyCreationSet2() {
        GameScreenModel model = new GameScreenModel();
        model.createEnemySet2();

        ArrayList<Enemy> enemies = model.getCurrentEnemies();
        assertEquals(enemies.get(0) instanceof BeastEnemy, true);
        assertEquals(enemies.get(1) instanceof DemonEnemy, true);
    }

    @Test
    public void testEnemyCreationSet3() {
        GameScreenModel model = new GameScreenModel();
        model.createEnemySet3();

        ArrayList<Enemy> enemies = model.getCurrentEnemies();
        assertEquals(enemies.get(0) instanceof DemonEnemy, true);
        assertEquals(enemies.get(1) instanceof GhostEnemy, true);
    }

    ///////////////////////////
    // SPRINT 5 TESTS!!!!!  //
    /////////////////////////

    @Test
    public void testConfusionMovement() {
        //setup
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        GameScreenModel model = new GameScreenModel();
        player.setGameScreenModel(model);
        player.setSpriteWidth(5);
        player.setScreenWidth(200);
        player.setScreenHeight(500);
        player.setMovementStrategy(new ConfusionMovement());
        player.setxPos(100);
        player.setyPos(100);

        //right
        player.moveRight();
        double posX = player.getxPos();
        assertEquals(90, (int) posX);

        //left
        player.moveLeft();
        posX = player.getxPos();
        assertEquals(100, (int) posX);

        //up
        player.moveUp();
        double posY = player.getyPos();
        assertEquals(110, (int) posY);

        //down
        player.moveDown();
        posY = player.getyPos();
        assertEquals(100, (int) posY);
    }

    @Test
    public void testSpeedBoostMovement() {
        //setup
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        GameScreenModel model = new GameScreenModel();
        player.setGameScreenModel(model);
        player.setSpriteWidth(5);
        player.setScreenWidth(200);
        player.setScreenHeight(500);
        player.setMovementStrategy(new SpeedBoost());
        player.setxPos(100);
        player.setyPos(100);

        //right
        player.moveRight();
        double posX = player.getxPos();
        assertEquals(125, (int) posX);

        //left
        player.moveLeft();
        posX = player.getxPos();
        assertEquals(100, (int) posX);

        //up
        player.moveUp();
        double posY = player.getyPos();
        assertEquals(75, (int) posY);

        //down
        player.moveDown();
        posY = player.getyPos();
        assertEquals(100, (int) posY);
    }
  
    @Test
    public void testScoreLossEasy() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        player.setSpriteHeight(15);
        player.setSpriteWidth(15);
        player.setDifficultyNum(1);

        GameScreenModel model = new GameScreenModel();
        model.setScoreVal(100);

        player.setGameScreenModel(model);


        Rectangle playerHitBox = new Rectangle(0, 0,
                0 + player.getSpriteWidth(),
                0 + player.getSpriteHeight());

        BeastEnemy beastEnemy = new BeastEnemy();
        beastEnemy.setHitBox(new Rectangle(0, 0, 5, 5));

        if (playerHitBox.intersects(beastEnemy.getHitBox())) {
            player.takeDamage();
        }

        int expectedScore = 95;
        assertEquals(expectedScore, model.getScoreVal(), 0);
    }

    @Test
    public void testScoreLossMedium() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        player.setSpriteHeight(15);
        player.setSpriteWidth(15);
        player.setDifficultyNum(2);

        GameScreenModel model = new GameScreenModel();
        model.setScoreVal(100);

        player.setGameScreenModel(model);


        Rectangle playerHitBox = new Rectangle(0, 0,
                0 + player.getSpriteWidth(),
                0 + player.getSpriteHeight());

        BeastEnemy beastEnemy = new BeastEnemy();
        beastEnemy.setHitBox(new Rectangle(0, 0, 5, 5));

        if (playerHitBox.intersects(beastEnemy.getHitBox())) {
            player.takeDamage();
        }

        int expectedScore = 90;
        assertEquals(expectedScore, model.getScoreVal(), 0);
    }

    @Test
    public void testScoreLossHard() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        player.setSpriteHeight(15);
        player.setSpriteWidth(15);
        player.setDifficultyNum(3);

        GameScreenModel model = new GameScreenModel();
        model.setScoreVal(100);

        player.setGameScreenModel(model);


        Rectangle playerHitBox = new Rectangle(0, 0,
                0 + player.getSpriteWidth(),
                0 + player.getSpriteHeight());

        BeastEnemy beastEnemy = new BeastEnemy();
        beastEnemy.setHitBox(new Rectangle(0, 0, 5, 5));

        if (playerHitBox.intersects(beastEnemy.getHitBox())) {
            player.takeDamage();
        }

        int expectedScore = 85;
        assertEquals(expectedScore, model.getScoreVal(), 0);
    }
    @Test
    public void testSwordEnemyCollision() {
        Sword sword = Sword.getSword();
        sword.setSpriteHeight(15);
        sword.setSpriteWidth(15);

        Rectangle playerHitBox = new Rectangle(0, 0,
                0 + sword.getSpriteWidth(),
                0 + sword.getSpriteHeight());

        GhostEnemy ghostEnemy = new GhostEnemy();
        ghostEnemy.setHitBox(new Rectangle(0, 0, 5, 5));

        assertEquals(playerHitBox.intersects(ghostEnemy.getHitBox()), true);
    }

    @Test
    public void testSpeedPowerupCollides() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        player.setSpriteHeight(15);
        player.setSpriteWidth(15);
        GameScreenModel model = new GameScreenModel();
        player.setGameScreenModel(model);

        Rectangle playerHitBox = new Rectangle(0, 0,
                0 + player.getSpriteWidth(),
                0 + player.getSpriteHeight());

        SpeedPowerup speedPower = new SpeedPowerup();
        speedPower.setSpriteWidth((float) 15.0);
        speedPower.setSpriteHeight((float) 15.0);
        speedPower.setHitBox(new Rectangle(0, 0, 5, 5));
        assertEquals(playerHitBox.intersects((speedPower.getHitBox())), true);
    }

    @Test
    public void testHealthPowerupCollides() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        player.setSpriteHeight(15);
        player.setSpriteWidth(15);
        GameScreenModel model = new GameScreenModel();
        player.setGameScreenModel(model);

        Rectangle playerHitBox = new Rectangle(0, 0,
                0 + player.getSpriteWidth(),
                0 + player.getSpriteHeight());

        HealthPowerup heathPower = new HealthPowerup();
        heathPower.setSpriteWidth((float) 15.0);
        heathPower.setSpriteHeight((float) 15.0);
        heathPower.setHitBox(new Rectangle(0, 0, 5, 5));
        assertEquals(playerHitBox.intersects((heathPower.getHitBox())), true);
    }

    @Test
    public void testShieldPowerCollides() {
        PlayerClass.clear();
        PlayerClass player = PlayerClass.getPlayer();
        player.setSpriteHeight(15);
        player.setSpriteWidth(15);
        GameScreenModel model = new GameScreenModel();
        player.setGameScreenModel(model);

        Rectangle playerHitBox = new Rectangle(0, 0,
                0 + player.getSpriteWidth(),
                0 + player.getSpriteHeight());

        ShieldPowerup shieldPower = new ShieldPowerup();
        shieldPower.setSpriteWidth((float) 15.0);
        shieldPower.setSpriteHeight((float) 15.0);
        shieldPower.setHitBox(new Rectangle(0, 0, 5, 5));
        assertEquals(playerHitBox.intersects((shieldPower.getHitBox())), true);
    }

    @Test
    public void testPowerupCreationSet1() {
        GameScreenModel model = new GameScreenModel();
        model.createPowerUpSet1();

        ArrayList<Powerup> powerups = model.getCurrentPowerups();
        assertEquals(powerups.get(0) instanceof SpeedPowerup, true);
    }



}
