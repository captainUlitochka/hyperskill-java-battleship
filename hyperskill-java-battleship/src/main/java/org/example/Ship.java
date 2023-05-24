package org.example;

public class Ship {
    Coordinate startPoint;
    Coordinate endPoint;
    ShipType type;

    private String errorState = "";
    public Ship(String placement, ShipType type) {
        this.startPoint = new Coordinate(placement.substring(0, placement.indexOf(" ")));
        this.endPoint = new Coordinate(placement.substring(placement.indexOf(" ") + 1));
        this.type = type;
    }

    byte getXLength() {
        return (byte) Math.abs(startPoint.getX() - endPoint.getX());
    }

    byte getYLength() {
        return (byte) Math.abs(startPoint.getYIndex() - endPoint.getYIndex());
    }

    boolean isVertical() {
        return getYLength() > 0;
    }

    byte getLength() {
        if (isVertical()) {
            System.out.println("Длина по Y: " + getYLength() + 1); //TODO: удалить отладочное
            return (byte) (getYLength() + 1);
        } else {
            System.out.println("Длина по Х: " + getXLength() + 1); //TODO: удалить отладочное
            return (byte) (getXLength() + 1);
        }
    }

    public String getErrorState() {
        isValid();
        return errorState;
    }

    // Проверка, что корабль не вылезает за границы доски
    String validateBorders() {
        if (startPoint.getX() < 1 || startPoint.getX() > 10 ||
                endPoint.getX() < 1 || endPoint.getX() > 10 ||
                startPoint.getYIndex() < 1 || startPoint.getYIndex() > 10 ||
                endPoint.getYIndex() < 1 || endPoint.getYIndex() > 10
        ) {
            errorState = "Error! Wrong ship location! Try again:\n";
        }
        return errorState;
    }

    // Проверка, что координаты корабля соответсвуют его дефолтной длине
    String validateLength() {
        if (!((getXLength() == (type.getSize() - 1) && getYLength() == 0) ||
                (getYLength() == (type.getSize() - 1) && getXLength() == 0))) {
            errorState = "Error! Wrong length of the " + type.getName() + "! Try again:\n";
        }
        return errorState;
    }

    public boolean isValid() {
        return validateLength().length() == 0 && validateBorders().length() == 0;
    }

    boolean isCrossing() {
        return false;
    }

    boolean isTooClose() {
        return false;
        // Check the field with coordinates (start row - 1) to (end row + 1) and (start column -1) to (end column +1) for occupied characters.
    }
}
