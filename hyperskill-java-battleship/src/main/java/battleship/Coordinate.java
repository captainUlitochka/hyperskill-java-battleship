package battleship;

public class Coordinate {
    public char y;
    public byte x;

    public Coordinate(String coordinate) {
        this.y = coordinate.charAt(0);
        this.x = Byte.parseByte(coordinate.substring(1));
    }

    public byte getYIndex() {
        return (byte) ((byte) y - (byte) 'A' + 1);
    }

    public byte getX() {
        return x;
    }

    public boolean isValidBorder() {
        return (getYIndex() >= 1 && getYIndex() <= Board.BOARD_SIZE &&
                getX() >= 1 && getX() <= Board.BOARD_SIZE);
    }

    public String getErrorState() {
        if (!isValidBorder()) {
            return "\nError! You entered the wrong coordinates! Try again:\n";
        }
        return "";
    }
}
