package com.xma.chess.core.figures;

import com.xma.chess.core.Action;
import com.xma.chess.core.Board;
import com.xma.chess.core.ChessType;
import com.xma.chess.core.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Figure {

    private List<Action> actions;

    public Pawn(Board board) {
        super(board);
    }

    private void add(Action action) {
        if (action != null) actions.add(action);
    }


    @Override
    public List<Action> getActions(Position position) {
        actions = new ArrayList<>();
        boolean isWhite = board.isWhite(position);

        add(moveForward(position, position.top(isWhite)));
        add(moveDoubleForward(position, position.topDouble(isWhite)));
        add(eat(isWhite, position, position.leftTop(isWhite)));
        add(eat(isWhite, position, position.rightTop(isWhite)));
        add(take(isWhite, position, position.left(isWhite)));
        add(take(isWhite, position, position.right(isWhite)));

        return actions;
    }


    private Action eat(boolean isWhite, Position startPosition, Position endPosition) {
        if (endPosition != null //позиция корректна?
                && board.type(endPosition) != ChessType.NONE //на позиции не пусто?
                && board.isWhite(endPosition) != isWhite  //на позиции противник?
        ) return Action.move(startPosition, endPosition);

        return null;
    }

    private Action take(boolean isWhite, Position startPosition, Position opponentPos) {
        if (opponentPos != null //позиция корректна?
                && board.type(opponentPos) != ChessType.NONE //позиция не пустая?
                && board.isWhite(startPosition) != isWhite //на позиции противник?
                && board.wasDoubleMove(opponentPos) //противник делал двойной ход?
        ) {
            return Action.take(startPosition, opponentPos.top(isWhite), opponentPos);
        }
        return null;
    }

    private Action moveForward(Position startPosition, Position endPosition) {
        if (endPosition != null) { //позиция корректна?
            if (board.type(endPosition) == ChessType.NONE) { //на позиции пусто?
                if (board.isLastLine(endPosition)) { //это последняя линия поля?
                    return Action.swap(startPosition, endPosition);
                } else {
                    return Action.move(startPosition, endPosition);
                }
            }
        }
        return null;
    }


    private Action moveDoubleForward(Position startPosition, Position endPosition) {
        if (!board.wasMoving(startPosition)) { //фигура не делала ход?
            if (endPosition != null) { //позиция корректна?
                if (board.type(endPosition) == ChessType.NONE) { //на позиции пусто?
                    return Action.doubleMove(startPosition, endPosition);
                }
            }
        }
        return null;
    }
}
