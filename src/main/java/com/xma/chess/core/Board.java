package com.xma.chess.core;

import com.xma.chess.core.actions.Action;
import com.xma.chess.core.actions.ActionEat;
import com.xma.chess.core.actions.ActionMove;
import com.xma.chess.core.actions.ActionSwap;

public class Board {

    public static final int MASK_MOVE = 16;
    public static final int MASK_COLOR = 8;
    public static final int MASK_TYPE = 7;
    public static final int MASK_DOUBLE_MOVE = 32;

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
        setFigure(type, isWhite, Position.coordinatesToPosition(row, col));
    }

    private byte getFigure(ChessType type, boolean isWhite) {
        int code = type.getCode();
        int color = isWhite ? 8 : 0;
        int value = code | color;
        return (byte) value;
    }

    public void make(Action action) {
        switch (action.getActionType()) {
            case MOVE, DOUBLE_MOVE -> {
                ActionMove actionMove = (ActionMove) action;
                move(actionMove.getStartPosition(), actionMove.getEndPosition());
            }
            case EAT, TAKING -> {
                ActionEat actionEat = (ActionEat) action;
                eat(actionEat.getStartPosition(), actionEat.getEndPosition(), actionEat.getEatenPosition());
            }
            case SWAP -> {
                ActionSwap actionSwap = (ActionSwap) action;
                swap(actionSwap.getStartPosition(), actionSwap.getEndPosition(), actionSwap.getSwapType(), actionSwap.isWhite());
            }
            case CASTLING -> {
                ActionCastling actionCastling = (ActionCastling) action;
                move(actionCastling.getStartPosition(), actionCastling.getEndPosition());
                move(actionCastling.getRookStartPosition(), actionCastling.getRookEndPosition());
            }
        }
    }


    public boolean isWhite(Position position) {
        return (board[position.getPosition()] & MASK_COLOR) >= 1;
    }

    public boolean isLastLine(Position position) {
        return (position.getRow() == 0) || (position.getRow() == 7);
    }

    public boolean wasMoving(Position position) {
        return (board[position.getPosition()] & MASK_MOVE) > 0;
    }

    public boolean wasDoubleMove(Position position) {
        return (board[position.getPosition()] & MASK_DOUBLE_MOVE) > 0;
    }

    public ChessType type(Position position) {
        return ChessType.getByCode(board[position.getPosition()] & MASK_TYPE);
    }

    public void print(){
        print(false);
    }
    public void print(boolean reverse) {
        StringBuilder b = new StringBuilder("A  B  C D  E F  G  H");
        if (reverse) b = b.reverse();

        StringBuilder builder = new StringBuilder("  ");
        builder.append(b);
        builder.append("\n ╔═════════════════════╗\n");
        for (int row = 7; row >= 0; row--) {
            if (reverse) builder.append(9-row);
            else builder.append(row + 1);
            builder.append("║");
            for (int col = 0; col <= 7; col++) {
                ChessType type = type(row * 8 + col);
                builder.append(type.toChar(isWhite(row * 8 + col)));
                if (type == ChessType.NONE && col % 4 == 1) builder.append(' ');
                builder.append(' ');
            }
            builder.append("║");
            if (reverse) builder.append(9-row);
            else builder.append(row + 1);
            builder.append('\n');
        }
        builder.append(" ╚═════════════════════╝\n  ");
        builder.append(b);

        System.out.println(builder);
    }

    public Board reverse() {
        byte[] desk = new byte[64];
        for (int i = 0; i < 16; i++) {
            desk[i] = board[63 - 2 * (i / 8) - (i % 8)];
        }
        return new Board(desk);
    }


    private void swap(Position startPosition, Position endPosition, ChessType swapType, boolean white) {
    }

    private void eat(Position startPosition, Position endPosition, Position eatenPosition) {
    }

    private void move(Position startPosition, Position endPosition) {
    }


    private boolean isWhite(int position) {
        return (board[position] & MASK_COLOR) >= 1;
    }

    private boolean isLastLine(int position) {
        return (position / 8 == 0) || (position / 8 == 7);
    }

    private boolean wasMoving(int position) {
        return (board[position] & MASK_MOVE) > 0;
    }

    private boolean wasDoubleMove(int position) {
        return (board[position] & MASK_DOUBLE_MOVE) > 0;
    }

    private ChessType type(int position) {
        return ChessType.getByCode(board[position] & MASK_TYPE);
    }


}
