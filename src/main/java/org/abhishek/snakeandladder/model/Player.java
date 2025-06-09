package org.abhishek.snakeandladder.model;

import org.abhishek.snakeandladder.Game;

public abstract class Player {

    protected final String name;
    private int currentPosition;

    public Player(String name) {
        this.name = name;
        this.currentPosition = 0;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int position) {
        this.currentPosition = position;
    }

    public abstract String getName();

    public abstract int play(Game game);
}
