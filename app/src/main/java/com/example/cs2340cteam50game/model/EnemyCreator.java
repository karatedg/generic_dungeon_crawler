package com.example.cs2340cteam50game.model;

public abstract class EnemyCreator {
    abstract Enemy createEnemy();

    public void render() {
        Enemy enemy = createEnemy();
        enemy.movementCycle();
        enemy.onHit();
        enemy.die();
    }
}
