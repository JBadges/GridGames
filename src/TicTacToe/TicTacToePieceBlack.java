package TicTacToe;

import javafx.stage.Stage;

import java.awt.geom.Point2D;

//Concrete class that inherits from the Tic Tac Toe Piece abstract class.
public class TicTacToePieceBlack extends TicTacToePiece {

    //Uses the keyword super to call the constructor of the abstract class.
    public TicTacToePieceBlack(Stage stage, Point2D coord, double size) {
        super(stage, coord, size);
    }

    //Overrides the method from its superclass and returns 'B' for black.
    @Override
    public char getPieceType() {
        return 'B';
    }
}
