package org.abhishek.snakeandladder;

import org.abhishek.snakeandladder.model.Board;
import org.abhishek.snakeandladder.model.Dice;
import org.abhishek.snakeandladder.model.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {
    private final Board board;
    private final List<Player> players;
    private final Dice dice;
    private int winPosition;


    public Game(int boardSize, int noOfDice) {
        this.board = Board.getInstance(boardSize);
        players = new ArrayList<>();
        dice = new Dice(noOfDice);
        winPosition = 1;
    }

    public boolean playRound() {
        if (players.size() > 1) {
            Iterator<Player> iterator = players.iterator();
            while (iterator.next()!=null) {
                Player player = iterator.next();
                int nextPosition = player.play(this);
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

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public Board getBoard() {
        return board;
    }

    public Dice getDice() {
        return dice;
    }

}
