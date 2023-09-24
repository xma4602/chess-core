package com.xma.chess.core;

public class Action {

    private final ActionType actionType;
    Position startPosition;
    Position endPosition;
    Position eatenPosition;

    private Action(ActionType actionType, Position startPosition, Position endPosition) {
        this(actionType, startPosition, endPosition, null);
    }

    private Action(ActionType actionType, Position startPosition, Position endPosition, Position eatenPosition) {
        this.actionType = actionType;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.eatenPosition = eatenPosition;
    }

    public static Action move(Position startPosition, Position endPosition) {
        return new Action(ActionType.MOVE, startPosition, endPosition);
    }

    public static Action doubleMove(Position startPosition, Position endPosition) {
        return new Action(ActionType.DOUBLE_MOVE, startPosition, endPosition);
    }

    public static Action swap(Position startPosition, Position endPosition) {
        return new Action(ActionType.SWAP, startPosition, endPosition);
    }

    public static Action take(Position startPosition, Position endPosition, Position eatenPosition) {
        return new Action(ActionType.TAKING, startPosition, endPosition, eatenPosition);
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    public Position getStartPosition() {
        return startPosition;
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
