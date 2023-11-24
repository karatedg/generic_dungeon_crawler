package com.example.cs2340cteam50game.model;

public class SpeedBoost implements MovementStrategy {
    private final PlayerClass player = PlayerClass.getPlayer();

    @Override
    public void moveUp() {
        player.moveY(-2.5 * player.getMovementSpeed());
    }

    @Override
    public void moveDown() {
        player.moveY(2.5 * player.getMovementSpeed());
    }

    @Override
    public void moveRight() {
        player.moveX(2.5 * player.getMovementSpeed());
    }

    @Override
    public void moveLeft() {
        player.moveX(-2.5 * player.getMovementSpeed());
    }


}
