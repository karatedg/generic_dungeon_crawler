package com.example.cs2340cteam50game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class EndScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endofgame_activity);

        //Restart and Quit Buttons
        Button restartButton = (Button) findViewById(R.id.restartButton);
        Button quitButton = (Button) findViewById(R.id.quitButton);

        /*//Leaderboard
        ListView leaderboardlist = (ListView) findViewById(R.id.leaderboard);
        String[] players = new String[5];
        players[0] = "Player 1";
        players[1] = "Player 2";
        players[2] = "Player 3";
        players[3] = "Player 4";
        players[4] = "Player 5";
        players[5] = "Player 6";

        ArrayAdapter<String> playersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, players);
        leaderboardlist.setAdapter(playersAdapter);*/

        //Return to opening screen
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EndScreen.this, OpeningScreen.class);
                startActivity(intent);
            }
        });

        //Quit to home screen
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EndScreen.this.finish();
                System.exit(0);
            }
        });

    }

}
