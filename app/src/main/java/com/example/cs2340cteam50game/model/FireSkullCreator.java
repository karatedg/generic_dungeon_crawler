package com.example.cs2340cteam50game.model;

import com.example.cs2340cteam50game.model.FireSkullEnemy;

public class FireSkullCreator extends EnemyCreator {

    public FireSkullCreator() {
        System.out.println("FS CREATOR MADE!");
    }
    public  Enemy createEnemy() {
        return new FireSkullEnemy();
    }
}
