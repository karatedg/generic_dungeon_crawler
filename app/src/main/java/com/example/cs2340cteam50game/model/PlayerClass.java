package com.example.cs2340cteam50game.model;

import android.graphics.drawable.Drawable;
import com.example.cs2340cteam50game.view.PlayerView;
import com.example.cs2340cteam50game.viewmodel.GameScreenModel;

public class PlayerClass {

    private double screenWidth;
    private double screenHeight;
    public void setScreenWidth(double x) {
        this.screenWidth = x;
    }
    public void setScreenHeight(double y) {
        this.screenHeight = y;
    }

    //System Values:
    private double xPos;
    private double yPos;
    private final double movementSpeed;
    private MovementStrategy movementStrategy;
    private float spriteWidth;
    private float spriteHeight;
    private int healthPoints;

    //Player Selected Values:
    private String username;
    private Drawable sprite;
    private int difficultyNum;

    //In-Game:
    private GameScreenModel gameScreenModel;

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
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.movementSpeed = 10;
        this.username = "";
        this.healthPoints = 0;
        this.sprite = null;
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

    public void moveLeft() {
        movementStrategy.moveLeft();
    }

    public void moveRight() {
        movementStrategy.moveRight();
    }

    public void moveUp() {
        movementStrategy.moveUp();
    }

    public void moveDown() {
        movementStrategy.moveDown();
    }

    /** Move x by given distance
     * @param distance distance to move
     */
    public void moveX(double distance) {
        double newX = xPos + distance;
        int collisionType = gameScreenModel.checkCollisions(newX, yPos);
        if (collisionType == 2) {
            gameScreenModel.nextRoom();
        } else if (collisionType == 0) {
            if (xPos + distance < 0) {
                this.xPos = 0;
            } else if (xPos + spriteWidth + distance > screenWidth) {
                this.xPos = screenWidth - spriteWidth;
            } else {
                xPos += distance;
            }
        }
    }

    /** Move y by given distance
     * @param distance distance to move
     */
    public void moveY(double distance) {
        double newY = yPos + distance;
        int collisionType = gameScreenModel.checkCollisions(xPos, newY);
        if (gameScreenModel.checkCollisions(xPos, newY) == 2) {
            gameScreenModel.nextRoom();
        } else if (collisionType == 0) {
            if (yPos + distance < 0) {
                this.yPos = 0;
            } else if ((yPos + spriteHeight + distance) > (screenHeight - 60)) {
                this.yPos = screenHeight - spriteHeight - 60;
            } else {
                yPos += distance;
            }
        }
    }


    ///////////////////////////////////////////////////////
    //                                                   //
    //  Getters and Setters for all instance variables  //
    //                                                  //
    //////////////////////////////////////////////////////

    public void setSpriteData(PlayerView playerView) {
        this.spriteWidth = playerView.getSpriteWidth();
        this.spriteHeight = playerView.getSpriteHeight();
    }

    public void setGameScreenModel(GameScreenModel model) {
        this.gameScreenModel = model;
    }

    public void setSpriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    public void setSpriteHeight(int spriteHeight) {
        this.spriteHeight = spriteHeight;
    }

    /**
     * MovementStrategy Setter
     * @param movementStrategy movement strategy
     */
    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    /**
     * Get x position.
     * @return x-coordinate
     */
    public double getxPos() {
        return xPos;
    }

    /**
     * Set x position.
     * @param x x-coordinate
     */
    public void setxPos(double x) {
        this.xPos = x;
    }


    /**
     * Get y position.
     * @return y-coordinate
     */
    public double getyPos() {
        return yPos;
    }

    /**
     * Set y position.
     * @param yPos y-coordinate
     */
    public void setyPos(double yPos) {
        this.yPos = yPos;
    }


    /**
     * Get movementSpeed.
     * @return movementSpeed
     */
    public double getMovementSpeed() {
        return movementSpeed;
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
     * Get Sprite.
     * @return sprite drawable
     */
    public Drawable getSprite() {
        return sprite;
    }

    /**
     * Set Sprite.
     * @param sprite sprite drawable
     */
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
