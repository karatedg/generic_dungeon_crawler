package com.example.cs2340cteam50game.viewmodel;

import android.widget.ImageView;

import com.example.cs2340cteam50game.R;

public class GameScreenModel {



    public static String difficultySwitch(int difficulty) {
        switch (difficulty) {
        case 1:
            return "Easy";
        case 2:
            return "Medium";
        case 3:
            return "Hard";
        default:
            return "Easy";
        }
    }

    public static void spriteSet(int spriteNum, ImageView spriteImage) {
        switch (spriteNum) {
        case 1:
            spriteImage.setImageResource(R.drawable.red_idle);
            break;
        case 2:
            spriteImage.setImageResource(R.drawable.blue_idle);
            break;
        case 3:
            spriteImage.setImageResource(R.drawable.green_idle);
            break;
        default:
            break;
        }
    }

    public static int setScreen(int currentScreen, ImageView map) {
        switch (currentScreen) {
        case 0:
            map.setImageResource(R.drawable.map1);
            return 0;
        case 1:
            map.setImageResource(R.drawable.map2);
            return 1;
        case 2:
            map.setImageResource(R.drawable.map3);
            return 2;
        default:
            map.setImageResource(R.drawable.map1);
            return 0;
        }
    }

}
