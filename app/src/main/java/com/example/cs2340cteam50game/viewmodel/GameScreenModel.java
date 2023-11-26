package com.example.cs2340cteam50game.viewmodel;

import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.BeastCreator;
import com.example.cs2340cteam50game.model.BeastEnemy;
import com.example.cs2340cteam50game.model.ConfusionMovement;
import com.example.cs2340cteam50game.model.DefaultSpeed;
import com.example.cs2340cteam50game.model.DemonCreator;
import com.example.cs2340cteam50game.model.DemonEnemy;
import com.example.cs2340cteam50game.model.Enemy;
import com.example.cs2340cteam50game.model.FireSkullCreator;
import com.example.cs2340cteam50game.model.FireSkullEnemy;
import com.example.cs2340cteam50game.model.GhostCreator;
import com.example.cs2340cteam50game.model.GhostEnemy;
import com.example.cs2340cteam50game.model.HealthPowerup;
import com.example.cs2340cteam50game.model.NoSpeed;
import com.example.cs2340cteam50game.model.PlayerClass;

import com.example.cs2340cteam50game.model.Powerup;
import com.example.cs2340cteam50game.model.Rectangle;

import com.example.cs2340cteam50game.model.Sword;

import com.example.cs2340cteam50game.model.ShieldPowerup;
import com.example.cs2340cteam50game.model.SpeedBoost;
import com.example.cs2340cteam50game.model.SpeedPowerup;

import com.example.cs2340cteam50game.view.BeastView;
import com.example.cs2340cteam50game.view.DemonView;
import com.example.cs2340cteam50game.view.FireSkullView;
import com.example.cs2340cteam50game.view.GameScreen;
import com.example.cs2340cteam50game.view.GhostView;
import com.example.cs2340cteam50game.view.HealthPowerupView;
import com.example.cs2340cteam50game.view.PlayerView;

import com.example.cs2340cteam50game.view.ShieldPowerupView;


import com.example.cs2340cteam50game.view.SwordView;


import com.example.cs2340cteam50game.view.SpeedPowerupView;

import java.util.ArrayList;

public class GameScreenModel {

    private Handler mainHandler = new Handler();

    private final PlayerClass player = PlayerClass.getPlayer();
    private PlayerView playerView;
    private SwordView swordView;
    private ImageView map;
    private GameScreen gameScreen;
    private int screenWidth;
    private int screenHeight;
    private RelativeLayout gameLayout;

    private Drawable speedSprite;
    private Drawable healthSprite;
    private Drawable shieldSprite;
    private Drawable playerShieldSprite;

    //Creates the Creator classes used for making enemies
    private FireSkullCreator fsCreator = new FireSkullCreator();
    private Drawable fsSprite;
    private BeastCreator bCreator = new BeastCreator();
    private Drawable bSprite;
    private GhostCreator gCreator = new GhostCreator();
    private Drawable gSprite;
    private DemonCreator dCreator = new DemonCreator();
    private Drawable dSprite;

    private CountDownTimer timer;

    private int currentRoom = 0;
    private Drawable swordSprite;

    private final Rectangle[] map1Walls = {
        new Rectangle(423, 256, 450, 695),
        new Rectangle(526, 865, 953, 950),
        new Rectangle(738, 79, 768, 435),
        new Rectangle(1055, 168, 1088, 425),
        new Rectangle(1055, 518, 1088, 693),
        new Rectangle(2000, 342, 2220, 390),
        new Rectangle(2000, 522, 2220, 565),
        new Rectangle(2170, 400, 2220, 510)
    };
    private final Rectangle[] map2Walls = {
        new Rectangle(106, 232, 536, 272),
        new Rectangle(106, 632, 426, 672),
        new Rectangle(606, 872, 636, 942),
        new Rectangle(816, 872, 846, 942),
        new Rectangle(736, 472, 1166, 512),
        new Rectangle(1376, 712, 1796, 752),
        new Rectangle(1586, 312, 2016, 352),
        new Rectangle(1556, 0, 1586, 80),
        new Rectangle(1766, 0, 1796, 80),
        new Rectangle(1590, 0, 1765, 20)
    };
    private final Rectangle[] map3Walls = {
        new Rectangle(0, 230, 210, 280),
        new Rectangle(0, 390, 210, 440),
        new Rectangle(420, 80, 850, 120),
        new Rectangle(420, 710, 850, 750),
        new Rectangle(820, 310, 850, 560),
        new Rectangle(1160, 550, 1590, 590),
        new Rectangle(1480, 240, 1900, 280),
        new Rectangle(1580, 790, 2010, 830),
        new Rectangle(2090, 390, 2120, 640),
        new Rectangle(1770, 0, 1800, 80),
        new Rectangle(1980, 0, 2010, 80),
        new Rectangle(1801, 0, 1979, 20),
    };

