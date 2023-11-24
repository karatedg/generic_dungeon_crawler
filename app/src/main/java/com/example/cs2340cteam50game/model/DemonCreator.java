package com.example.cs2340cteam50game.model;

public class DemonCreator extends EnemyCreator {
    public  Enemy createEnemy() {
        return new DemonEnemy();
    }
}
