package com.example.cs2340cteam50game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class EndScreen extends AppCompatActivity {
    GameScreen gameScreen = new GameScreen();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endofgame_activity);

        //Restart and Quit Buttons
        Button restartButton = (Button) findViewById(R.id.restartButton);
        Button quitButton = (Button) findViewById(R.id.quitButton);

        TextView leaderboard = (TextView) findViewById(R.id.leaderboard);

        //Return to opening screen
        restartButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(EndScreen.this, OpeningScreen.class);
                        startActivity(intent);
                    }
                });

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        Score score1 = new Score();
        LeaderBoardView.renderLeaderboard(Leaderboard.getLeaderboard(), findViewById(R.id.leaderboard));
        //leaderboard.setText(Integer.toString(gameScreen.scoreVal));

        //Quit to home screen
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EndScreen.this.finishAffinity();
                System.exit(0);
            }
        });

    }

}
