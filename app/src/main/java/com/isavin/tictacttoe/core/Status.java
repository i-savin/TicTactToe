package com.isavin.tictacttoe.core;

/**
 * Enumeration class <code>Status</code> represents player's status.
 *
 * @author Ilia Savin
 * @version
 */
public enum Status {
    /**
     * Player got this status if he won the party.
     */
    WON,
    /**
     * Player got this status if the party finishes with draw.
     */
    DRAW,
    /**
     * Player got this status if he lost the party.
     */
    LOST,
    /**
     * This status indicates that party is not over and player may make move.
     */
    PLAYING;
}

