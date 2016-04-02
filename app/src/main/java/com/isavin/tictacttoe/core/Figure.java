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
        if (this.name().equals("x")) {
            return 'x';
        } else {
            return 'O';
        }
    }
}

