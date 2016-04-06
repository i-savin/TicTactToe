package com.isavin.tictacttoe.core;

/**
 * Enumeration class <code>Figure</code> represents available figures to play.
 *
 * @author Ilia Savin
 * @version
 */
public enum Figure {
    X,
    O;
    
    /**
     * Method to get char representation of <code>Figure</code> object.
     *
     * @return char symbol of figure
     */
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

