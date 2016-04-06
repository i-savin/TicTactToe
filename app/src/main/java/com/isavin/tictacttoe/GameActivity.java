package com.isavin.tictacttoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.isavin.tictacttoe.core.Figure;
import com.isavin.tictacttoe.core.GameSession;
import com.isavin.tictacttoe.core.Move;
import com.isavin.tictacttoe.core.Rules;
import com.isavin.tictacttoe.core.player.ArtificialIntelligence;
import com.isavin.tictacttoe.core.player.Human;
import com.isavin.tictacttoe.core.player.Player;

public class GameActivity extends AppCompatActivity {

    private GameSession game;

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

        Human human = new Human(playerName, Figure.valueOf(gameMode));
        ArtificialIntelligence skynet = new ArtificialIntelligence(gameMode.equalsIgnoreCase("x") ? Figure.O : Figure.X);
        Rules rules = new Rules();
        game = new GameSession(skynet, human, rules);
        rules.setGame(game);
        if (skynet.getFigure().equals(Figure.X)) {
            Move move = game.makeAiMove();
            if (move != null) {
                String moveId = "cell_" + move.getRow() + "_" + move.getColumn();
                ImageView imageView = (ImageView) findViewById(getResources().getIdentifier(
                        moveId, "id", getApplicationContext().getPackageName()));
                imageView.setImageResource(skynet.getFigure().equals(Figure.X) ? R.drawable.x : R.drawable.o);
            }
        }
    }

    public void onMove(View view) {
        ImageView imageView = (ImageView) view;
        String resourceName = getResources().getResourceName(imageView.getId());
        String[] strings = resourceName.split("_");
        Move move = game.makeHumanMove(Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
        if (move != null) {
            imageView.setImageResource(game.getHumanPlayer().getFigure().equals(Figure.X) ? R.drawable.x : R.drawable.o);
        } else {
            return;
        }
        if (game.isFinished()) {
            Toast.makeText(this, game.getWinner(), Toast.LENGTH_LONG).show();
            return;
        }

        move = game.makeAiMove();
        if (move != null) {
            String moveId = "cell_" + move.getRow() + "_" + move.getColumn();
            imageView = (ImageView) findViewById(getResources().getIdentifier(
                    moveId, "id", getApplicationContext().getPackageName()));
            imageView.setImageResource(game.getAiPlayer().getFigure().equals(Figure.X) ? R.drawable.x : R.drawable.o);
        }
        if (game.isFinished()) {
            Toast.makeText(this, game.getWinner(), Toast.LENGTH_LONG).show();
            return;
        }
    }
}
