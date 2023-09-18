package com.xma.chess.core;

public enum ChessType {
    NONE,
    PAWN,
    KNIGHT,
    BISHOP,
    ROOK,
    QUEEN,
    KING;

    private final int code;



    ChessType() {
        code = this.ordinal();
    }

    int getCode() {
        return code;
    }
}
