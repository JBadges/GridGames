package GameOfLife;

import javafx.scene.paint.Color;

import java.awt.*;
import java.util.Random;

/**
 * Created by jacks on 2017-12-16.
 */
public class GameOfLifeGrid {

    public static final int SIZE = 20;

    private GameOfLifeSlot[][] grid;

    public GameOfLifeGrid(int rows, int cols) {
        resetGrid(rows, cols);
    }

    public void resetGrid(int rows, int cols){
        grid = new GameOfLifeSlot[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Color c;
                switch (new Random().nextInt(5)) {
                    case 0:
                        c = Color.RED;
                        break;
                    case 1:
                        c = Color.BLUE;
                        break;
                    default:
                        c = Color.BLACK;
                }
                grid[row][col] = new GameOfLifeSlot(new Point(row * SIZE, col * SIZE), SIZE, c);
            }
        }
    }

    public void update() {
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                int numberOfReds = 0;
                int numberOfBlues = 0;
                int numberOfBlacks = 0;

                Color topLeft, top, topRight, botLeft, bot, botRight, left, right;
                topLeft = getCellColor(row - 1, col - 1);
                top = getCellColor(row - 1, col);
                topRight = getCellColor(row - 1, col + 1);
                botLeft = getCellColor(row + 1, col - 1);
                bot = getCellColor(row + 1, col);
                botRight = getCellColor(row + 1, col + 1);
                left = getCellColor(row, col - 1);
                right = getCellColor(row, col + 1);

                if (Color.BLUE.equals(topLeft))
                    numberOfBlues++;
                if (Color.BLUE.equals(top))
                    numberOfBlues++;
                if (Color.BLUE.equals(topRight))
                    numberOfBlues++;
                if (Color.BLUE.equals(botLeft))
                    numberOfBlues++;
                if (Color.BLUE.equals(bot))
                    numberOfBlues++;
                if (Color.BLUE.equals(botRight))
                    numberOfBlues++;
                if (Color.BLUE.equals(left))
                    numberOfBlues++;
                if (Color.BLUE.equals(right))
                    numberOfBlues++;

                if (Color.RED.equals(topLeft))
                    numberOfReds++;
                if (Color.RED.equals(top))
                    numberOfReds++;
                if (Color.RED.equals(topRight))
                    numberOfReds++;
                if (Color.RED.equals(botLeft))
                    numberOfReds++;
                if (Color.RED.equals(bot))
                    numberOfReds++;
                if (Color.RED.equals(botRight))
                    numberOfReds++;
                if (Color.RED.equals(left))
                    numberOfReds++;
                if (Color.RED.equals(right))
                    numberOfReds++;

                if (Color.BLACK.equals(topLeft))
                    numberOfBlacks++;
                if (Color.BLACK.equals(top))
                    numberOfBlacks++;
                if (Color.BLACK.equals(topRight))
                    numberOfBlacks++;
                if (Color.BLACK.equals(botLeft))
                    numberOfBlacks++;
                if (Color.BLACK.equals(bot))
                    numberOfBlacks++;
                if (Color.BLACK.equals(botRight))
                    numberOfBlacks++;
                if (Color.BLACK.equals(left))
                    numberOfBlacks++;
                if (Color.BLACK.equals(right))
                    numberOfBlacks++;

                getCell(row, col).setNumberOfBlues(numberOfBlues);
                getCell(row, col).setNumberOfReds(numberOfReds);
                getCell(row, col).setNumberOfBlacks(numberOfBlacks);
            }
        }
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                GameOfLifeSlot cell = getCell(row, col);
                if (Color.BLACK.equals(cell.getColor())) {
                    if (cell.getNumberOfReds() == 3 && cell.getNumberOfBlues() != 3)
                        setColor(row, col, Color.RED);
                    else if (cell.getNumberOfBlues() == 3 && cell.getNumberOfReds() != 3)
                        setColor(row, col, Color.BLUE);
                }
                if (cell.getNumberOfBlues() + cell.getNumberOfReds() < 2) {
                    setColor(row, col, Color.BLACK);
                }
                if (cell.getNumberOfBlues() + cell.getNumberOfReds() == 2 || cell.getNumberOfBlues() + cell.getNumberOfReds() == 3) {
                    //Stays alive
                }
                if (cell.getNumberOfBlues() + cell.getNumberOfReds() > 3) {
                    setColor(row, col, Color.BLACK);
                }
            }
        }
    }

    private Color getCellColor(int row, int col) {
        if (row < 0)
            return null;
        if (row >= getRows())
            return null;
        if (col < 0)
            return null;
        if (col >= getCols())
            return null;

        return getColor(row, col);
    }

    public GameOfLifeSlot getCell(int row, int col) {
        return grid[row][col];
    }

    public Color getColor(int row, int col) {
        return grid[row][col].getColor();
    }

    public void setColor(int row, int col, Color color) {
        grid[row][col].setColor(color);
    }

    public int getCols() {
        return grid[0].length;
    }

    public int getRows() {
        return grid.length;
    }

}
