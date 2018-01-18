package TicTacToe;

import javafx.stage.Stage;

import java.awt.geom.Point2D;

/**
 * Created by jacks on 2018-01-18.
 */
public class TicTacToePieceO extends TicTacToePiece {

    public TicTacToePieceO(Stage stage, Point2D coord, double size) {
        super(stage, coord, size);
    }

    @Override
    public char getPieceType() {
        return 'O';
    }

}
