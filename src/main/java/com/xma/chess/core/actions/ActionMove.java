package com.xma.chess.core.actions;

import com.xma.chess.core.Position;

public class ActionMove extends Action{

    protected Position endPosition;
    protected Position startPosition;

    public ActionMove(Position startPosition, Position endPosition) {
        super(ActionType.MOVE);
    }

    public ActionMove(Position startPosition, Position endPosition, ActionType type) {
        super(type);
    }


    public Position getEndPosition() {
        return endPosition;
    }

    public Position getStartPosition() {
        return startPosition;
    }
}
