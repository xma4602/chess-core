package com.xma.chess.core.figures;

import com.xma.chess.core.Action;
import com.xma.chess.core.Board;

import java.util.List;

public abstract class Figure {

    protected final Board board;

    public Figure(Board board) {
        this.board = board;
    }

    public abstract List<Action> getActions(int position);

    public List<Action> getActions(int row, int col) {
        return getActions(Board.coordinatesToPosition(row, col));
    }

    public List<Action> getActions(String cell) {
        return getActions(Board.cellToPosition(cell));
    }

}
