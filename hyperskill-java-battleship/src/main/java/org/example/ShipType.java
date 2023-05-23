package org.example;

public enum ShipType {
    /*
     Aircraft Carrier is 5 cells,
     Battleship is 4 cells,
     Submarine is 3 cells,
     Cruiser is 3 cells,
     Destroyer is 2 cells.
     */
    CARRIER(5, "Aircraft Carrier"),
    BATTLESHIP(4, "Battleship"),
    SUBMARINE(3, "Submarine"),
    CRUISER(3, "Cruiser"),
    DESTROYER(2, "Destroyer");

    private final int size;
    private final String name;

    ShipType(int size, String name) {
        this.size = size;
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }
}
