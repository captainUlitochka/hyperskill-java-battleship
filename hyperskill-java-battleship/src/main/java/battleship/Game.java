package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    Board firstPlayer = new Board();
    Board secondPlayer = new Board();
    List<Board> players = new ArrayList<>(2);

    void startGame() {
        players.add(firstPlayer);
        players.add(secondPlayer);
        fillMap();
    }

    // Let players fill their maps
    public void fillMap() {
        for (int i = 0; i < players.size(); i++) {
            System.out.printf("\nPlayer %d, place your ships on the game field\n", i + 1);
            players.get(i).printBoard(false);
            players.get(i).askForShip();
            passMove();
        }
    }

    // Show boards and let player take a shot
    public void makeMove() {
        int turn = 0;
        do {
            players.get((turn + 1) % 2).printBoard(true);
            System.out.println("\n---------------------\n");
            players.get(turn % 2).printBoard(false);

            System.out.printf("\nPlayer %d, it's your turn:\n", (turn + 1) % 2);
            players.get((turn + 1) % 2).takeAShot();
            passMove();
            turn++;
        } while (players.get((turn + 1) % 2).isAliveShipExists());

    }

    private void passMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPress Enter and pass the move to another player\n");
        scanner.nextLine();
    }
}