    private ArrayList<Powerup> currentPowerups = new ArrayList<>();
    private ArrayList<Enemy> currentEnemies = new ArrayList<>();
    private ArrayList<View> powerupViews = new ArrayList<>();
    private ArrayList<View> enemyViews = new ArrayList<>();
    private ArrayList<EnemyMovementHandler> eMovementHandlers = new ArrayList<>();

    private Rectangle[] currentWallSet = map1Walls;

    private Rectangle exitBox = currentWallSet[currentWallSet.length - 1];
    private int scoreVal = 0;

    //    public void startScoreTimer(TextView scoreDisplay) {
    //        timer = new CountDownTimer((scoreVal) * 1000, 1000) {
    //            public void onTick(long millisUntilFinished) {
    //                setScoreVal((int) (millisUntilFinished / 1000));
    //                scoreDisplay.setText("Score: " + scoreVal);
    //            }
    //            public void onFinish() {
    //                setScoreVal(0);
    //                scoreDisplay.setText("Score: " + scoreVal);
    //            }
    //        }.start();
    //    }

    //Takes in a difficulty int and returns a string representing that difficulty
    public String difficultySwitch(int difficulty) {
        switch (difficulty) {
        case 1:
            return "Easy";
        case 2:
            return "Medium";
        case 3:
            return "Hard";
        default:
            return null;
        }
    }




    private PlayerMovementHandler left = new PlayerMovementHandler(0, this, mainHandler);
    private PlayerMovementHandler up = new PlayerMovementHandler(1, this, mainHandler);
    private PlayerMovementHandler down = new PlayerMovementHandler(2, this, mainHandler);
    private PlayerMovementHandler right = new PlayerMovementHandler(3, this, mainHandler);


    public void startMovement(int direction) {
        switch (direction) {
        case 0:
            new Thread(left).start();
            break;
        case 1:
            new Thread(up).start();
            break;
        case 2:
            new Thread(down).start();
            break;
        case 3:
            new Thread(right).start();
            break;
        default:
        }
    }

    public void stopMovement(int direction) {
        switch (direction) {
        case 0:
            left.stopMovement();
            break;
        case 1:
            up.stopMovement();
            break;
        case 2:
            down.stopMovement();
            break;
        case 3:
            right.stopMovement();
            break;
        default:
        }
    }


    //Callable movement methods
    public void moveLeft() {
        player.moveLeft();
        playerView.updatePosition();
    }
    public void moveUp() {
        player.moveUp();
        playerView.updatePosition();
    }
    public void moveRight() {
        player.moveRight();
        playerView.updatePosition();
    }
    public void moveDown() {
        player.moveDown();
        playerView.updatePosition();
    }

    //Collision Handler for Walls & Rooms
    public int checkCollisions(double newX, double newY, int direction) {

        Rectangle playerHitBox = new Rectangle((float) newX, (float) newY,
                (float) newX + player.getSpriteWidth(),
                (float) newY + player.getSpriteHeight());

        if (playerHitBox.intersects(exitBox)) {
            return 2;
        }

        for (int i = 0; i < currentWallSet.length - 1; i++) {
            if (playerHitBox.intersectsWall(currentWallSet[i], direction)) {
                player.setxPos(playerHitBox.getLeft());
                player.setyPos(playerHitBox.getTop());
                return 1;
            }
        }
        return 0;
    }

    // Collision Handler for Enemies
    public void checkEnemyCollisions(float xPos, float yPos, int direction) {
        Rectangle playerHitBox = new Rectangle(xPos, yPos,
                xPos + player.getSpriteWidth(),
                yPos + player.getSpriteHeight());

        for (Enemy enemy : currentEnemies) {
            if (playerHitBox.intersectsWall(enemy.getHitBox(), direction)) {

                player.takeDamage();
                updateScoreDisplay();

                player.setxPos(playerHitBox.getLeft());
                player.setyPos(playerHitBox.getTop());
                gameScreen.updateHealth(player.getHealthPoints());
                //confusion movement stuff, only applies for the ghost
                if (enemy instanceof GhostEnemy) {
                    player.setMovementStrategy(new ConfusionMovement());
                    CountDownTimer confusion = new CountDownTimer(8000, 1000) {
                        public void onTick(long millisUntilFinished) {
                        }
                        public void onFinish() {
                            player.setMovementStrategy(new DefaultSpeed());
                        }
                    }.start();

                }
                break;
            }
        }
    }

