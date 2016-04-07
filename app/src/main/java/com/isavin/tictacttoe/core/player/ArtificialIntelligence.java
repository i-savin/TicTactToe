package com.isavin.tictacttoe.core.player;

import com.isavin.tictacttoe.core.Board;
import com.isavin.tictacttoe.core.Figure;
import com.isavin.tictacttoe.core.Move;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isavin on 02.04.16.
 */
public class ArtificialIntelligence extends Player {

    private Move choice;
    private int baseScore;

    public ArtificialIntelligence(Figure figure) {
        super("Skynet", figure);
    }

    @Override
    public Move makeMove(Board board) {
        minimax(board, figure, 0);
        return choice;
    }

    private int minimax(Board board, Figure figure, int depth) {
        if (isTerminal(board, depth)) {
            return score(board, depth);
        }
        List<Integer> scores = new ArrayList<>();
        List<Move> moves = new ArrayList<>();
        List<Move> availableMoves = board.getAvailableMoves();
        for (int i = 0; i < availableMoves.size(); i++) {
            Move move = availableMoves.get(i);
            move.setFigure(figure);
            Board possibleBoard = board.getNewState(move);
            scores.add(minimax(possibleBoard, figure.opposite(), depth + 1));
            moves.add(move);
        }

        if (figure == this.figure) {
            int maxIndex = 0;
            int max = scores.get(maxIndex);
            for (int i = 0; i < scores.size(); i++) {
                if (scores.get(i) > max) {
                    maxIndex = i;
                    max = scores.get(i);
                }
            }
            choice = moves.get(maxIndex);
            return max;
        } else {
            int minIndex = 0;
            int min = scores.get(minIndex);
            for (int i = 0; i < scores.size(); i++) {
                if (scores.get(i) < min) {
                    minIndex = i;
                    min = scores.get(i);
                }
            }
            choice = moves.get(minIndex);
            return min;
        }
    }

    private boolean isTerminal(Board board, int depth) {
        return won(board, figure) || won(board, figure.opposite()) 
                || board.getAvailableMovesNumber() == 0 || depth == 8;
    }

    private int score(Board board, int depth) {
        if (won(board, figure)) {
            return 10 - depth;
        } else if (won(board, figure.opposite())) {
            return depth - 10;
        }
        return 0;
    }

    private boolean won(Board board, Figure wonFigure) {
        char[][] field = board.getGameField();
        char symbol = wonFigure.toChar();

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
}
