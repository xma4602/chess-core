package com.xma.chess.core;

public class Board {

    private byte[] board;

    public Board() {
        board = new byte[64];

        setFigure(ChessType.ROOK, true, 0);
        setFigure(ChessType.ROOK, true, 7);
        setFigure(ChessType.ROOK, false, 56);
        setFigure(ChessType.ROOK, false, 63);

        setFigure(ChessType.KNIGHT, true, 1);
        setFigure(ChessType.KNIGHT, true, 6);
        setFigure(ChessType.KNIGHT, false, 57);
        setFigure(ChessType.KNIGHT, false, 62);

        setFigure(ChessType.BISHOP, true, 2);
        setFigure(ChessType.BISHOP, true, 5);
        setFigure(ChessType.BISHOP, false, 58);
        setFigure(ChessType.BISHOP, false, 61);

        setFigure(ChessType.QUEEN, true, 3);
        setFigure(ChessType.QUEEN, false, 59);

        setFigure(ChessType.KING, true, 4);
        setFigure(ChessType.KING, false, 60);

        for (int i = 0; i < 8; i++) {
            setFigure(ChessType.PAWN, true, 8 + i);
            setFigure(ChessType.PAWN, false, 48 + i);
        }
    }

    private Board(byte[] board) {
        this.board = board;
    }


    private void setFigure(ChessType type, boolean isWhite, int position) {
        board[position] = getFigure(type, isWhite);
    }

    private byte getFigure(ChessType type, boolean isWhite) {
        return (byte) (type.getCode() | (isWhite ? 8 : 0));
    }


    public boolean isWhite(int position) {
        return (board[position] >> 2 & 1) >= 1;
    }

    public boolean isWhite(int col, int row) {
        return isWhite(row * 8 + col);
    }

    public boolean isWhite(String cell) {
        return (board[cellToPosition(cell)] >> 2 & 1) >= 1;
    }


    public boolean isEmpty(int position) {
        return board[position] == 0;
    }

    public boolean isEmpty(int col, int row) {
        return isEmpty(row * 8 + col);
    }

    public boolean isEmpty(String cell) {
        return isEmpty(cellToPosition(cell));
    }


    public ChessType type(int position) {
        int code = board[position] & 0b00000111;
        for (ChessType v : ChessType.values())
            if (v.getCode() == code) return v;
        return ChessType.NONE;
    }

    public ChessType type(int col, int row) {
        return type(row * 8 + col);
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
                if (isEmpty(col, row)) {
                    int pos = (row * 8 + col);
                    pos = (pos / 8 + pos % 8) % 2;
                    builder.append(pos == 1 ? "◼" : "◻");
                    if (col % 4 == 1) builder.append(' ');
                } else {
                    boolean isWhite = isWhite(col, row);
                    switch (type(col, row)) {
                        case PAWN -> builder.append(isWhite ? '♙' : '♟');
                        case KNIGHT -> builder.append(isWhite ? '♘' : '♞');
                        case BISHOP -> builder.append(isWhite ? '♗' : '♝');
                        case ROOK -> builder.append(isWhite ? '♖' : '♜');
                        case QUEEN -> builder.append(isWhite ? '♕' : '♛');
                        case KING -> builder.append(isWhite ? '♔' : '♚');
                    }
                }
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
