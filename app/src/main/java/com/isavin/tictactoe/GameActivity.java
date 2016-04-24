package com.isavin.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.isavin.tictactoe.core.Difficulty;
import com.isavin.tictactoe.core.Figure;
import com.isavin.tictactoe.core.GameSession;
import com.isavin.tictactoe.core.Move;
import com.isavin.tictactoe.core.Rules;
import com.isavin.tictactoe.core.player.ArtificialIntelligence;
import com.isavin.tictactoe.core.player.Human;

public class GameActivity extends AppCompatActivity {

    private GameSession game;
    private String playerName;
    private String gameMode;
    private Difficulty level;

    private View btnPlayAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        playerName = intent.getStringExtra("playerName");
        gameMode = intent.getStringExtra("gameMode");
        level = (Difficulty) intent.getSerializableExtra("level");
        String gameText = String.format(getResources().getString(R.string.player_enter), playerName, gameMode);
        TextView textView = (TextView) findViewById(R.id.game_text_id);
        assert textView != null;
        textView.setText(gameText);

        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        Human human = new Human(playerName, Figure.valueOf(gameMode));
        ArtificialIntelligence artificialIntelligence = new ArtificialIntelligence(gameMode.equalsIgnoreCase("x") ? Figure.O : Figure.X, level.getDepth());
        Rules rules = new Rules();
        game = new GameSession(artificialIntelligence, human, rules);
        rules.setGame(game);
        if (rules.getTurnNumber(artificialIntelligence) < rules.getTurnNumber(human)) {
            Move move = game.makeAiMove();
            if (move != null) {
                String moveId = "cell_" + move.getRow() + "_" + move.getColumn();
                ImageView imageView = (ImageView) findViewById(getResources().getIdentifier(
                        moveId, "id", getApplicationContext().getPackageName()));
                assert imageView != null;
                imageView.setImageResource(artificialIntelligence.getFigure().equals(Figure.X) ? R.drawable.x : R.drawable.o);
            }
        }
    }

    private void resetGame() {
        Human human = new Human(playerName, Figure.valueOf(gameMode));
        ArtificialIntelligence artificialIntelligence = new ArtificialIntelligence(gameMode.equalsIgnoreCase("x") ? Figure.O : Figure.X, level.getDepth());
        Rules rules = new Rules();
        game = new GameSession(artificialIntelligence, human, rules);
        rules.setGame(game);
        for (int i = 0; i < game.getBoard().getSize(); i++) {
            for (int j = 0; j < game.getBoard().getSize(); j++) {
                int id = getResources().getIdentifier("cell_" + i + "_" + j, "id", getPackageName());
                ImageView imageView = (ImageView) findViewById(id);
                imageView.setImageResource(R.drawable.empty);
            }
        }
        if (rules.getTurnNumber(artificialIntelligence) < rules.getTurnNumber(human)) {
            Move move = game.makeAiMove();
            if (move != null) {
                String moveId = "cell_" + move.getRow() + "_" + move.getColumn();
                ImageView imageView = (ImageView) findViewById(getResources().getIdentifier(
                        moveId, "id", getApplicationContext().getPackageName()));
                assert imageView != null;
                imageView.setImageResource(artificialIntelligence.getFigure().equals(Figure.X) ? R.drawable.x : R.drawable.o);
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
            String winner = game.getWinner();
            String finishText = winner != null ?
                    String.format(getResources().getString(R.string.won_message), winner) :
                    getResources().getString(R.string.draw_message);
            Toast.makeText(this, finishText, Toast.LENGTH_SHORT).show();
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
            String winner = game.getWinner();
            String finishText = winner != null ?
                    String.format(getResources().getString(R.string.won_message), winner) :
                    getResources().getString(R.string.draw_message);
            Toast.makeText(this, finishText, Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
