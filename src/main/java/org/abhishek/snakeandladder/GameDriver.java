package org.abhishek.snakeandladder;

import org.abhishek.snakeandladder.model.Board;
import org.abhishek.snakeandladder.model.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameDriver {

    public static void main(String[] args) {
        String fileName = "src/main/java/org/abhishek/snakeandladder/input.txt"; // Path to your file

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int boardSize = Integer.parseInt(br.readLine());
            int noOfDice = Integer.parseInt(br.readLine());
            Game game = new Game(boardSize, noOfDice);

            int noOfSnakes = Integer.parseInt(br.readLine());

            Board board = game.getBoard();
            while (noOfSnakes > 0) {
                String input = br.readLine();
                String[] inputs = input.split(" ");
                int start = Integer.parseInt(inputs[0]);
                int end = Integer.parseInt(inputs[1]);
                if (start <= end) {
                    System.out.println("Snake value is not valid, please enter again");
                    continue;
                }
                board.addLadderOrSnake(start, end);
                noOfSnakes--;
            }

            //System.out.print("Enter no. of ladders: ");
            int noOfLadders = Integer.parseInt(br.readLine());
            //System.out.print("Enter " + noOfLadders + " ladders start and end positions: ");
            while (noOfLadders > 0) {
                String input = br.readLine();
                String[] inputs = input.split(" ");
                int start = Integer.parseInt(inputs[0]);
                int end = Integer.parseInt(inputs[1]);
                if (start >= end) {
                    System.out.println("Ladder value is not valid, please enter again");
                    continue;
                }
                board.addLadderOrSnake(start, end);
                noOfLadders--;
            }

            //System.out.print("Enter no. of players: ");
            int noOfPlayers = Integer.parseInt(br.readLine());

            //System.out.print("Enter " + noOfPlayers + " player names: ");
            while (noOfPlayers > 0) {
                game.addPlayer(new Player(br.readLine()));
                noOfPlayers--;
            }

            while (game.playRound()) {

            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }


    }
}
