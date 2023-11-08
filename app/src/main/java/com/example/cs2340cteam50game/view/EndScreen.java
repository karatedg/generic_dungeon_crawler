package com.example.cs2340cteam50game.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340cteam50game.model.PlayerClass;
import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.Score;
import com.example.cs2340cteam50game.model.Leaderboard;


public class EndScreen extends AppCompatActivity {
    private GameScreen gameScreen = new GameScreen();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endofgame_activity);


        //Restart and Quit Buttons
        Button restartButton = (Button) findViewById(R.id.restartButton);
        Button quitButton = (Button) findViewById(R.id.quitButton);

        TextView leaderboard = (TextView) findViewById(R.id.leaderboard);

        TextView gameStatus = (TextView) findViewById(R.id.GameOverText);
        if (PlayerClass.getPlayer().getHealthPoints() <= 0) {
            gameStatus.setText("GAME OVER");
        }

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
        Score score1 = new Score(PlayerClass.getPlayer().getUsername(), score);
        LeaderBoardView.renderLeaderboard(Leaderboard.getLeaderboard(),
                                findViewById(R.id.leaderboard), score1);
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
