package org.abhishek.snakeandladder.model;

import org.abhishek.snakeandladder.Game;

public class AIPlayer extends Player {


    public AIPlayer(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int play(Game game) {
        int currentPosition = this.getCurrentPosition();
        int diceRoll = game.getDice().roll(this);
        int nextPosition = currentPosition + diceRoll;

        Board board = game.getBoard();
        if (nextPosition >= board.getFinalSize()) {
            nextPosition = currentPosition;
        } else {
            nextPosition = board.getNextPosition(nextPosition);
            this.setCurrentPosition(nextPosition);
        }
        System.out.println("AI Player " + this.name + " rolled a " + diceRoll + " and moved from " + currentPosition + " to " + nextPosition);

        return nextPosition;
    }
}
