package com.isavin.tictacttoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.isavin.tictacttoe.core.Difficulty;

public class GreetingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greetings);
        EditText nameView = (EditText) findViewById(R.id.player_name);
        nameView.setCursorVisible(false);
        nameView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((EditText) v).setCursorVisible(true);
                } else {
                    ((EditText) v).setCursorVisible(false);
                }

            }
        });
    }

    public void onStartGameClicked(View view) {
        Intent intent = new Intent(this, GameActivity.class);

        EditText nameView = (EditText) findViewById(R.id.player_name);
        String playerName = nameView.getText().toString();
        if ("".equals(playerName)) {
            Toast.makeText(this, getResources().getString(R.string.enter_name_warning), Toast.LENGTH_SHORT).show();
            return;
        }
        intent.putExtra("playerName", playerName);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.game_mode_id);
        if (radioGroup.getCheckedRadioButtonId() != -1) {
            RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
            intent.putExtra("gameMode", radioButton.getText().toString());
        }

        Spinner levelSpinner = (Spinner) findViewById(R.id.select_level);
        intent.putExtra("level", Difficulty.getDifficultyById((int) levelSpinner.getSelectedItemId()));
        startActivity(intent);
    }
}
