package com.xma.chess.core.actions;

import com.xma.chess.core.Position;

public class Action {

    private final ActionType actionType;

    protected Action(ActionType type) {
        actionType = type;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public static Action move(Position startPosition, Position endPosition) {
        return new ActionMove(startPosition, endPosition);
    }

    public static Action doubleMove(Position startPosition, Position endPosition) {
        return new ActionMove(startPosition, endPosition);
    }

    public static Action swap(Position startPosition, Position endPosition) {
        return new ActionSwap(startPosition, endPosition);
    }

    public static Action take(Position startPosition, Position endPosition, Position eatenPosition) {
        return new ActionEat(startPosition, endPosition, eatenPosition);
    }


    public enum ActionType {
        MOVE,
        DOUBLE_MOVE,
        EAT,
        SWAP,
        CASTLING,
        TAKING,
    }
}
