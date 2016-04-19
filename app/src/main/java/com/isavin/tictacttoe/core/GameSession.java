package com.isavin.tictacttoe.core;

import com.isavin.tictacttoe.core.player.ArtificialIntelligence;
import com.isavin.tictacttoe.core.player.Human;
import com.isavin.tictacttoe.core.player.Player;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Class <code>GameSession</code> represents game session between two players.
 * Each game party has two players, game board and rules. Rules defines
 * player's behavior during the game, conditions of finishing party, 
 * winners etc. During the game each player makes his move, after each move
 * <code>Rules</code> object checks if game can be continued and if so next
 * player makes his move. GameSession party continues until one of player wins or
 * there aren't available moves.
 *
 * @see Rules
 * @see Player
 * @see Board
 * @author Ilia Savin
 * @version
 */
public class GameSession {
    private final Logger logger = Logger.getLogger("com.isavin.tictacttoe");

    private Board board;
    private Rules rules;
    private boolean finished;

    private ArtificialIntelligence aiPlayer;
    private Human humanPlayer;

    public GameSession(ArtificialIntelligence aiPlayer, Human humanPlayer, Rules rules) {
        board = new Board();
        this.aiPlayer = aiPlayer;
        this.humanPlayer = humanPlayer;
        this.rules = rules;
    }

    public Move makeAiMove() {
        Move currentMove;
        currentMove = aiPlayer.makeMove(board);
        logger.log(Level.INFO, aiPlayer + " moves at " + currentMove);
        if (!rules.validate(currentMove)) {
            return null;
        }
        board.change(currentMove);
        aiPlayer.setMadeMove(true);
        rules.checkGameState();
        logger.log(Level.INFO, board.toString());
        return currentMove;
    }

    public Move makeHumanMove(int row, int column) {
        Move currentMove = new Move(humanPlayer.getFigure(), row, column);
        if (!rules.validate(currentMove)) {
            return null;
        }
        board.change(currentMove);
        rules.checkGameState();
        return currentMove;
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
	    return new Player[] {aiPlayer, humanPlayer};
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
        if (aiPlayer.getStatus() == Player.Status.WON) {
            return aiPlayer.getName();
        }
        if (humanPlayer.getStatus() == Player.Status.WON) {
            return humanPlayer.getName();
        }
	    return null;
	}

    public ArtificialIntelligence getAiPlayer() {
        return aiPlayer;
    }

    public Human getHumanPlayer() {
        return humanPlayer;
    }
}

