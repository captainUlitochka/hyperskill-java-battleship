package org.example;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard(false);
        board.askForShip();
        board.printStartState();
        board.takeAShot();
    }
}