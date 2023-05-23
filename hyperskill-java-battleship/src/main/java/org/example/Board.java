package org.example;

public class Board {
    private static final int BOARD_SIZE = 10;
    private final String[][] board = new String[BOARD_SIZE + 1][BOARD_SIZE + 1];
    private static final String FOG = "~";
    private static final String SHIP = "O";
    private static final String HIT = "X";
    private static final String MISS = "M";


    public Board() {
        board[0][0] = " ";
        // Заполняем цифры по оси X
        for (int i = 1; i < BOARD_SIZE + 1; i++) {
            board[0][i] = Integer.toString(i);
        }

        // Заполняем буквы по оси Y
        for (char i = 'A'; i <= 'J'; i++) {
            board[i - 'A' + 1][0] = Character.toString(i);
        }

        // Закрашиваем доску
        for (int i = 1; i < BOARD_SIZE + 1; i++) {
            for (int j = 1; j < BOARD_SIZE + 1; j++) {
                board[i][j] = FOG;
            }

        }
    }

    public void printBoard() {
        for (int i = 0; i < BOARD_SIZE + 1; i++) {
            System.out.print("\n");
            for (int j = 0; j < BOARD_SIZE + 1; j++) {
                System.out.print(" " + board[i][j]);
            }
        }
    }


}
