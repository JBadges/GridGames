package TicTacToe;

import javafx.stage.Stage;

import java.awt.*;
import java.awt.geom.Point2D;

//Concrete class that sets up the grid for Tic Tac Toe.
public class TicTacToeGrid {

    //The value for the size of each individual Tic Tac Toe piece.
    public final int SIZE = 800 / 3;
    //Create a 2D array for the Tic Tac Toe pieces.
    TicTacToePiece[][] grid = new TicTacToePiece[3][3];
    //Create a private reference for the stage
    private Stage stage;


    //Non-default constructor.
    public TicTacToeGrid(Stage stage) {
        this.stage = stage;
        resetGrid();
    }

    //Creates a 3 by 3 grid of Tic Tac Toe pieces.
    public void resetGrid() {
        grid = new TicTacToePiece[3][3];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = new TicTacToePiece(stage, new Point(row * SIZE, col * SIZE), SIZE) {
                    //At the start of the game, all pieces don't belong to either player so return the null char.
                    @Override
                    public char getPieceType() {
                        return '\u0000';
                    }
                };
            }
        }
    }

    //Returns the location of a Tic Tac Toe piece.
    public TicTacToePiece getCell(int row, int col) {
        return grid[row][col];
    }

    //Returns the type of the Tic Tac Toe piece (black or yellow).
    public char getPiece(int row, int col) {
        return grid[row][col].getPieceType();
    }

    //Make a new piece for either the Black or Yellow player
    public void setPiece(int row, int col, char piece) {
        if (piece == 'B')
            grid[row][col] = new TicTacToePieceBlack(stage, new Point(row * SIZE, col * SIZE), SIZE);
        else if (piece == 'Y')
            grid[row][col] = new TicTacToePieceYellow(stage, new Point(row * SIZE, col * SIZE), SIZE);
    }

    //Method overload. This method takes in a coordinate and type of piece then calls the setPiece method above.
    public void setPiece(Point2D gridPos, char piece) {
        setPiece((int)gridPos.getX(),(int) gridPos.getY(), piece);
    }

    //Returns the array position of the Tic Tac Toe piece that is being hovered.
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

    //Winner check method.
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
