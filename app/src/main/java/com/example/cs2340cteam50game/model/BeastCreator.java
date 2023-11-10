package com.example.cs2340cteam50game.model;

public class BeastCreator extends EnemyCreator {

    public BeastCreator() {
        System.out.println("BEAST CREATOR MADE!");
    }
    public  Enemy createEnemy() {
        return new BeastEnemy();
    }
}
