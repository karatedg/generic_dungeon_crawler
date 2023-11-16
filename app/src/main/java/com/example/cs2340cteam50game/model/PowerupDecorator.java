package com.example.cs2340cteam50game.model;

public class PowerupDecorator implements Powerup{

    protected Powerup decoratedPowerup;

    public PowerupDecorator(Powerup decoratedPowerup){
        this.decoratedPowerup = decoratedPowerup;
    }
    @Override
    public void usePowerup() {
        decoratedPowerup.usePowerup();
    }
}
