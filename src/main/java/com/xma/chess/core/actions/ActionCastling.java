package com.xma.chess.core.actions;

import com.xma.chess.core.Position;
import com.xma.chess.core.actions.ActionMove;

public class ActionCastling extends ActionMove {
    private final Position rookStartPosition;
    private final Position rookEndPosition;

    public ActionCastling(Position kingStartPosition, Position kindEndPosition, Position rookStartPosition, Position rookEndPosition) {
        super(kingStartPosition, kindEndPosition, ActionType.CASTLING);
        this.rookStartPosition = rookStartPosition;
        this.rookEndPosition = rookEndPosition;
    }

    public Position getRookEndPosition() {
        return rookEndPosition;
    }

    public Position getRookStartPosition() {
        return rookStartPosition;
    }
}
