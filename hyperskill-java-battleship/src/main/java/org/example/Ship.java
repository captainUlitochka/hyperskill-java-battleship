package org.example;

public enum Ship {
    /*
     Aircraft Carrier is 5 cells,
     Battleship is 4 cells,
     Submarine is 3 cells,
     Cruiser is 3 cells,
     Destroyer is 2 cells.
     */
    CARRIER(5), BATTLESHIP(4), SUBMARINE(3), CRUISER(3), DESTROYER(2);

    private final int size;

    Ship(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
