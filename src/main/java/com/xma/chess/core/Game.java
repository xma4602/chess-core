package com.xma.chess.core;

import com.xma.chess.core.actions.Action;
import com.xma.chess.core.figures.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Game {
    private final Board board;
    private boolean whiteMove;

    private final Map<ChessType, Figure> figures = new HashMap<>();

    public Game() {
        board = new Board();
        whiteMove = true;

        figures.put(ChessType.PAWN, new Pawn(board));
        figures.put(ChessType.KNIGHT, new Knight(board));
        figures.put(ChessType.BISHOP, new Bishop(board));
        figures.put(ChessType.ROOK, new Rook(board));
        figures.put(ChessType.QUEEN, new Queen(board));
        figures.put(ChessType.KING, new King(board));
    }

    public List<Action> getActionsFor(boolean isWhite) {
        List<Action> actions = new LinkedList<>();
        List<Action> figActions;
        for (Position position : board.figurePositions(isWhite)) {
            figActions = figures.get(board.type(position)).getActions(position);
            //проверка позиций
            actions.addAll(figActions);
        }
        return actions;
    }

    public void make(Action action){
        board.make(action);
    }
}
