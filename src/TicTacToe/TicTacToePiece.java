package TicTacToe;

import Grid.I_GridSlot;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by jacks on 2018-01-18.
 */
public abstract class TicTacToePiece implements I_GridSlot {

    public abstract char getPieceType();

    private Stage stage;
    private Point2D coord;
    private double size;

    @Override
    public Point2D getCoord() {
        return this.coord;
    }

    @Override
    public double getSize() {
        return this.size;
    }

    @Override
    public boolean isMouseOnTop() {
        Point2D.Double mousePoint = new Point2D.Double(MouseInfo.getPointerInfo().getLocation().getX() - this.stage.getX() - 8, MouseInfo.getPointerInfo().getLocation().getY() - this.stage.getY() - 30);
        if (mousePoint.getX() > getCoord().getX() && mousePoint.getX() < getCoord().getX() + this.size && mousePoint.getY() > getCoord().getY() && mousePoint.getY() < getCoord().getY() + this.size)
            return true;
        return false;
    }

    public TicTacToePiece(Stage stage, Point2D coord, double size) {
        this.stage = stage;
        this.coord = coord;
        this.size = size;
    }
}
