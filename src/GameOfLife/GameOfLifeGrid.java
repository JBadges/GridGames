package GameOfLife;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Created by jacks on 2017-12-16.
 */
public class GameOfLifeGrid {

    public static final int SIZE = 40;

    private GameOfLifeSlot[][] grid;
    private Stage stage;

    /**
     * @param stage Stage from javaFX used to find mouse postion
     * @param rows  The number of rows in the Game of Life simulation
     * @param cols  The number of colums in the Game of Life simluation
     */
    public GameOfLifeGrid(Stage stage, int rows, int cols) {
        this.stage = stage;
        resetGrid(rows, cols);
    }

    /**
     * @param rows Creates a new GameOfLifeSlot array of size row
     * @param cols Creates a new GameOfLifeSlot secondary array of size col
     * @implNote Initializes the array with objects for each element
     */
    public void resetGrid(int rows, int cols) {
        grid = new GameOfLifeSlot[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Color c = Color.BLACK;
                grid[row][col] = new GameOfLifeSlot(stage, new Point(row * SIZE, col * SIZE), SIZE, c);
            }
        }
    }

    /**
     * @implNote Simulation Rules - 1. if the surrounding colours are equal to 3 set the cell to that colour 2. if the total colours is less than 2 the cell dies 3. if the total number of colours is 2 or 3 the cell lives 4. if the total colours is > 3 the cell dies
     */
    public void update() {
        //Update the variables of each cell for the simulation check after
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
        //BASELINE rules for game of life - Altered from the original rules to use two players (colors) instead of one
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

    /**
     * @param row the index of the first array
     * @param col the element index from the array chosen from row
     * @return the AWT color value of the cell
     */
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

    /**
     * @param row Row value starting at index 0
     * @param col Col value starting at index 0
     * @return the GameOfLifeSlot Object at the specified location
     */
    public GameOfLifeSlot getCell(int row, int col) {
        return grid[row][col];
    }

    /**
     * @param row Row value starting at index 0
     * @param col Col value starting at index 0
     * @return the AWT Color value from the getColor method from the element at [row][col]
     */
    public Color getColor(int row, int col) {
        return grid[row][col].getColor();
    }

    /**
     * @param row   Row value starting at index 0
     * @param col   Col value starting at index 0
     * @param color Sets the AWT color of the element at [row][col]
     */
    public void setColor(int row, int col, Color color) {
        grid[row][col].setColor(color);
    }

    /**
     * @return the length of the frist array ([0]) since all the arrays are the same length
     */
    public int getCols() {
        return grid[0].length;
    }

    /**
     * @return the length of the array grid
     */
    public int getRows() {
        return grid.length;
    }

    /**
     * @return the GameOfLifeSlot object for the current mouse position
     */
    public GameOfLifeSlot findHoveredSlot() {
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                GameOfLifeSlot gameOfLifeSlot = getCell(row, col);
                if (gameOfLifeSlot.isMouseOnTop()) {
                    return gameOfLifeSlot;
                }
            }
        }
        return null;
    }

}
