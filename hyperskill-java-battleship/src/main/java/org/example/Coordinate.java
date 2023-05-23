package org.example;

public class Coordinate {
    public char y;
    public byte x;

    public Coordinate(String coordinate) {
        this.y = coordinate.charAt(0);
        this.x = Byte.parseByte(coordinate.substring(1));
    }

    public byte getYIndex() {
        return (byte) ((byte)y - (byte)'A' + 1);
    }

    public byte getX() {
        return x;
    }
}
