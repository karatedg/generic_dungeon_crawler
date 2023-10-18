package com.example.cs2340cteam50game.model;


import android.graphics.drawable.Drawable;


public class PlayerClass {

    //Default Values:
    private double x;
    private double y;
    private double movementSpeed;

    //Player Selected Values:
    private String username;
    private Drawable sprite;

    private int spriteNum;
    private int difficultyNum;
    private int healthPoints;

    //private Sprite playerSprite;




    //In-Game:

    /*
    Will be properly implemented once we make the parent class for inventoryItems!

    private ArrayList<> inventory = new ArrayList<>();
     */

    //Player Instance
    private static volatile PlayerClass playerInstance;

    /**
     * Private player constructor.
     * Instantiates with default values that can be changed afterwards
     */
    private PlayerClass() {
        this.x = 0.0;
        this.y = 0.0;
        this.movementSpeed = 5.0;
        this.username = "";
        this.healthPoints = 0;
        this.sprite = null;
        this.spriteNum = 1;
        this.difficultyNum = 1;

    }

    /**
     * Checks for existence of player, and either returns existing or instantiates a new player.
     * @return Player
     */
    public static PlayerClass getPlayer() {
        if (playerInstance == null) {
            synchronized (PlayerClass.class) {
                if (playerInstance == null) {
                    playerInstance = new PlayerClass();
                }
            }
        }
        return playerInstance;
    }


    //Getters and Setters for all instance variables

    /**
     * Get x position.
     * @return x-coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Set x position.
     * @param x x-coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    public void moveX(double distance) {
        //TODO: Ensure that the player cannot move out of the bounds of the map
        this.x = x + distance;
    }

    /**
     * Get y position.
     * @return y-coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Set y position.
     * @param y y-coordinate
     */
    public void setY(double y) {
        this.y = y;
    }

    public void moveY(double distance) {
        //TODO: Ensure that the player cannot move out of the bounds of the map
        this.y = y + distance;
    }

    /**
     * Get movementSpeed.
     * @return movementSpeed
     */
    public double getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * Set movementSpeed.
     * @param movementSpeed movementSpeed
     */
    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    /**
     * Get username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username.
     * @param username username
     */
    public void setUsername(String username) {
        if (username == null || username.equals("")) {
            throw new IllegalArgumentException("Username cannot be empty or null");
        } else if (username.length() > 25) {
            throw new IllegalArgumentException("Username is too long!");
        }
        this.username = username;
    }

    /**
     * Get HP.
     * @return HP
     */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * Set HP.
     * @param healthPoints HP
     */
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    /**
     * Get spriteNum.
     * @return spriteNum
     */
    public int getSpriteNum() {
        return spriteNum;
    }

    /**
     * Set spriteNum.
     * @param spriteNum spriteNum: (1 = Red, 2 = Blue, 3 = Green)
     */
    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    public Drawable getSprite() {
        return sprite;
    }

    public void setSprite(Drawable sprite) {
        this.sprite = sprite;
    }

    /**
     * Get difficultyNum.
     * @return difficultyNum
     */
    public int getDifficultyNum() {
        return difficultyNum;
    }

    /**
     * Set difficultyNum.
     * @param difficultyNum difficultyNum: (1 = Easy, 2 = Medium, 3 = Hard)
     */
    public void setDifficultyNum(int difficultyNum) {
        this.difficultyNum = difficultyNum;
        if (difficultyNum == 1) {
            setHealthPoints(150);
        } else if (difficultyNum == 2) {
            setHealthPoints(100);
        } else if (difficultyNum == 3) {
            setHealthPoints(75);
        }
    }

    /**
     * Deletes the current player instance.
     */
    public static void clear() {
        playerInstance = null;
    }

    /**
     * Checks whether or not a player has already been created.
     * @return true if created, false if not
     */
    public static boolean playerExists() {
        return (playerInstance != null);
    }

}