    //Collision Handler for PowerUps
    public void checkPowerUpCollisions(float xPos, float yPos, int direction) {
        Rectangle playerHitBox = new Rectangle(xPos, yPos,
                xPos + player.getSpriteWidth(),
                yPos + player.getSpriteHeight());

        for (Powerup powerup : currentPowerups) {
            if (playerHitBox.intersectsWall(powerup.getHitBox(), direction)) {
                if (powerup instanceof SpeedPowerup) {
                    player.setMovementStrategy(new SpeedBoost());
                    CountDownTimer speedBoosted = new CountDownTimer(12000, 1000) {
                        public void onTick(long millisUntilFinished) {
                        }
                        public void onFinish() {
                            player.setMovementStrategy(new DefaultSpeed());
                        }
                    }.start();
                    gameLayout.removeView(powerupViews.get(0));

                } else if (powerup instanceof HealthPowerup) {
                    if (player.getDifficultyNum() == 1) {
                        player.setHealthPoints(150); //difficulty health
                    } else if (player.getDifficultyNum() == 2) {
                        player.setHealthPoints(100);
                    } else {
                        player.setHealthPoints(75);
                    }
                    gameScreen.updateHealth(player.getHealthPoints());
                    gameLayout.removeView(powerupViews.get(0));
                } else if (powerup instanceof ShieldPowerup) {
                    //Regen
                    CountDownTimer invincibility = new CountDownTimer(8000, 400) {
                        public void onTick(long millisUntilFinished) {
                            if (player.getDifficultyNum() == 1) {
                                player.setHealthPoints(150); //difficulty health
                            } else if (player.getDifficultyNum() == 2) {
                                player.setHealthPoints(100);
                            } else {
                                player.setHealthPoints(75);
                            }
                            gameScreen.updateHealth(player.getHealthPoints());
                        }
                        public void onFinish() {
                            gameScreen.updateHealth(player.getHealthPoints());
                        }
                    }.start();
                    gameLayout.removeView(powerupViews.get(0));
                }

                break;
            }
        }
    }
    public void checkEnemySwordCollisions(Sword sword) {
        for (Enemy enemy : currentEnemies) {
            if (sword.getHitBox().intersects(enemy.getHitBox())) {

                // player score increases
                adjustScoreVal(10);
                updateScoreDisplay();

                if (currentRoom == 0 && enemy instanceof FireSkullEnemy) {

                    enemy.setHitBox(new Rectangle((float) -20, (float) -20,
                            (float) -20, (float) -20));

                    eMovementHandlers.get(0).stopMovement();
                    gameLayout.removeView(enemyViews.get(0));
                }
                if (currentRoom == 0 && enemy instanceof BeastEnemy) {
                    enemy.setHitBox(new Rectangle((float) -20, (float) -20,
                            (float) -20, (float) -20));
                    eMovementHandlers.get(1).stopMovement();
                    gameLayout.removeView(enemyViews.get(1));
                }
                if (currentRoom == 1 && enemy instanceof BeastEnemy) {
                    enemy.setHitBox(new Rectangle((float) -20, (float) -20,
                            (float) -20, (float) -20));
                    eMovementHandlers.get(0).stopMovement();
                    gameLayout.removeView(enemyViews.get(0));
                }
                if (currentRoom == 1 && enemy instanceof DemonEnemy) {
                    enemy.setHitBox(new Rectangle((float) -20, (float) -20,
                            (float) -20, (float) -20));
                    eMovementHandlers.get(1).stopMovement();
                    gameLayout.removeView(enemyViews.get(1));
                }
                if (currentRoom == 2 && enemy instanceof DemonEnemy) {
                    enemy.setHitBox(new Rectangle((float) -20, (float) -20,
                            (float) -20, (float) -20));
                    eMovementHandlers.get(0).stopMovement();
                    gameLayout.removeView(enemyViews.get(0));
                }
                if (currentRoom == 2 && enemy instanceof GhostEnemy) {
                    enemy.setHitBox(new Rectangle((float) -20, (float) -20,
                            (float) -20, (float) -20));
                    eMovementHandlers.get(1).stopMovement();
                    enemy.setHitBox(new Rectangle((float) -20, (float) -20,
                            (float) -20, (float) -20));
                    gameLayout.removeView(enemyViews.get(1));
                }
            }
        }
    }

