package com.example.cs2340cteam50game.model;

public class PowerupDecorator {

    protected Powerup decoratedPowerup;

    public PowerupDecorator(Powerup decoratedPowerup) {
        this.decoratedPowerup = decoratedPowerup;
    }

    public void usePowerup() {
        //decoratedPowerup.usePowerup();
    }
}
