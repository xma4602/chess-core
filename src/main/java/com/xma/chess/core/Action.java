package com.xma.chess.core;

public class Action {

    private final ActionType actionType;
    int startPosition;
    int endPosition;
    int eatenPosition;

    private Action(ActionType actionType, int startPosition, int endPosition) {
        this(actionType, startPosition, endPosition, -1);
    }

    private Action(ActionType actionType, int startPosition, int endPosition, int eatenPosition) {
        this.actionType = actionType;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.eatenPosition = eatenPosition;
    }

    public static Action move(int startPosition, int endPosition) {
        return new Action(ActionType.MOVE, startPosition, endPosition);
    }

    public static Action doubleMove(int startPosition, int endPosition) {
        return new Action(ActionType.DOUBLE_MOVE, startPosition, endPosition);
    }

    public static Action swap(int startPosition, int endPosition) {
        return new Action(ActionType.SWAP, startPosition, endPosition);
    }

    public static Action take(int startPosition, int endPosition, int eatenPosition) {
        return new Action(ActionType.TAKING, startPosition, endPosition, eatenPosition);
    }

    public ActionType getActionType() {
        return actionType;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public int getStartPosition() {
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
