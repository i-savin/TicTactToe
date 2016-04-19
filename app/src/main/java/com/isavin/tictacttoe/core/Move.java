package com.isavin.tictacttoe.core;

/**
 * Class <code>Move</code> contains next move position and making move
 * player's game figure.
 *
 * @author Ilia Savin
 * @version
 */
public class Move {
    private Figure figure;
	private int column;
	private int row;

	public Move(Figure figure, int row, int column) {
	    this.figure = figure;
	    this.column = column;
	    this.row = row;
	}

	public Move(int row, int column) {
		this.column = column;
		this.row = row;
	}

	public Figure getFigure() {
	    return this.figure;
	}

	public int getColumn() {
	    return this.column;
	}

	public int getRow() {
	    return this.row;
	}

	public void setFigure(Figure figure) {
		this.figure = figure;
	}

	@Override
	public String toString() {
	    return "[" + row + ", " + column + ", " + figure.name() + "]";
	}
}

