package com.isavin.tictacttoe.core;

/**
 * Created by isavin on 17.04.16.
 */
public enum Difficulty {
    AMATEUR(1),
    BEGINNER(3),
    PROFESSIONAL(6),
    WORLD_CLASS(9);

    Difficulty(int deep) {
        this.deep = deep;
    }

    private int deep;

    public int getDeep() {
        return deep;
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
