package com.isavin.tictacttoe.core;

/**
 * Class <code>Move</code> contains next move position and making move
 * player's gamefigure.
 *
 * @author Ilia Savin
 * @version
 */
public class Move {
    private Figure figure;
	private int column;
	private int row;
	
	/**
	 * Initializes newly created <code>Move</code> object.
	 *
	 * @param figure making move player's game figure
	 * @param row y coordinate of current move
	 * @param column x coordinate of current move
	 */
	public Move(Figure figure, int row, int column) {
	    this.figure = figure;
	    this.column = column;
	    this.row = row;
	}
	
	/**
	 * Method to get current move figure.
	 *
	 * @return <code>Figure</code> object
	 */
	public Figure getFigure() {
	    return this.figure;
	}
	
	/**
	 * Method to get x coordinate of the move.
	 *
	 * @return <code>int</code> value of column
	 */
	public int getColumn() {
	    return this.column;
	}
	
	/**
	 * Method to get Y coordinate of the move.
	 *
	 * @return <code>int</code> value of row
	 */
	public int getRow() {
	    return this.row;
	}
	
	/**
	 * Return string representation of move.
	 *
	 * @return <code>String</code> e.g. "[1, 2, Figure.x]"
	 */
	@Override
	public String toString() {
	    return "[" + row + ", " + column + ", " + figure.name() + "]";
	}
}

