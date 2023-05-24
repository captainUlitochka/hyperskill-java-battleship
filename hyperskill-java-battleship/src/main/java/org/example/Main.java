package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        board.printBoard();
        board.askForShip();
        //TODO: Выводить сообщение о начале игры
        board.takeAShot(scanner.nextLine());
        //TODO: Предлагать ввод с новой строки
    }
}
