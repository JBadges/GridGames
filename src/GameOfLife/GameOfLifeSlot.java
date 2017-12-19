package GameOfLife;


import Grid.I_GridSlot;
import javafx.scene.paint.Color;


import java.awt.geom.Point2D;

/**
 * Created by jacks on 2017-12-16.
 */
public class GameOfLifeSlot implements I_GridSlot {
    private Point2D coord;
    private double size;
    private Color color = Color.BLACK;
    private int numberOfBlues, numberOfReds, numberOfBlacks;

    public GameOfLifeSlot(Point2D coord, double size, Color color) {
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

}
