package com.example.cs2340cteam50game.model;

public class FireSkullCreator extends EnemyCreator {
    public  Enemy createEnemy() {
        return new FireSkullEnemy();
    }
}
