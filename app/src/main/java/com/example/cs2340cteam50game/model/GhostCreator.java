package com.example.cs2340cteam50game.model;

import com.example.cs2340cteam50game.model.GhostEnemy;

public class GhostCreator extends EnemyCreator {

    public GhostCreator() {
        System.out.println("GHOST CREATOR MADE!");
    }
    public  Enemy createEnemy() {
        return new GhostEnemy();
    }
}
