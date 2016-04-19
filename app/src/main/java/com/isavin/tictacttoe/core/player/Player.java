package com.isavin.tictacttoe.core.player;

import com.isavin.tictacttoe.core.Board;
import com.isavin.tictacttoe.core.Figure;
import com.isavin.tictacttoe.core.Move;

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

    public Player(String name, Figure figure) {
        this.name = name;
        this.figure = figure;
        status = Status.PLAYING;
    }

    public abstract Move makeMove(Board board);

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

    public Figure getFigure() {
        return this.figure;
    }

    public String toString() {
        return this.name;
    }

    public boolean isMadeMove() {
        return this.madeMove;
    }
    
    public void setMadeMove(boolean made) {
        this.madeMove = made;
    }

    /**
     * Enumeration class <code>Status</code> represents player's status.
     *
     */
    public enum Status {
        WON,
        DRAW,
        LOST,
        PLAYING;
    }

    public String getName() {
        return name;
    }
}

