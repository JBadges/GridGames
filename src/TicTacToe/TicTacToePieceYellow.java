package TicTacToe;

import javafx.stage.Stage;

import java.awt.geom.Point2D;


public class TicTacToePieceYellow extends TicTacToePiece {

    public TicTacToePieceYellow(Stage stage, Point2D coord, double size) {
        super(stage, coord, size);
    }

    @Override
    public char getPieceType() {
        return 'Y';
    }

}
