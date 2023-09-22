package com.xma.chess.core.figures;

import com.xma.chess.core.Action;
import com.xma.chess.core.Board;
import com.xma.chess.core.ChessType;

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
    public List<Action> getActions(int position) {
        actions = new ArrayList<>();

        if (board.isWhite(position)) {
            add(moveForward(position, position + 8));
            add(moveDoubleForward(position, position + 16));
            add(eat(true, position, position + 7));
            add(eat(true, position, position + 9));
            add(take(true, position, position - 1));
            add(take(true, position, position + 1));
        } else {
            add(moveForward(position, position - 8));
            add(moveDoubleForward(position, position - 16));
            add(eat(false, position, position - 9));
            add(eat(false, position, position - 7));
            add(take(false, position, position - 1));
            add(take(false, position, position + 1));
        }

        return actions;
    }


    private Action eat(boolean isWhite, int startPosition, int endPosition) {
        if (board.existPosition(endPosition) //позиция корректна?
                && board.type(endPosition) != ChessType.NONE //на позиции не пусто?
                && board.isWhite(endPosition) != isWhite  //на позиции противник?
        ) return Action.move(startPosition, endPosition);

        return null;
    }

    private Action take(boolean isWhite, int startPosition, int opponentPos) {
        if (board.existPosition(opponentPos) //позиция корректна?
                && board.type(opponentPos) != ChessType.NONE //позиция не пустая?
                && board.isWhite(startPosition) != isWhite //на позиции противник?
                && board.wasDoubleMove(opponentPos) //противник делал двойной ход?
        ) {
            if (isWhite) {
                return Action.take(startPosition, opponentPos + 8, opponentPos);
            } else {
                return Action.take(startPosition, opponentPos - 8, opponentPos);
            }
        }
        return null;
    }

    private Action moveForward(int startPosition, int endPosition) {
        if (board.existPosition(endPosition)) { //позиция корректна?
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


    private Action moveDoubleForward(int startPosition, int endPosition) {
        if (!board.wasMoving(startPosition)) { //фигура не делала ход?
            if (board.existPosition(endPosition)) { //позиция корректна?
                if (board.type(endPosition) == ChessType.NONE) { //на позиции пусто?
                    return Action.doubleMove(startPosition, endPosition);
                }
            }
        }
        return null;
    }
}
