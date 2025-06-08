package org.abhishek.snakeandladder.model;

public class Board {

    private final int[] boardMap;

    public Board(int size) {
        boardMap = new int[size * size+1];
    }


    public void addLadderOrSnake(int start, int end) {
        if(boardMap[start]==0) {
            boardMap[start] = end;
        } else {
            System.out.println("Multiple snakes/ladders can't have the same start/head point");
        }
    }

    public int getNextPosition(int position) {
        int nextPosition = boardMap[position];
        while(nextPosition!=0) {
            position = nextPosition;
            nextPosition = boardMap[position];
        }
        return position;
    }

    public int getFinalSize() {
        return boardMap.length;
    }
}
