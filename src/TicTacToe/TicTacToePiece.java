package TicTacToe;

import Grid.I_GridSlot;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.geom.Point2D;

//An abstract class for Tic Tac Toe pieces that implements the Grid Slot interface.
public abstract class TicTacToePiece implements I_GridSlot {

    //Abstract method that returns a char to find the type of piece.
    public abstract char getPieceType();

    //Private references.
    private Stage stage;
    private Point2D coord;
    private double size;

    //Override methods from the interface (mandatory).
    @Override
    public Point2D getCoord() {
        return this.coord;
    }

    @Override
    public double getSize() {
        return this.size;
    }

    //Method to figure out if the user's mouse is above a piece or not.
    @Override
    public boolean isMouseOnTop() {
        Point2D.Double mousePoint = new Point2D.Double(MouseInfo.getPointerInfo().getLocation().getX() - this.stage.getX() - 8, MouseInfo.getPointerInfo().getLocation().getY() - this.stage.getY() - 30);
        if (mousePoint.getX() > getCoord().getX() && mousePoint.getX() < getCoord().getX() + this.size && mousePoint.getY() > getCoord().getY() && mousePoint.getY() < getCoord().getY() + this.size)
            return true;
        return false;
    }

    //Constructor to be called by a subclass.
    public TicTacToePiece(Stage stage, Point2D coord, double size) {
        this.stage = stage;
        this.coord = coord;
        this.size = size;
    }
}
