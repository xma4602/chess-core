package com.xma.chess.core;

public class Board {

    private byte[] board;

    public Board() {
        board = new byte[64];

        for (int row = 0; row < 8; row += 7) {
            setFigure(ChessType.ROOK, row == 0, row, 0);
            setFigure(ChessType.ROOK, row == 0, row, 7);

            setFigure(ChessType.KNIGHT, row == 0, row, 1);
            setFigure(ChessType.KNIGHT, row == 0, row, 6);

            setFigure(ChessType.BISHOP, row == 0, row, 2);
            setFigure(ChessType.BISHOP, row == 0, row, 5);

            setFigure(ChessType.QUEEN, row == 0, row, 3);
            setFigure(ChessType.KING, row == 0, row, 4);

            for (int col = 0; col < 8; col++) {
                setFigure(ChessType.PAWN, row < 4, Math.abs(1 - row), col);
            }
        }

        for (int row = 2; row < 6; row++) {
            for (int col = 0; col < 8; col++) {
                boolean color = (row % 2 + col % 2) == 1;
                setFigure(ChessType.NONE, color, row, col);
            }
        }
    }

    private Board(byte[] board) {
        this.board = board;
    }


    private void setFigure(ChessType type, boolean isWhite, int position) {
        board[position] = getFigure(type, isWhite);
    }

    private void setFigure(ChessType type, boolean isWhite, int row, int col) {
        setFigure(type, isWhite, coordinatesToPosition(row, col));
    }

    private byte getFigure(ChessType type, boolean isWhite) {
        int code = type.getCode();
        int color = isWhite ? 8 : 0;
        int value = code | color;
        return (byte) value;
    }


    public boolean isWhite(int position) {
        return (board[position] & 8) >= 1;
    }

    public boolean isWhite(int row, int col) {
        return isWhite(coordinatesToPosition(row, col));
    }

    public boolean isWhite(String cell) {
        return (board[cellToPosition(cell)] >> 2 & 1) >= 1;
    }


    public boolean isEmpty(int position) {
        return board[position] == 0;
    }

    public boolean isEmpty(int row, int col) {
        return isEmpty(coordinatesToPosition(row, col));
    }

    private static int coordinatesToPosition(int row, int col) {
        return row * 8 + col;
    }

    public boolean isEmpty(String cell) {
        return isEmpty(cellToPosition(cell));
    }


    public ChessType type(int position) {
        int code = board[position] & 7;
        return ChessType.getByCode(code);
    }

    public ChessType type(int row, int col) {
        return type(coordinatesToPosition(row, col));
    }

    public ChessType type(String cell) {
        return type(cellToPosition(cell));
    }


    public void print() {
        String letters = "  A  B  C D  E F  G  H\n";
        StringBuilder builder = new StringBuilder();
        builder.append(letters);
        builder.append(" ╔═════════════════════╗\n");
        for (int row = 7; row >= 0; row--) {
            builder.append(row + 1).append("║");
            for (int col = 0; col <= 7; col++) {
                ChessType type = type(row, col);
                builder.append(type.toChar(isWhite(row, col)));
                if (type == ChessType.NONE && col % 4 == 1) builder.append(' ');
                builder.append(' ');
            }
            builder.append("║").append(row + 1).append('\n');
        }
        builder.append(" ╚═════════════════════╝\n");
        builder.append(letters);

        System.out.println(builder);
    }

    public Board reverse() {
        byte[] desk = new byte[64];
        for (int i = 0; i < 16; i++) {
            desk[i] = board[63 - 2 * (i / 8) - (i % 8)];
        }
        return new Board(desk);
    }

    public static int cellToPosition(String cell) {
        return (cell.charAt(0) - 'a') + (cell.charAt(1) - '1') * 8;
    }

    public static String positionToCell(int position) {
        return String.valueOf((char) ((position % 8) + 'a')) + (char) ((position / 8) + '1');
    }


}
