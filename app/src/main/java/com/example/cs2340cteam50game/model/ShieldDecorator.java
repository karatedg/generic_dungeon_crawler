package com.example.cs2340cteam50game.model;


public class ShieldDecorator extends PowerupDecorator {

    private PlayerClass player;
    //private int armorID =  R.drawable.playerWShield;
    public ShieldDecorator(Powerup decoratedPowerup) {
        super(decoratedPowerup);
    }

    @Override
    public void usePowerup() {
        //decoratedPowerup.usePowerup();
        useAbility(decoratedPowerup);
    }

    private void useAbility(Powerup decoratedPowerup) {
        System.out.println("Shield has been implemented");
        //Drawable sprite = getResources().getDrawable(spriteID);
        //player.setSprite(sprite);
    }
}
