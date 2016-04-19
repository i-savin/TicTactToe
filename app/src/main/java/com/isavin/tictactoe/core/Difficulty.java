package com.isavin.tictactoe.core;

public enum Difficulty {
    AMATEUR(1),
    BEGINNER(3),
    PROFESSIONAL(6),
    WORLD_CLASS(9);

    Difficulty(int depth) {
        this.depth = depth;
    }

    private int depth;

    public int getDepth() {
        return depth;
    }

    public static Difficulty getDifficultyById(int id) {
        switch (id) {
            case 0:
                return AMATEUR;
            case 1:
                return BEGINNER;
            case 2:
                return PROFESSIONAL;
            default:
                return WORLD_CLASS;
        }
    }
}
