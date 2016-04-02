package com.isavin.tictacttoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        String playerName = intent.getStringExtra("playerName");
        String gameMode = intent.getStringExtra("gameMode");

        String gameText = String.format("Player %s has entered the game as %s", playerName, gameMode);
        TextView textView = (TextView) findViewById(R.id.game_text_id);
        textView.setText(gameText);

    }

    public void onMove(View view) {
        ImageView imageView = (ImageView) view;
        imageView.setImageResource((R.drawable.x));
    }
}
