package com.example.cs2340cteam50game;

import static org.junit.Assert.assertEquals;

public class SprintUnitTest {
    // Player Unit Tests
    @org.junit.Test
    public void playerSingleton() {
        PlayerClass player1 = PlayerClass.getPlayer();
        player1.setUsername("Testing123");
        PlayerClass player2 = PlayerClass.getPlayer();
        assertEquals(player1, player2);
    }

    @org.junit.Test(expected = java.lang.IllegalArgumentException.class)
    public void playerNameEmpty() {
        PlayerClass player = PlayerClass.getPlayer();
        player.setUsername(" ");
        player.setUsername("");
    }

    @org.junit.Test(expected = java.lang.IllegalArgumentException.class)
    public void playerNameNull() {
        PlayerClass player = PlayerClass.getPlayer();
        player.setUsername(null);
    }

    @org.junit.Test
    public void playerHealthEasy() {
        PlayerClass player  = PlayerClass.getPlayer();
        player.setDifficultyNum(1);
        assertEquals(player.getHealthPoints(), 150);
    }

    @org.junit.Test
    public void playerHealthMedium() {
        PlayerClass player  = PlayerClass.getPlayer();
        player.setDifficultyNum(2);
        assertEquals(player.getHealthPoints(), 100);
    }
    @org.junit.Test
    public void playerHealthHard() {
        PlayerClass player  = PlayerClass.getPlayer();
        player.setDifficultyNum(3);
        assertEquals(player.getHealthPoints(), 75);
    }
}
