package com.example.cs2340cteam50game.model;

public class DemonCreator extends EnemyCreator {

    public DemonCreator() {
        System.out.println("DEMON CREATOR MADE!");
    }
    public  Enemy createEnemy() {
        return new DemonEnemy();
    }
}
