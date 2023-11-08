package com.example.cs2340cteam50game.model;

import com.example.cs2340cteam50game.model.BeastEnemy;

public class BeastCreator extends EnemyCreator {

    public BeastCreator() {
        System.out.println("BEAST CREATOR MADE!");
    }
    public  Enemy createEnemy() {
        return new BeastEnemy();
    }
}
