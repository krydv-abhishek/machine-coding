package org.abhishek.snakeandladder;

import org.abhishek.snakeandladder.model.Board;
import org.abhishek.snakeandladder.model.Dice;
import org.abhishek.snakeandladder.model.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private int winPosition;


    public Game(int boardSize, int noOfDice) {
        this.board = new Board(boardSize);
        players = new ArrayList<>();
        dice = new Dice(noOfDice);
        winPosition = 1;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public boolean playRound() {

        if (players.size() > 1) {
            Iterator<Player> iterator = players.iterator();
            while (iterator.hasNext()) {
                Player player = iterator.next();
                int currentPosition = player.getCurrentPosition();
                int diceRoll = dice.roll();
                int nextPosition = currentPosition + diceRoll;

                if (nextPosition >= board.getFinalSize()) {
                    nextPosition = currentPosition;
                } else {
                    nextPosition = board.getNextPosition(nextPosition);
                    player.setCurrentPosition(nextPosition);
                }
                System.out.println(player.getName() + " rolled a " + diceRoll + " and moved from " + currentPosition + " to " + nextPosition);

                if (nextPosition == board.getFinalSize()-1) {
                    iterator.remove();
                    System.out.println(player.getName() + " wins at position " + winPosition);
                    winPosition++;
                }
            }
            return true;
        } else {
            System.out.println("Game ends.");
            return false;
        }
    }

    public Board getBoard() {
        return board;
    }
}
