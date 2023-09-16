package com.example.cs2340cteam50game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int button1Count = 0;
    private boolean buttonClicked = false;

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences.Editor e1 = getSharedPreferences("p1", MODE_PRIVATE).edit();
        e1.clear();
        e1.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button);
        Button nextButton = (Button) findViewById(R.id.nextButton);
        Button resetCount = (Button) findViewById(R.id.resetCount);
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        SharedPreferences p1 = getSharedPreferences("p1", MODE_PRIVATE);

        if (p1.contains("text")) {
            textView1.setText(p1.getString("text", ""));
            button1Count = p1.getInt("clickCount", 0);
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1Count++;
                textView1.setText(String.format(getString(R.string.buttonClick), button1Count));
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        resetCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1Count = 0;
                textView1.setText(R.string.Activity1Text);
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        TextView text = (TextView) findViewById(R.id.textView1);
        SharedPreferences p1 = getSharedPreferences("p1", MODE_PRIVATE);
        SharedPreferences.Editor e1 = getSharedPreferences("p1", MODE_PRIVATE).edit();
        e1.putString("text", text.getText().toString());
        e1.putInt("clickCount", button1Count);
        e1.apply();
        Log.d("Activity Stopped", "A1 Closed");
    }

}