package com.isavin.tictactoe.core;

/**
 * Enumeration class <code>Figure</code> represents available figures to play.
 *
 * @author Ilia Savin
 */
public enum Figure {
    X,
    O;

    public char toChar() {
        if (this == Figure.X) {
            return 'x';
        } else {
            return 'O';
        }
    }

    public Figure opposite() {
        if (this == Figure.X) {
            return Figure.O;
        } else {
            return Figure.X;
        }
    }
}

