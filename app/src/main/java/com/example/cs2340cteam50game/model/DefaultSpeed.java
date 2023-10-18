package com.example.cs2340cteam50game.model;

public class DefaultSpeed implements MovementStrategy {
    private final PlayerClass player = PlayerClass.getPlayer();

    @Override
    public void moveUp() {
        player.moveY(player.getMovementSpeed());
    }

    @Override
    public void moveDown() {
        player.moveY(-1 * player.getMovementSpeed());
    }

    @Override
    public void moveRight() {
        player.moveX(player.getMovementSpeed());
    }

    @Override
    public void moveLeft() {
        player.moveX(-1 * player.getMovementSpeed());
    }
}
