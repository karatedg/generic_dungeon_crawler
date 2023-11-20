package com.example.cs2340cteam50game.model;

public class BeastCreator extends EnemyCreator {
    public  Enemy createEnemy() {
        return new BeastEnemy();
    }
}
