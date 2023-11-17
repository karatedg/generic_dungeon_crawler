package com.example.cs2340cteam50game.model;


public class SpeedDecorator extends PowerupDecorator {

    private PlayerClass player;
    private SpeedBoost speedBoost;
    public SpeedDecorator(Powerup decoratedPowerup) {
        super(decoratedPowerup);
    }

    @Override
    public void usePowerup() {
        //decoratedPowerup.usePowerup();
        setAbility(decoratedPowerup);
    }

    private void setAbility(Powerup decoratedPowerup){
        System.out.println("Speed has been increased");
        player.setMovementStrategy(speedBoost);
    }
}
