package com.isavin.tictacttoe.core.player;

import com.isavin.tictacttoe.core.Board;
import com.isavin.tictacttoe.core.Figure;
import com.isavin.tictacttoe.core.Move;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by isavin on 02.04.16.
 */
public class Human extends Player {

    public Human(String name, Figure figure) {
        super(name, figure);
    }

    @Override
    public Move makeMove(Board board) {
        return null;
    }

}
