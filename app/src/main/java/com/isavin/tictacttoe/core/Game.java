package com.isavin.tictacttoe.core;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Class <code>Game</code> represents game session between two players.
 * Each game party has two players, game board and rules. Rules defines
 * player's behavior during the game, conditions of finishing party, 
 * winners etc. During the game each player makes his move, after each move
 * <code>Rules</code> object checks if game can be continued and if so next
 * player makes his move. Game party continues until one of player wins or
 * there aren't available moves.
 *
 * @see Rules
 * @see Player
 * @see Board
 * @author Ilia Savin
 * @version
 */
public class Game implements Runnable {
    private final Logger logger = Logger.getLogger("com.isavin.tictacttoe");
    
    private Board board;
    private Player[] players;
    private Rules rules;
    private boolean finished;
    
    /**
     * Initializes newly created <code>Game</code> object.
     * When game gets player it "asks" rules to define which one will go first
     * and according to that each player gets specific index in game's
     * <code>Players</code> array.
     *
     * @param playerOne <code>Player</code> object representing the first player
     * @param playerTwo <code>Player</code> object representing the second player
     * @param rules <code>Rules</code> object
     */
    public Game(Player playerOne, Player playerTwo, Rules rules) {
        board = new Board();
        players = new Player[2];
        players[rules.getTurnNumber(playerOne)] = playerOne;
        players[rules.getTurnNumber(playerTwo)] = playerTwo;
        this.rules = rules;
        for (int i = 0; i < players.length; i++) {
            logger.log(Level.INFO, "Player: " + players[i] + " plays "
                                              + players[i].getFigure());
        }
    }
    
    @Override
    public void run() {
        play();
    }
    
    /**
     * Method to describe one game session. While <code>Rules</code> object
     * doesn't finish the game (by calling <code>finish()</code> method,
     * players makes their moves.
     *
     * @see #makeMove(int number)
     * @see Status
     */
    public void play() {
        logger.log(Level.INFO, "New game begins...");
        while (!finished) {
            for (int i = 0; i < players.length; i++) {
                if (!finished) {
                    logger.log(Level.INFO, "Player " + i + " moves");
                    makeMove(i);
                }
                players[i].setMadeMove(false);
            }
        }
        for (int i = 0; i < players.length; i++) {
            setPlayersMadeMove(true);
            if (players[i].getStatus() == Status.WON) {
                logger.log(Level.INFO, players[i].getFigure().name() + " won!");
                return;
            }
        }
        logger.log(Level.INFO, "It's a draw!");
    }

    /**
     * Method calls <code>Player</code>'s <code>makeMove</code> method, passes
     * it to <code>Rules</code> object to validate it. Then calls the
     * <code>Rules</code> method <code>chackGameState()</code> to check if
     * the game can be finished.
     *
     * @param number of player to make move
     * @see Rules#validate(Move move)
     * @see Rules#checkGameState()
     */
	public void makeMove(int number) {
	    Move currentMove;
	    do {
	        currentMove = players[number].makeMove(board);
	        logger.log(Level.INFO, players[number] + " moves at " + currentMove);
	    } while (!rules.validate(currentMove));
	    players[number].setMadeMove(true);
	    rules.checkGameState();
	    logger.log(Level.INFO, board.toString());
	}
	
	/**
	 * Method to finish current game, i.e. to set <code>finished</code> field
	 * to <code>true</code>
	 */
	public void finish() {
	    this.finished = true;
	}
	
	/**
	 * Method to get players array.
	 *
	 * @return <code>Player[]</code> array
	 */
	public Player[] getPlayers() {
	    return this.players;
	}
	
	/**
	 * Method to get game board.
	 *
	 * @return <code>Board</code> object
	 */
	public Board getBoard() {
	    return this.board;
	}
	
	/**
	 * Method to check if current game finishes.
	 *
	 * @return true if game is finished
	 */
	public boolean isFinished() {
	    return this.finished;
	}
	
	/**
	 * Method to get winner of current game.
	 *
	 * @return <code>String</code> "type-of-figure won" or "It's a draw"
	 */
	public String getWinner() {
	    for (int i = 0; i < players.length; i++) {
	        if (players[i].getStatus() == Status.WON) {
	            return players[i].getFigure().name() + " won!";
	        }
	    }
	    return "It's a draw";
	}
	
	private void setPlayersMadeMove(boolean made) {
	    for (int i = 0; i < players.length; i++) {
	        players[i].setMadeMove(made);
	    }
	}
}

