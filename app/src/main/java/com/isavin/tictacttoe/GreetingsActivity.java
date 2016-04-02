package com.isavin.tictacttoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class GreetingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greetings);
    }

    public void onStartGameClicked(View view) {
        Intent intent = new Intent(this, GameActivity.class);

        EditText nameView = (EditText) findViewById(R.id.player_name);
        String playerName = nameView.getText().toString();
        if ("".equals(playerName)) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }
        intent.putExtra("playerName", playerName);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.game_mode_id);
        if (radioGroup.getCheckedRadioButtonId() != -1) {
            RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
            intent.putExtra("gameMode", radioButton.getText().toString());
        }

        startActivity(intent);
    }
}
