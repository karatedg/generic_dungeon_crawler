package com.example.cs2340cteam50game.model;

public class HealthPowerup implements Powerup {
    @Override
    public void usePowerup() {
        System.out.println("Health Powerup(Medkit) has been picked up.");
    }
}