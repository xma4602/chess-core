package com.xma.chess.core;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */

public class BoardTest {

    @Test
    public void print() {
        Board board = new Board();
        board.print();
    }

    @Test
    public void printFigures() {
        for (var type : ChessType.values()) {
            int code = type.getCode();
            int valueWhite = code | 8;
            System.out.printf("черный %s  \t- %d\t|\tбелый %s  \t- %d\n", type.name(), code, type.name(), valueWhite);
        }
    }

    @Test
    public void cellToPosition() {
        for (int row = 8; row >= 1; row--) {
            for (char col = 'a'; col <= 'h'; col++) {
                System.out.printf("%02d ", Position.cellToPosition("%s%d".formatted(col, row)));
            }
            System.out.println();
        }
        System.out.println();
    }

    @Test
    public void positionToCell() {
        for (int row = 7; row >= 0; row--) {
            for (int col = 0; col < 8; col++) {
                System.out.print(Position.positionToCell(row * 8 + col) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Test
    public void reverse(){
        Board board = new Board();
        board.print(true);
    }
}
