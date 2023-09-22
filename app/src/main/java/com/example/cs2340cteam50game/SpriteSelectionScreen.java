package com.example.cs2340cteam50game;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SpriteSelectionScreen extends AppCompatActivity {
    private int spriteNum;
    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_sprite_selection);

        Button backButton = (Button) findViewById(R.id.backButton);
        TextView textView1 = (TextView) findViewById(R.id.gamePropertiesViewText);


    }




}
