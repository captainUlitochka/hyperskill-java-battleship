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

    public String validateLength() {
        if (startPoint.getX() < 1 || startPoint.getX() > 10 ||
                endPoint.getX() < 1 || endPoint.getX() > 10 ||
                startPoint.getYIndex() < 1 || startPoint.getYIndex() > 10 ||
                endPoint.getYIndex() < 1 || endPoint.getYIndex() > 10
        ) {
            errorState = "Error! Wrong ship location! Try again:\n";
        }

        if (!((getXLength() == (type.getSize() - 1) && getYLength() == 0) ||
                (getYLength() == (type.getSize() - 1) && getXLength() == 0))) {
            errorState = "Error! Wrong length of the " + type.getName() + "! Try again:\n";
        }
        return errorState;
    }

    public boolean isValid() {
        return validateLength().length() == 0;
    }
}
