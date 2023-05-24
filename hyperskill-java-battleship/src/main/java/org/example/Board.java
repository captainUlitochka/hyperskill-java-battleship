package org.example;

import java.util.Scanner;

public class Board {
    private static final int BOARD_SIZE = 10;
    private static final int SHIP_COUNT = 5;
    private final String[][] board = new String[BOARD_SIZE + 1][BOARD_SIZE + 1];
    private static final String FOG = "~";
    private static final String SHIP = "O";
    private static final String HIT = "X";
    private static final String MISS = "M";
    Ship[] ships = new Ship[SHIP_COUNT];


    public Board() {
        board[0][0] = " "; // [по вертикали] [по горизонтали]
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

    public void askForShip() {
        byte shipIndex = 0;
        for (ShipType shipType : ShipType.values()) {
            addShipToBoard(shipType, shipIndex);
            shipIndex++;
        }
    }

    void addShipToBoard(ShipType type, byte shipIndex) {
        Scanner scanner = new Scanner(System.in);
        Ship ship;
        // Запрос координат корабля
        do {
            System.out.printf("\nEnter the coordinates of the %s (%d cells):\n", type.getName(), type.getSize());
            ship = new Ship(scanner.nextLine(), type);
            System.out.println(ship.getErrorState());
        } while (!ship.isValid());
        ships[shipIndex] = ship;

        // Рисование кораблей на карте
        if (!ship.isVertical()) {
            for (int i = ship.startPoint.getX(); i < ship.startPoint.getX() + ship.getLength(); i++) {
                board[ship.startPoint.getYIndex()][i] = SHIP;
            }
        } else {
            for (int i = ship.startPoint.getYIndex(); i < ship.startPoint.getYIndex() + ship.getLength(); i++) {
                board[i][ship.startPoint.getX()] = SHIP;
            }
            printBoard();
        }
    }

}
