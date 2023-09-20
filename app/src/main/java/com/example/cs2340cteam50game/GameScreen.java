package com.example.cs2340cteam50game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {

    private int health;
    private String name;
    private int difficulty;
    private int spriteNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_game_activity);

        //getting ids for different elements that will be needed
        Button skipToEnd = (Button) findViewById(R.id.skipToEnd);
        TextView healthValue = (TextView) findViewById(R.id.healthValue);
        TextView playerName = (TextView) findViewById(R.id.playerName);
        TextView difficultySettings = (TextView) findViewById(R.id.difficultySetting);


        /**
         * TODO: set values here for health, name, spriteNum, and string based on what is passed from configScreen
         * using something like this in the previous screen:
         * intentName.putExtra("name",value)
         * Then getting the value here with:
         * variable = getIntent.get[DATATYPE]Extra("name", defaultValue)
         * I think this should work for getting the values from Config to Game.

         **/



        //skip to end screen when button pressed
        skipToEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GameScreen.this, EndScreen.class);
                startActivity(intent);
            }
        });

    }

}