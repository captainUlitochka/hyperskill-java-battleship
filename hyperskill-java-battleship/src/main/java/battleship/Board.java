package battleship;

import java.util.Scanner;

public class Board {
    public static final int BOARD_SIZE = 10;
    private static final int SHIP_COUNT = 5;
    private final String[][] board = new String[BOARD_SIZE + 1][BOARD_SIZE + 1];
    private static final String FOG = "~";
    public static final String SHIP = "O";
    private static final String HIT = "X";
    private static final String MISS = "M";
    Ship[] ships = new Ship[SHIP_COUNT];

    Scanner scanner = new Scanner(System.in);

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

    public void printBoard(boolean isHidden) {
        for (int i = 0; i < BOARD_SIZE + 1; i++) {
            System.out.print("\n");
            for (int j = 0; j < BOARD_SIZE + 1; j++) {
                if (!isHidden) {
                    System.out.print(" " + board[i][j]);
                } else {
                    if (board[i][j].equals(HIT) || board[i][j].equals(MISS) || i == 0 || j == 0) {
                        System.out.print(" " + board[i][j]);
                    } else {
                        System.out.print(" " + FOG);
                    }
                }
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
        Ship ship;
        boolean isCrossing;
        // Запрос координат корабля
        do {
            isCrossing = false;
            System.out.printf("\nEnter the coordinates of the %s (%d cells):\n", type.getName(), type.getSize());
            ship = new Ship(scanner.nextLine(), type);
            System.out.println(ship.getErrorState());
            for (int i = 0; i < shipIndex; i++) {
                isCrossing = isCrossing || ship.isCrossing(ships[i]);
            }
        } while (!ship.isValid() || isCrossing);
        ships[shipIndex] = ship;

        // Рисование кораблей на карте
        if (!ship.isVertical()) {
            for (int i = ship.minX; i <= ship.maxX; i++) {
                board[ship.minY][i] = SHIP;
            }
            printBoard(false);
        } else {
            for (int i = ship.minY; i <= ship.maxY; i++) {
                board[i][ship.minX] = SHIP;
            }
            printBoard(false);
        }
    }

    public void takeAShot() {
        // Reading coordinates from player
        Coordinate coordinate = processPlayersInputCoordinates();

        // Painting a hit or miss and show a message about shot's result
        String shotValue = board[coordinate.getYIndex()][coordinate.getX()];
        if (shotValue.equals(SHIP) || shotValue.equals(HIT)) {
            board[coordinate.getYIndex()][coordinate.getX()] = HIT;
            printBoard(true);
            int hitCount = countDestroyedParts(coordinate);
            System.out.println(printSuccessfulShotResult(hitCount));
        } else if (shotValue.equals(FOG) || shotValue.equals(MISS)) {
            board[coordinate.getYIndex()][coordinate.getX()] = MISS;
            printBoard(true);
            System.out.println("\nYou missed. Try again:\n");
        }
    }

    // Take an input and check if it's correct
    private Coordinate processPlayersInputCoordinates() {
        Coordinate coordinate;
        do {
            coordinate = new Coordinate(scanner.next());
            System.out.print(coordinate.getErrorState());
        } while (coordinate.isValidBorder());
        return coordinate;
    }

    // Counting destroyed parts of player's ship
    private int countDestroyedParts(Coordinate coordinate) {
        int hitIndex = -1;
        for (int i = 0; i < ships.length; i++) {
            if (ships[i].isShipHitted(coordinate)) {
                hitIndex = i;
            }
        }
        return hitIndex;
    }

    private String printSuccessfulShotResult(int hitCount) {
        String message = "\nYou hit a ship! Try again:\n";
        if (isAliveShipExists()) {
            if (!ships[hitCount].isAlive()) message = "\nYou sank a ship! Specify a new target:\n";
        } else {
            message = "\nYou sank the last ship. You won. Congratulations!\n";
        }
        return message;
    }

    boolean isAliveShipExists() {
        boolean isAlive = false;
        for (Ship ship : ships) {
            isAlive = isAlive || ship.isAlive();
        }
        return isAlive;
    }
}