    public void nextRoom() {

        currentEnemies.clear();
        currentPowerups.clear();
        for (EnemyMovementHandler handler : eMovementHandlers) {
            handler.stopMovement();
        }
        eMovementHandlers.clear();

        if (currentRoom == 2) {
            player.setMovementStrategy(new NoSpeed());
            //timer.cancel();
            gameScreen.endGame();
        } else {
            clearEnemies();
            clearPowerups();
            currentRoom++;
            setScreen(currentRoom);
            switch (currentRoom) {
            case 1:
                player.setxPos(700);
                player.setyPos(882);
                break;
            case 2:
                player.setxPos(10);
                player.setyPos(312);
                break;
            default:
            }
            playerView.updatePosition();
        }
    }

    //Updates the current game screen - handling the new image, screenNum, and currentWallSet
    public void setScreen(int currentScreen) {
        switch (currentScreen) {
        case 0:
            map.setImageResource(R.drawable.newmap1);
            currentWallSet = map1Walls;
            createEnemySet1();
            createEnemySet1Views();
            createPowerUpSet1();
            createPowerUpSet1Views();
            break;
        case 1:
            map.setImageResource(R.drawable.newmap2);
            currentWallSet = map2Walls;
            createEnemySet2();
            createEnemySet2Views();
            createPowerUpSet2();
            createPowerUpSet2Views();
            break;
        case 2:
            map.setImageResource(R.drawable.newmap3);
            currentWallSet = map3Walls;
            createEnemySet3();
            createEnemySet3Views();
            createPowerUpSet3();
            createPowerUpSet3Views();
            break;
        default:
            map.setImageResource(R.drawable.newmap1);
            currentWallSet = map1Walls;
            createEnemySet1();
            createEnemySet1Views();
            break;
        }
        exitBox = currentWallSet[currentWallSet.length - 1];
    }

    public void endGameDeath() {

        currentEnemies.clear();
        currentPowerups.clear();
        for (EnemyMovementHandler handler : eMovementHandlers) {
            handler.stopMovement();
        }
        eMovementHandlers.clear();
        clearEnemies();
        //timer.cancel();

        gameScreen.endGame();
    }

    //NEW CODE
    public void clearEnemies() {
        for (View enemy : enemyViews) {
            gameLayout.removeView(enemy);
        }
        enemyViews.clear();
    }

    public void clearPowerups() {
        for (View powerup : powerupViews) {
            gameLayout.removeView(powerup);
        }
        powerupViews.clear();
    }

    public void createPowerUpSet1() {
        //Add Speed PowerUp
        SpeedPowerup speedPowerup = (SpeedPowerup) new SpeedPowerup();
        speedPowerup.setSprite(speedSprite);
        speedPowerup.setxPos(210.0);
        speedPowerup.setyPos(screenHeight / 3);
        currentPowerups.add(speedPowerup);
    }

    public void createPowerUpSet1Views() {
        SpeedPowerup speedPowerup = (SpeedPowerup) currentPowerups.get(0);
        SpeedPowerupView speedPowerupView = new SpeedPowerupView(gameScreen, speedPowerup);
        speedPowerup.setSpriteData(speedPowerupView);
        gameLayout.addView(speedPowerupView);
        powerupViews.add(speedPowerupView);
    }

    public void createPowerUpSet2() {
        //Add Health PowerUp
        HealthPowerup healthPowerup = (HealthPowerup) new HealthPowerup();
        healthPowerup.setSprite(healthSprite);
        healthPowerup.setxPos(1400.0);
        healthPowerup.setyPos(screenHeight / 2);
        currentPowerups.add(healthPowerup);
    }

    public void createPowerUpSet2Views() {
        HealthPowerup healthPowerup = (HealthPowerup) currentPowerups.get(0);
        HealthPowerupView healthPowerupView = new HealthPowerupView(gameScreen, healthPowerup);
        healthPowerup.setSpriteData(healthPowerupView);
        gameLayout.addView(healthPowerupView);
        powerupViews.add(healthPowerupView);
    }

    public void createPowerUpSet3() {
        //Add Shield PowerUp
        ShieldPowerup shieldPowerup = (ShieldPowerup) new ShieldPowerup();
        shieldPowerup.setSprite(shieldSprite);
        shieldPowerup.setxPos(1000);
        shieldPowerup.setyPos(screenHeight / 2);
        currentPowerups.add(shieldPowerup);
    }

