package com.xma.chess.core;

public class Position {
    private final int position;

    public Position(int position) {
        if (verify(position)) {
            throw new IllegalArgumentException("Value of position must be in range [0, 63], but was " + position);
        }
        this.position = position;
    }

    public Position(int row, int column) {
        if (row < 0 || 7 < row) {
            throw new IllegalArgumentException("Value of row must be in range [0, 7], but was " + row);
        }
        if (column < 0 || 7 < column) {
            throw new IllegalArgumentException("Value of column must be in range [0, 7], but was " + column);
        }
        this.position = coordinatesToPosition(row, column);
    }

    public Position(String cell) {
        cell = cell.toLowerCase().strip();
        if (cell.length() != 2) {
            throw new IllegalArgumentException("The string length of the position should be 2, but was " + cell.length());
        }

        char column = cell.charAt(0);
        if (column < 'a' || 'h' < column) {
            throw new IllegalArgumentException("Value of column must be in range [a, h], but was " + column);
        }
        char row = cell.charAt(1);
        if (row < '1' || '8' < row) {
            throw new IllegalArgumentException("Value of row must be in range [1, 8], but was " + row);
        }

        this.position = celToPosition(cell);
    }


    public int getPosition() {
        return position;
    }

    public int getRow() {
        return position / 8;
    }

    public int getColumn() {
        return position % 8;
    }

    public String getCell() {
        return String.valueOf((char) ((position % 8) + 'a')) + (char) ((position / 8) + '1');
    }


    public Position left(boolean forWhite) {
        int pos = forWhite ? position + 1 : position - 1;
        if (verify(pos)) return new Position(pos);
        else return null;
    }

    public Position right(boolean forWhite) {
        int pos = forWhite ? position - 1 : position + 1;
        if (verify(pos)) return new Position(pos);
        else return null;
    }

    public Position top(boolean forWhite) {
        int pos = forWhite ? position + 8 : position - 8;
        if (verify(pos)) return new Position(pos);
        else return null;
    }

    public Position bottom(boolean forWhite) {
        int pos = forWhite ? position - 8 : position + 8;
        if (verify(pos)) return new Position(pos);
        else return null;
    }

    public Position topDouble(boolean forWhite) {
        int pos = forWhite ? position + 16 : position - 16;
        if (verify(pos)) return new Position(pos);
        else return null;
    }


    public Position leftTop(boolean forWhite) {
        int pos = forWhite ? position + 7 : position - 7;
        if (verify(pos)) return new Position(pos);
        else return null;
    }

    public Position leftBottom(boolean forWhite) {
        int pos = forWhite ? position - 9 : position + 9;
        if (verify(pos)) return new Position(pos);
        else return null;
    }

    public Position rightTop(boolean forWhite) {
        int pos = forWhite ? position + 9 : position - 9;
        if (verify(pos)) return new Position(pos);
        else return null;
    }

    public Position rightBottom(boolean forWhite) {
        int pos = forWhite ? position - 7 : position + 7;
        if (verify(pos)) return new Position(pos);
        else return null;
    }


    public static boolean verify(int position) {
        return position < 0 || 63 < position;
    }

    public static int coordinatesToPosition(int row, int column) {
        return row * 8 + column;
    }

    public static int celToPosition(String cell) {
        return (cell.charAt(0) - 'a') + (cell.charAt(1) - '1') * 8;
    }

}
