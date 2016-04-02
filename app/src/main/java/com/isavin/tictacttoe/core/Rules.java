package com.isavin.tictacttoe.core;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class <code>Rules</code> represents virtual referee. It checks if players 
 * make correct moves, defines moves priority, defines winner of current
 * party and so on.
 *
 * @author Ilia Savin
 * @version
 */
public class Rules {
    private final Logger logger = Logger.getLogger("com.isavin.tictacttoe");
    private static final Figure FIRST_MOVE_FIGURE = Figure.X;

    private Game game;
    private Figure firstMoveFigure;
    private int movesCount;
    
    /**
     * Initializes newly created <code>Rules</code> object.
     *
     * @param figure <code>Figure</code> object to define first move's figure
     */
    public Rules(Figure figure) {
        this.firstMoveFigure = figure;
    }
    
    /**
     * Initializes newly created <code>Rules</code> object by calling
     * <code>this()</code> with default <code>FIRST_MOVE_FIGURE</code> as
     * parameter.
     */
    public Rules() {
        this(FIRST_MOVE_FIGURE);
    }

    /**
     * Method to check if the move is correct. It checks if move's index
     * is in game field bounds and if move's position is empty.
     *
     * @param move <code>Move</code> object to be validated
     * @return true if move is correct and false if not.
     */
	public boolean validate(Move move) {
	    if (move == null) {
	        if (movesCount == 9) {
                game.getPlayers()[0].setStatus(Status.DRAW);
                game.getPlayers()[1].setStatus(Status.DRAW);
                game.finish();
                return true;
	        }
	    }   
	    if (isMoveIndexCorrect(move) && isMoveFieldEmpty(move)) {
	        game.getBoard().change(move);
	        movesCount++;
	        return true;
	    } else {
	        logger.log(Level.INFO, "Incorrect: " + move);
	        return false;
	    }   
	}
	
	/**
	 * Method to define player's priority. If player's figure is the same as
	 * <code>firstMoveFigure</code> it will go first.
	 *
	 * @param player <code>Player</code> object to define priority
	 * @return <code>int</code> index of player
	 */
	public int getTurnNumber(Player player) {
	    if (player.getFigure() == firstMoveFigure) {
	        return 0;
	    } else {
	        return 1;
	    }
	}
	
	private boolean isMoveIndexCorrect(Move move) {
	    if (move.getColumn() >= game.getBoard().getSize()
	            || move.getRow() >= game.getBoard().getSize()) {
	        logger.log(Level.INFO, "Move is incorrect");
	        return false;
	    } else {
	        return true;
	    }
	}
	
	private boolean isMoveFieldEmpty(Move move) {
	    if (game.getBoard().getFieldCell(move) == 'n') {
	        return true;
	    } else {
	        logger.log(Level.INFO, "Move field is not empty");
	        return false;
	    }
	}
	
	/**
	 * Method to check current game state, i.e. is there winner or game may be
	 * continued. If game must be finish, method update players statuses,
	 * calls game's <code>finish()</code> method.
	 *
	 * @see Status
	 * @see Game#resetPlayersMoves()
	 * @see Game#finish()
	 */
	public void checkGameState() {
	    if (won(Figure.X)) {
	        updatePlayerStatus(Figure.X, game.getPlayers());
	        game.finish();
	        return;
	    }
	    
	    if (won(Figure.O)) {
	        updatePlayerStatus(Figure.O, game.getPlayers());
	        game.finish();
	        return;
	    }
   
	    if (movesCount == 9) {
	        game.getPlayers()[0].setStatus(Status.DRAW);
	        game.getPlayers()[1].setStatus(Status.DRAW);
	        game.finish();
	        return;
	    }
	}
	
	private boolean won(Figure wonFigure) {
	    char[][] field = game.getBoard().getGameField();
	    char symbol = wonFigure.name().charAt(0);
	    
        if (field[1][1] == symbol) { 
            if(field[0][0] == symbol && field[2][2] == symbol) {
                return true;
            }
            if(field[0][2] == symbol && field[2][0] == symbol) {
                return true;
            }
            if(field[1][0] == symbol && field[1][2] == symbol) {
                return true;
            }
            if(field[0][1] == symbol && field[2][1] == symbol) {
                return true;
            }
        }
        if(field[0][0] == symbol) {
            if(field[0][1] == symbol && field[0][2] == symbol) {
                return true;
            }
            if(field[1][0] == symbol && field[2][0] == symbol) {
                return true;
            }
        }
        if(field[2][2] == symbol) {
            if(field[1][2] == symbol && field[0][2] == symbol) {
                return true;
            }
            if( field[2][1] == symbol && field[2][0] == symbol) {
                return true;
            }
        }
        
        return false;
    }
    
    private void updatePlayerStatus(Figure wonFigure, Player[] players) {
        for (int i = 0; i < players.length; i++) {
            if (players[i].getFigure() == wonFigure) {
                players[i].setStatus(Status.WON);
            } else {
                players[i].setStatus(Status.LOST);
            }
        }
    }
    
    /**
     * Set current <code>game</code> filed.
     * 
     * @param game <code>Game</code> object to be set
     */
    public void setGame(Game game) {
        this.game = game;
    }
}

