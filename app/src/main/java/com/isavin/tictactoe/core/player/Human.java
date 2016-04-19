package com.isavin.tictactoe.core.player;

import com.isavin.tictactoe.core.Board;
import com.isavin.tictactoe.core.Figure;
import com.isavin.tictactoe.core.Move;

public class Human extends Player {

    public Human(String name, Figure figure) {
        super(name, figure);
    }

    @Override
    public Move makeMove(Board board) {
        return null;
    }

}
