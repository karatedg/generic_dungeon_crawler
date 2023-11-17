package com.example.cs2340cteam50game.model;

public class SpeedBoost implements MovementStrategy {
    private final PlayerClass player = PlayerClass.getPlayer();

    @Override
    public void moveUp() {
        player.setMovementSpeed(25);
        player.moveY(player.getMovementSpeed());
    }

    @Override
    public void moveDown() {
        player.setMovementSpeed(25);
        player.moveY(-1 * player.getMovementSpeed());
    }

    @Override
    public void moveRight() {
        player.setMovementSpeed(25);
        player.moveX(player.getMovementSpeed());
    }

    @Override
    public void moveLeft() {
        player.setMovementSpeed(25);
        player.moveX(-1 * player.getMovementSpeed());
    }


}