    public void createPowerUpSet3Views() {
        ShieldPowerup shieldPowerup = (ShieldPowerup) currentPowerups.get(0);
        ShieldPowerupView shieldPowerupView = new ShieldPowerupView(gameScreen, shieldPowerup);
        shieldPowerup.setSpriteData(shieldPowerupView);
        gameLayout.addView(shieldPowerupView);
        powerupViews.add(shieldPowerupView);
    }

    public void createEnemySet1() {

        //Add Fire Skull
        FireSkullEnemy fsEnemy = (FireSkullEnemy) fsCreator.createEnemy();
        fsEnemy.setSprite(fsSprite);
        fsEnemy.setxPos(screenWidth * (7.0 / 10.0));
        fsEnemy.setyPos(screenHeight / 3.0);
        currentEnemies.add(fsEnemy);

        //Add Beast
        BeastEnemy beastEnemy = (BeastEnemy) bCreator.createEnemy();
        beastEnemy.setSprite(bSprite);
        beastEnemy.setxPos(screenWidth * (3.33 / 10.0));
        beastEnemy.setyPos(screenHeight * (6 / 10.0));
        currentEnemies.add(beastEnemy);
    }

    public void createEnemySet1Views() {
        FireSkullEnemy fsEnemy = (FireSkullEnemy) currentEnemies.get(0);
        FireSkullView fsView = new FireSkullView(gameScreen,
                fsEnemy);
        fsEnemy.setSpriteData(fsView);
        gameLayout.addView(fsView);
        EnemyMovementHandler fsMove = new EnemyMovementHandler(fsEnemy, fsView, player,
                gameScreen, 1, mainHandler);
        eMovementHandlers.add(fsMove);

        new Thread(fsMove).start();

        enemyViews.add(fsView);


        BeastEnemy bEnemy = (BeastEnemy) currentEnemies.get(1);
        BeastView bView = new BeastView(gameScreen, bEnemy);
        bEnemy.setSpriteData(bView);
        gameLayout.addView(bView);
        EnemyMovementHandler bMove = new EnemyMovementHandler(bEnemy, bView, player,
                gameScreen, 0, mainHandler);
        eMovementHandlers.add(bMove);

        new Thread(bMove).start();

        enemyViews.add(bView);

    }

    public void createEnemySet2() {

        //Add Beast
        BeastEnemy beastEnemy = (BeastEnemy) bCreator.createEnemy();
        beastEnemy.setSprite(bSprite);
        beastEnemy.setxPos(screenWidth * (7.5 / 10.0));
        beastEnemy.setyPos(screenHeight * (5 / 10.0));
        currentEnemies.add(beastEnemy);

        //Add Demon
        DemonEnemy demonEnemy = (DemonEnemy) dCreator.createEnemy();
        demonEnemy.setSprite(dSprite);
        demonEnemy.setxPos(screenWidth * (3.5 / 10.0));
        demonEnemy.setyPos(screenHeight * (1 / 10.0));
        currentEnemies.add(demonEnemy);
    }

    public void createEnemySet2Views() {
        BeastEnemy bEnemy = (BeastEnemy) currentEnemies.get(0);
        BeastView bView = new BeastView(gameScreen,
                bEnemy);
        bEnemy.setSpriteData(bView);
        gameLayout.addView(bView);
        EnemyMovementHandler bMove = new EnemyMovementHandler(bEnemy, bView, player,
                gameScreen, 0, mainHandler);
        eMovementHandlers.add(bMove);

        new Thread(bMove).start();

        enemyViews.add(bView);


        DemonEnemy dEnemy = (DemonEnemy) currentEnemies.get(1);
        DemonView dView = new DemonView(gameScreen, dEnemy);
        dEnemy.setSpriteData(dView);
        gameLayout.addView(dView);
        EnemyMovementHandler dMove = new EnemyMovementHandler(dEnemy, dView, player,
                gameScreen, 3, mainHandler);
        eMovementHandlers.add(dMove);

        new Thread(dMove).start();

        enemyViews.add(dView);

    }

    public void createEnemySet3() {

        //Add Demon
        DemonEnemy demonEnemy = (DemonEnemy) dCreator.createEnemy();
        demonEnemy.setSprite(dSprite);
        demonEnemy.setxPos(screenWidth * (2.0 / 10.0));
        demonEnemy.setyPos(screenHeight * (3.33 / 10.0));
        currentEnemies.add(demonEnemy);

        //Add Ghost
        GhostEnemy ghostEnemy = (GhostEnemy) gCreator.createEnemy();
        ghostEnemy.setSprite(gSprite);
        ghostEnemy.setxPos(screenWidth * (4.75 / 10.0));
        ghostEnemy.setyPos(screenHeight * (2.25 / 10.0));
        currentEnemies.add(ghostEnemy);
    }

