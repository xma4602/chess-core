package com.xma.chess.core.figures;

import com.xma.chess.core.Board;
import com.xma.chess.core.Position;
import com.xma.chess.core.actions.Action;

import java.util.List;

public class Bishop extends Figure{
    public Bishop(Board board) {
        super(board);
    }

    @Override
    public List<Action> getActions(Position position) {
        return null;
    }
}
