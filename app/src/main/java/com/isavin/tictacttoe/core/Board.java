package com.isavin.tictacttoe.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class <code>Board</code> represents game field for current game.
 *
 * @author Ilia Savin
 * @version
 */
public class Board {
    private static final int DEFAULT_FIELD_SIZE = 3;
    
    private int fieldSize;
    private char[][] gameField;

    public Board getNewState(Move move) {
        Board newBoard = new Board();
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                newBoard.gameField[i][j] = gameField[i][j];
            }
        }
        newBoard.gameField[move.getRow()][move.getColumn()] = move.getFigure().toChar();
        return newBoard;
    }
    
    /**
     * Initializes newly created <code>Board</code> object. Creates new 
     * <code>char</code> array which represents abstract game field and stores
     * values of each move. Default value (i.e. value before any moves happen)
     * is <code>n</code>. 
     */
    public Board() {
        fieldSize = DEFAULT_FIELD_SIZE;
        this.gameField = new char[fieldSize][fieldSize];
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField.length; j++) {
                gameField[i][j] = 'n';
            }
        }
    }

    public List<Move> getAvailableMoves() {
        List<Move> moves = new ArrayList<>();
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField.length; j++) {
                if (gameField[i][j] == 'n') {
                    moves.add(new Move(i, j));
                }
            }
        }
        return moves;
    }

    public int getAvailableMovesNumber() {
        int result = 0;
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField.length; j++) {
                if (gameField[i][j] == 'n') {
                    result++;
                }
            }
        }
        return result;
    }
    
    /**
     * Method to change values on game field cells if made move is correct.
     *
     * @see Rules#validate(Move move)
     * @see Move
     */
    public void change(Move move) {
	    int row = move.getRow();
	    int column = move.getColumn();
	    Figure figure = move.getFigure();
	    
	    gameField[row][column] = figure.toChar();
	}

    /**
	 * Return values of game field cell.
	 *
	 * @see Move
	 * @param move <code>Move</code> object
	 * @return <code>char,/code> symbol
	 */
	public char getFieldCell(Move move) {
	    int row = move.getRow();
	    int column = move.getColumn();
	    
	    return gameField[row][column];
	}

	public char[][] getGameField() {
	    return this.gameField;
	}
	
	/**
	 * Method to get game board size. Return one number because field is square.
	 */
	public int getSize() {
	    return this.fieldSize;
	}
	
	public String toString() {
	    StringBuffer field = new StringBuffer("\n");
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                field.append("[" + gameField[i][j] + "]");
            }
            field.append("\n");
        }
        return field.toString();
    }
}
