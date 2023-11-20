package com.example.cs2340cteam50game.model;

public class GhostCreator extends EnemyCreator {
    public  Enemy createEnemy() {
        return new GhostEnemy();
    }
}
