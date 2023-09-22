package com.xma.chess.core;

public enum ChessType {
    NONE,
    PAWN,
    KNIGHT,
    BISHOP,
    ROOK,
    QUEEN,
    KING;

    int getCode() {
        return this.ordinal();
    }

    public static ChessType getByCode(int code){
        if (code < 0 || code > 6)
            throw  new IllegalArgumentException("Not exist figure with this code");
        return ChessType.values()[code];
    }

    public char toChar(boolean isWhite){
        return switch (this) {
            case NONE -> isWhite ? '◼' : '◻';
            case PAWN -> isWhite ? '♙' : '♟';
            case KNIGHT -> isWhite ? '♘' : '♞';
            case BISHOP -> isWhite ? '♗' : '♝';
            case ROOK -> isWhite ? '♖' : '♜';
            case QUEEN -> isWhite ? '♕' : '♛';
            case KING -> isWhite ? '♔' : '♚';
        };
    }
}
