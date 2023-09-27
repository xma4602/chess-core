package com.xma.chess.core.actions;

import com.xma.chess.core.ChessType;
import com.xma.chess.core.Position;

public class ActionSwap extends ActionMove {

    private ChessType swapType;
    private boolean isWhite;

    public ActionSwap(Position startPosition, Position endPosition) {
        super(startPosition, endPosition, ActionType.SWAP);
    }

    public ChessType getSwapType() {
        return swapType;
    }

    public boolean isWhite() {
        return isWhite;
    }
}
