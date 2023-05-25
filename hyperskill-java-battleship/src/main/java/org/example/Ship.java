package org.example;

public class Ship {
    Coordinate startPoint;
    Coordinate endPoint;
    ShipType type;

    byte minX;
    byte minY;
    byte maxX;
    byte maxY;

    private String errorState = "";

    public Ship(String placement, ShipType type) {
        this.startPoint = new Coordinate(placement.substring(0, placement.indexOf(" ")));
        this.endPoint = new Coordinate(placement.substring(placement.indexOf(" ") + 1));
        this.type = type;
        this.minX = (byte) Math.min(startPoint.getX(), endPoint.getX());
        this.minY = (byte) Math.min(startPoint.getYIndex(), endPoint.getYIndex());
        this.maxX = (byte) Math.max(startPoint.getX(), endPoint.getX());
        this.maxY = (byte) Math.max(startPoint.getYIndex(), endPoint.getYIndex());
    }

    public byte getMinX() {
        return minX;
    }

    public byte getMinY() {
        return minY;
    }

    public byte getMaxX() {
        return maxX;
    }

    public byte getMaxY() {
        return maxY;
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
            return (byte) (getYLength() + 1);
        } else {
            return (byte) (getXLength() + 1);
        }
    }

    public String getErrorState() {
        isValid();
        return errorState;
    }

    // Проверка, что корабль не вылезает за границы доски
    String validateBorders() {
        if (!startPoint.isValidBorder() || !endPoint.isValidBorder()) {
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

    boolean isCrossing(Ship otherShip) {
        this.isVertical();
        otherShip.isVertical();
        boolean isCrossing = false;

        if (this.isVertical() && otherShip.isVertical()) {
            //  Оба вертикальные, проверить по X пересечение если стоят на 1 и той же или на соседних по Y
            if (Math.abs(this.startPoint.getX() - otherShip.startPoint.getX()) > 1) {
                isCrossing = false;
            }
            if (Math.abs(this.startPoint.getX() - otherShip.startPoint.getX()) <= 1) {
                if (otherShip.minY >= (this.minY - 1) && otherShip.minY <= (maxY + 1)) {
                    isCrossing = true;
                }
                if (otherShip.maxY >= (this.minY - 1) && otherShip.maxY <= (maxY + 1)) {
                    isCrossing = true;
                }
            }
        }
        if (!this.isVertical() && !otherShip.isVertical()) {
            if (Math.abs(this.startPoint.getYIndex() - otherShip.startPoint.getYIndex()) > 1) {
                isCrossing = false;
            }
            if (Math.abs(this.startPoint.getYIndex() - otherShip.startPoint.getYIndex()) <= 1) {
                if (otherShip.minX >= (this.minX - 1) && otherShip.minX <= (maxX + 1)) {
                    isCrossing = true;
                }
                if (otherShip.maxX >= (this.minX - 1) && otherShip.maxX <= (maxX + 1)) {
                    isCrossing = true;
                }
            }
        }

        if (!this.isVertical() && otherShip.isVertical()) {
            if (otherShip.minX >= (this.minX - 1) && otherShip.minX <= (this.maxX + 1) &&
                    this.minY >= (otherShip.minY - 1) && this.minY <= (otherShip.maxY + 1)) {
                isCrossing = true;
            }
        }

        if (this.isVertical() && !otherShip.isVertical()) {
            if (this.minX >= (otherShip.minX - 1) && this.minX <= (otherShip.maxX + 1) &&
                    otherShip.minY >= (this.minY - 1) && otherShip.minY <= (this.maxY + 1)) {
                isCrossing = true;
            }
        }
        if (isCrossing) {
        System.out.println("\nError! You placed it too close to another one. Try again:\n"); }
        return isCrossing;
    }
}
