package com.example.cs2340cteam50game.model;

public class NoSpeed implements MovementStrategy {
    private final PlayerClass player = PlayerClass.getPlayer();

    @Override
    public void moveUp() {
        player.moveY(0);
    }

    @Override
    public void moveDown() {
        player.moveY(0);
    }

    @Override
    public void moveRight() {
        player.moveX(0);
    }

    @Override
    public void moveLeft() {
        player.moveX(0);
    }
}
