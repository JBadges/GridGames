package TicTacToe;

import javafx.stage.Stage;

import java.awt.geom.Point2D;

//Concrete class that inherits from the Tic Tac Toe Piece abstract class.
public class TicTacToePieceYellow extends TicTacToePiece {

    //Uses the keyword super to call the constructor of the abstract class.
    public TicTacToePieceYellow(Stage stage, Point2D coord, double size) {
        super(stage, coord, size);
    }

    //Overrides the method from its superclass and returns 'Y' for black.
    @Override
    public char getPieceType() {
        return 'Y';
    }

}