    public void createEnemySet3Views() {

        DemonEnemy dEnemy = (DemonEnemy) currentEnemies.get(0);
        DemonView dView = new DemonView(gameScreen, dEnemy);
        dEnemy.setSpriteData(dView);
        gameLayout.addView(dView);
        EnemyMovementHandler dMove = new EnemyMovementHandler(dEnemy, dView, player,
                gameScreen, 3, mainHandler);
        eMovementHandlers.add(dMove);

        new Thread(dMove).start();

        enemyViews.add(dView);

        GhostEnemy gEnemy = (GhostEnemy) currentEnemies.get(1);
        GhostView gView = new GhostView(gameScreen,
                gEnemy);
        gEnemy.setSpriteData(gView);
        gameLayout.addView(gView);
        EnemyMovementHandler gMove = new EnemyMovementHandler(gEnemy, gView, player,
                gameScreen, 2, mainHandler);
        eMovementHandlers.add(gMove);

        new Thread(gMove).start();

        enemyViews.add(gView);




    }


    //Sets the currently used GameScreen
    public void setGameScreen(GameScreen game) {
        this.gameScreen = game;
        this.gameLayout = gameScreen.findViewById(R.id.gameLayout);
        this.screenWidth = gameScreen.getResources().getDisplayMetrics().widthPixels;
        this.screenHeight = gameScreen.getResources().getDisplayMetrics().heightPixels;
    }

    //Sets the currentMapView
    public void setMap(ImageView map) {
        this.map = map;
    }

    //Setter for score
    public void setScoreVal(int score) {
        scoreVal = Math.max(score, 0);
    }

    public void adjustScoreVal(int change) {
        scoreVal = Math.max(scoreVal + change, 0);
    }

    public void updateScoreDisplay() {
        gameScreen.setScoreDisplay(scoreVal);
    }

    //Getter for score
    public int getScoreVal() {
        return scoreVal;
    }

    //Getter for currentScreen
    public int getCurrentRoom() {
        return currentRoom;
    }

    public ArrayList<Enemy> getCurrentEnemies() {
        return currentEnemies;
    }

    //Setter for playerView
    public void setPlayerView(PlayerView playerView) {
        this.playerView = playerView;
    }

    public void setCurrentWallSet(int wallSet) {
        switch (wallSet) {
        case 0:
            currentWallSet = map1Walls;
            currentRoom = 0;
            break;
        case 1:
            currentWallSet = map2Walls;
            currentRoom = 1;
            break;
        case 2:
            currentWallSet = map3Walls;
            currentRoom = 2;
            break;
        default:
            System.out.println("DEFAULT SET WALL SET");
        }
        exitBox = currentWallSet[currentWallSet.length - 1];
    }
    public void attack() {
        Sword sword = Sword.getSword();
        sword.setSprite(swordSprite);
        sword.setxPos(player.getxPos() + player.getSpriteWidth());
        sword.setyPos(player.getyPos());
        SwordView swordView1 = new SwordView(gameScreen, sword);
        sword.setSpriteData(swordView1);
        System.out.println(sword.getHitBox().getTop());
        System.out.println(sword.getHitBox().getLeft());
        CountDownTimer countDownTimer = new CountDownTimer(500, 500) {
            @Override
            public void onTick(long l) {
                gameLayout.addView(swordView1);
                checkEnemySwordCollisions(sword);
            }

            @Override
            public void onFinish() {
                gameLayout.removeView(swordView1);
            }
        }.start();
    }
    private void removeSword(SwordView swordView1) {
        gameLayout.removeView(swordView1);
    }

    public void setFsSprite(Drawable fsSprite) {
        this.fsSprite = fsSprite;
    }

    public void setbSprite(Drawable bSprite) {
        this.bSprite = bSprite;
    }

    public void setgSprite(Drawable gSprite) {
        this.gSprite = gSprite;
    }

    public void setdSprite(Drawable dSprite) {
        this.dSprite = dSprite;
    }
    public void setEnemies(Enemy enemy) {
        currentEnemies.add(enemy);
    }

    public void setSwordSprite(Drawable drawable) {
        this.swordSprite = drawable;
    }
}


