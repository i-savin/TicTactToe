package com.isavin.tictactoe.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Class <code>Board</code> represents game field for current game.
 *
 * @author Ilia Savin
 */
public class Board {
    private static final int DEFAULT_FIELD_SIZE = 3;
    
    private int fieldSize;
    private char[][] gameField;

    public Board getNewState(Move move) {
        Board newBoard = new Board();
        for (int i = 0; i < fieldSize; i++) {
            System.arraycopy(gameField[i], 0, newBoard.gameField[i], 0, fieldSize);
        }
        newBoard.gameField[move.getRow()][move.getColumn()] = move.getFigure().toChar();
        return newBoard;
    }

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
        for (char[] aGameField : gameField) {
            for (int j = 0; j < gameField.length; j++) {
                if (aGameField[j] == 'n') {
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

	public char getFieldCell(Move move) {
	    int row = move.getRow();
	    int column = move.getColumn();
	    
	    return gameField[row][column];
	}

	public char[][] getGameField() {
	    return this.gameField;
	}

	public int getSize() {
	    return this.fieldSize;
	}
	
	public String toString() {
	    StringBuilder field = new StringBuilder("\n");
        for (char[] aGameField : gameField) {
            for (char anAGameField : aGameField) {
                field.append("[").append(anAGameField).append("]");
            }
            field.append("\n");
        }
        return field.toString();
    }
}
