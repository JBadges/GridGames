package GameOfLife;


import Grid.I_GridSlot;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by jacks on 2017-12-16.
 */
public class GameOfLifeSlot implements I_GridSlot  {

    private Stage stage;
    private Point2D coord;
    private double size;
    private Color color = Color.BLACK;
    private int numberOfBlues, numberOfReds, numberOfBlacks;

    public GameOfLifeSlot(Stage stage, Point2D coord, double size, Color color) {
        this.stage = stage;
        this.coord = coord;
        this.size = size;
        this.color = color;
    }

    public void setNumberOfBlues(int numberOfBlues){
        this.numberOfBlues = numberOfBlues;
    }

    public void setNumberOfReds(int numberOfReds){
        this.numberOfReds = numberOfReds;
    }

    public void setNumberOfBlacks(int numberOfBlacks){
        this.numberOfBlacks = numberOfBlacks;
    }

    public int getNumberOfBlues(){
        return numberOfBlues;
    }

    public int getNumberOfReds() {
        return numberOfReds;
    }

    public int getNumberOfBlacks() {
        return numberOfBlacks;
    }

    public int getNumberOfSameColor(){
        if(Color.BLUE.equals(getColor()))
            return numberOfBlues;
        if(Color.RED.equals(getColor()))
            return numberOfReds;
        return numberOfBlacks;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public Point2D getCoord() {
        return coord;
    }

    @Override
    public double getSize() {
        return size;
    }

    @Override
    public boolean isMouseOnTop() {
        Point2D.Double mousePoint = new Point2D.Double(MouseInfo.getPointerInfo().getLocation().getX() - stage.getX() - 8, MouseInfo.getPointerInfo().getLocation().getY() - stage.getY() - 30);
        if(mousePoint.getX() > getCoord().getX() && mousePoint.getX() < getCoord().getX() + size && mousePoint.getY() > getCoord().getY() && mousePoint.getY() < getCoord().getY() + size)
            return true;
        return false;
    }

}
