package com.example.cs2340cteam50game.model;


public class HealthDecorator extends PowerupDecorator {

    private PlayerClass player;
    private int newHealth = 100; //difficulty max health??
    public HealthDecorator(Powerup decoratedPowerup) {
        super(decoratedPowerup);
    }

    @Override
    public void usePowerup() {
        decoratedPowerup.usePowerup();
        useAbility(decoratedPowerup);
    }

    private void useAbility(Powerup decoratedPowerup){
        System.out.println("Health has been restored");
        player.setHealthPoints(newHealth);
    }
}
