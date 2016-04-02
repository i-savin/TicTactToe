package com.isavin.tictacttoe.core;

/**
 * Class <code>Player</code> represents abstract game player.
 * Main method of the player is <code>makeMove()</code> method. It must be 
 * override in each subclass. Player has <code>Name</code> and
 * <code>Figure</code> fields. Last one indicates which figure current player
 * moves. Also player has status - WON, LOST, DRAW or PLAYING.
 *
 * @author Ilia Savin
 * @version
 */
public abstract class Player {
    protected String name;
    protected Figure figure;
    protected Status status;
    protected boolean madeMove;
    
    /**
     * Initializes newly created <code>Player</code> object.
     *
     * @param name String name of the player
     * @param figure figure type player to control
     */
    public Player(String name, Figure figure) {
        this.name = name;
        this.figure = figure;
        status = Status.PLAYING;
    }
    
    /**
     * Method to be overriden in subclasses.
     *
     * @see Move
     * @param board game board to make moves on
     * @return move to be made
     */
    public abstract Move makeMove(Board board);
    
    /**
     * Method to set current player status.
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    
    /**
     * Return current player status.
     *
     * @return PLAYING, WON, LOST or DRAW
     */
    public Status getStatus() {
        return this.status;
    }
    
    /**
     * Return current player figure
     *
     * @return for x-o-toe game it returns x or o
     */
    public Figure getFigure() {
        return this.figure;
    }
    
    /**
     * Method to get name of the player.
     *
     * @return <code>String</code> name of the player
     */
    public String toString() {
        return this.name;
    }
    
    /**
     * Return <code>true</code> if player made his move.
     */
    public boolean isMadeMove() {
        return this.madeMove;
    }
    
    public void setMadeMove(boolean made) {
        this.madeMove = made;
    }
}

