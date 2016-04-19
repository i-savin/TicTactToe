package com.isavin.tictacttoe.core.player;

import com.isavin.tictacttoe.core.Board;
import com.isavin.tictacttoe.core.Difficulty;
import com.isavin.tictacttoe.core.Figure;
import com.isavin.tictacttoe.core.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


public class ArtificialIntelligence extends Player {

    public static final int MAX_DEPTH = Difficulty.WORLD_CLASS.getDepth();
    private Move choice;
    private int maxDepth;

    public ArtificialIntelligence(Figure figure, int maxDepth) {
        super("Skynet", figure);
        this.maxDepth = maxDepth;
    }

    public ArtificialIntelligence(Figure figure) {
        this(figure, MAX_DEPTH);
    }

    @Override
    public Move makeMove(Board board) {
        int availableMovesNumber = board.getAvailableMovesNumber();
        alphaBeta(board, figure, 0, -availableMovesNumber-1, availableMovesNumber+1);
        return choice;
    }

    private int alphaBeta(Board board, Figure figure, int depth, int alpha, int beta) {
        if (isStateOver(board, depth)) {
            return score(board, depth);
        }
        class Candidate implements Comparable<Candidate> {
            private int score;
            private Move move;

            Candidate(int score, Move move) {
                this.score = score;
                this.move = move;
            }

            @Override
            public int compareTo(Candidate another) {
                return this.score - another.score;
            }
        }
        TreeSet<Candidate> candidates = new TreeSet<>();
        List<Move> availableMoves = board.getAvailableMoves();
        for (int i = 0; i < availableMoves.size(); i++) {
            Move move = availableMoves.get(i);
            move.setFigure(figure);
            Board possibleBoard = board.getNewState(move);
            int score = alphaBeta(possibleBoard, figure.opposite(), depth + 1, alpha, beta);
            if (figure == this.figure) {
                candidates.add(new Candidate(score, move));
                if (score > alpha) {
                    alpha = score;
                }
            } else {
                if (score < beta) {
                    beta = score;
                }
            }
            if (alpha > beta) {
                break;
            }
        }

        if (figure != this.figure) {
            return beta;
        } else {
            choice = candidates.last().move;
            return alpha;
        }
    }

    private int minimax(Board board, Figure figure, int depth) {
        if (isStateOver(board, depth)) {
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

    private boolean isStateOver(Board board, int depth) {
        return won(board, figure) || won(board, figure.opposite()) 
                || board.getAvailableMovesNumber() == 0 || depth == maxDepth;
    }

    private int score(Board board, int depth) {
        if (won(board, figure)) {
            return maxDepth - depth;
        } else if (won(board, figure.opposite())) {
            return depth - maxDepth;
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
