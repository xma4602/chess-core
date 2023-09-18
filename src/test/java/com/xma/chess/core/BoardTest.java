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
    public void cellToPosition() {
        for (int row = 8; row >= 1; row--) {
            for (char col = 'a'; col <= 'h'; col++) {
                System.out.printf("%02d ", Board.cellToPosition("%s%d".formatted(col, row)));
            }
            System.out.println();
        }
        System.out.println();
    }

    @Test
    public void positionToCell() {
        for (int row = 7; row >= 0; row--) {
            for (int col = 0; col < 8; col++) {
                System.out.print(Board.positionToCell(row * 8 + col) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
