package com.xma.chess.core.figures;

import com.xma.chess.core.Action;
import com.xma.chess.core.Board;
import com.xma.chess.core.Position;

import java.util.List;

public abstract class Figure {

    protected final Board board;

    public Figure(Board board) {
        this.board = board;
    }

    public abstract List<Action> getActions(Position position);

}
