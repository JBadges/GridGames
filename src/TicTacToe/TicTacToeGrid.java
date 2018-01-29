package TicTacToe;

import javafx.stage.Stage;

import java.awt.*;
import java.awt.geom.Point2D;


public class TicTacToeGrid {

    public final int SIZE = 800 / 3;
    TicTacToePiece[][] grid = new TicTacToePiece[3][3];
    private Stage stage;

    public TicTacToeGrid(Stage stage) {
        this.stage = stage;
        resetGrid();
    }

    public void resetGrid() {
        grid = new TicTacToePiece[3][3];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = new TicTacToePiece(stage, new Point(row * SIZE, col * SIZE), SIZE) {
                    @Override
                    public char getPieceType() {
                        return '\u0000';
                    }
                };
            }
        }
    }

    public TicTacToePiece getCell(int row, int col) {
        return grid[row][col];
    }

    public char getPiece(int row, int col) {
        return grid[row][col].getPieceType();
    }

    public void setPiece(int row, int col, char piece) {
        if (piece == 'B')
            grid[row][col] = new TicTacToePieceBlack(stage, new Point(row * SIZE, col * SIZE), SIZE);
        else if (piece == 'Y')
            grid[row][col] = new TicTacToePieceYellow(stage, new Point(row * SIZE, col * SIZE), SIZE);
    }

    public void setPiece(Point2D gridPos, char piece) {
        setPiece((int)gridPos.getX(),(int) gridPos.getY(), piece);
    }

    public int[] findHoveredSlot() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                TicTacToePiece ticTacToePiece = getCell(row, col);
                if (ticTacToePiece.isMouseOnTop()) {
                    return new int[]{row, col};
                }
            }
        }
        return null;
    }

    public String whoWon() {
        /*
         * 0,0 0,1 0,2
         * 1,0 1,1 1,2
         * 2,0 2,1 2,2
         *
         * 0,0 1,0 2,0
         * 0,1 1,1 2,1
         * 0,2 1,2 2,2
         *
         * 0,0 1,1 2,2
         * 2,0 1,1 0,2
         */
        String winner = "\u0000";
        if (getPiece(0, 0) == getPiece(0, 1) && getPiece(0, 1) == getPiece(0, 2) && getPiece(0, 2) == 'B'
                || getPiece(1, 0) == getPiece(1, 1) && getPiece(1, 1) == getPiece(1, 2) && getPiece(1, 2) == 'B' ||
                getPiece(2, 0) == getPiece(2, 1) && getPiece(2, 1) == getPiece(2, 2) && getPiece(2, 2) == 'B' ||
                getPiece(0, 0) == getPiece(1, 0) && getPiece(1, 0) == getPiece(2, 0) && getPiece(2, 0) == 'B' ||
                getPiece(0, 1) == getPiece(1, 1) && getPiece(1, 1) == getPiece(2, 1) && getPiece(2, 1) == 'B' ||
                getPiece(0, 2) == getPiece(1, 2) && getPiece(1, 2) == getPiece(2, 2) && getPiece(2, 2) == 'B' ||
                getPiece(0, 0) == getPiece(1, 1) && getPiece(1, 1) == getPiece(2, 2) && getPiece(2, 2) == 'B' ||
                getPiece(2, 0) == getPiece(1, 1) && getPiece(1, 1) == getPiece(0, 2) && getPiece(0, 2) == 'B') {
            winner = "BLACK";
        } else if (getPiece(0, 0) == getPiece(0, 1) && getPiece(0, 1) == getPiece(0, 2) && getPiece(0, 2) == 'Y'
                || getPiece(1, 0) == getPiece(1, 1) && getPiece(1, 1) == getPiece(1, 2) && getPiece(1, 2) == 'Y' ||
                getPiece(2, 0) == getPiece(2, 1) && getPiece(2, 1) == getPiece(2, 2) && getPiece(2, 2) == 'Y' ||
                getPiece(0, 0) == getPiece(1, 0) && getPiece(1, 0) == getPiece(2, 0) && getPiece(2, 0) == 'Y' ||
                getPiece(0, 1) == getPiece(1, 1) && getPiece(1, 1) == getPiece(2, 1) && getPiece(2, 1) == 'Y' ||
                getPiece(0, 2) == getPiece(1, 2) && getPiece(1, 2) == getPiece(2, 2) && getPiece(2, 2) == 'Y' ||
                getPiece(0, 0) == getPiece(1, 1) && getPiece(1, 1) == getPiece(2, 2) && getPiece(2, 2) == 'Y' ||
                getPiece(2, 0) == getPiece(1, 1) && getPiece(1, 1) == getPiece(0, 2) && getPiece(0, 2) == 'Y') {
            winner = "YELLOW";
        }
        return winner;
    }
}
