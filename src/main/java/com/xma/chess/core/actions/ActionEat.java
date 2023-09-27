package com.xma.chess.core.actions;

import com.xma.chess.core.Position;

public class ActionEat extends ActionMove{

    private final Position eatenPosition;

    public ActionEat(Position startPosition, Position endPosition, Position eatenPosition) {
        super(startPosition, endPosition);
        this.eatenPosition = eatenPosition;
    }

    public Position getEatenPosition() {
        return eatenPosition;
    }
}
