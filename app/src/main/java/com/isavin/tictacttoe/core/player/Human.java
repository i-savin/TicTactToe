package com.isavin.tictacttoe.core.player;

import com.isavin.tictacttoe.core.Board;
import com.isavin.tictacttoe.core.Figure;
import com.isavin.tictacttoe.core.Move;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by isavin on 02.04.16.
 */
public class Human extends Player {
    private static final Logger logger = Logger.getLogger("com.isavin.tictacttoe.core.player");

    /**
     * Initializes newly created <code>Player</code> object.
     *
     * @param name   String name of the player
     * @param figure figure type player to control
     */
    public Human(String name, Figure figure) {
        super(name, figure);
    }

    @Override
    public Move makeMove(Board board) {
        return null;
    }

    class Coordinator {
        private Move move;

        /**
         * Checks received request to find player's move.
         * If player makes move, this method return it and if not this method
         * <code>null</code>.
         *
         */
        public synchronized void putMove(int x, int y) {
//            Move currentMove = null;
//            String[] cells = request.getParameterValues("keys");
//            if (cells != null) {
//                int t = 0;
//                for (int i = 0; i < 3; i++) {
//                    for (int j = 0; j < 3; j++) {
//                        if ("marked".equalsIgnoreCase(cells[t])) {
//                            currentMove = new Move(Figure.X, i, j);
//                        }
//                        t++;
//                    }
//                }
//            }
//            if (currentMove != null) {
//                logger.log(Level.INFO, "currentMove != null");
//                this.move = currentMove;
//                notifyAll();
//            }
//            logger.log(Level.INFO, "Put: " + this.move);
        }

        /**
         * Method makes current thread wait until player makes move.
         *
         * @return <code>Move</code> object or null if player didn't make move
         */
        public synchronized Move getMove() {
            while(this.move == null) {
                try {
                    logger.log(Level.INFO, "Sleep...");
                    wait();
                } catch (InterruptedException e) {}
            }
            Move currentMove = this.move;
            logger.log(Level.INFO, "Get: " + currentMove);
            this.move = null;
            return currentMove;
        }
    }
}
