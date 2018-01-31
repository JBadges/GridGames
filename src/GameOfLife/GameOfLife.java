package GameOfLife;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Created by jacks on 2017-12-16.
 */
public class GameOfLife extends Application {

    double lastTime = 0;
    int iterations = 0;
    int numberOfTurns = 0;
    boolean gameOver = false;
    GameOfLifeGrid grid;

    public GameOfLife(Stage name) {
        start(name);
    }

    //Set up scene for Game Of Life
    @Override
    public void start(Stage primaryStage) {
        grid = new GameOfLifeGrid(primaryStage, 800 / GameOfLifeGrid.SIZE, 800 / GameOfLifeGrid.SIZE);
        primaryStage.setTitle("Game Of Life");
        Group root = new Group();
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        //Clear and create a fresh grid
        grid.resetGrid(grid.getRows(), grid.getCols());
        //Update the screen
        updateScreen(gc);
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //For the first 50 clicks (25 per player) do not run the simulation and take user input
                if (numberOfTurns < 50) {
                    GameOfLifeSlot gameOfLifeSlot = grid.findHoveredSlot();
                    if (gameOfLifeSlot != null && gameOfLifeSlot.getColor().equals(Color.BLACK)) {
                        gameOfLifeSlot.setColor(numberOfTurns % 2 == 0 ? Color.BLUE : Color.RED);
                        numberOfTurns++;
                    }
                } else {
                    //Loop 1000 times at 10 milliseconds each iteration (1_000_000 nanosecond = 1 ms)
                    new AnimationTimer() {
                        @Override
                        public void handle(long now) {
                            if (now - lastTime > 10 * 1_000_000) {
                                if (iterations > 1000) {
                                    int numOfBlues = 0;
                                    int numOfReds = 0;
                                    for (int row = 0; row < grid.getRows(); row++) {
                                        for (int col = 0; col < grid.getCols(); col++) {
                                            if (grid.getColor(row, col).equals(Color.RED))
                                                numOfReds++;
                                            if (grid.getColor(row, col).equals(Color.BLUE))
                                                numOfBlues++;

                                        }
                                    }
                                    //Blue wins check
                                    if (numOfBlues > numOfReds && !gameOver) {
                                        gameOver = true;
                                        System.out.println("blue win");
                                        Button blueWin = new Button();
                                        blueWin.setPrefWidth(800);
                                        blueWin.setPrefHeight(800);
                                        blueWin.setText("BLUE WON YAY!");

                                        root.getChildren().add(blueWin);

                                        blueWin.setOnAction(new EventHandler<ActionEvent>() {

                                            public void handle(ActionEvent event) {
                                                System.exit(0);
                                            }
                                        });

                                    }
                                    //Red wins check
                                    if (numOfBlues < numOfReds && !gameOver) {
                                        gameOver = true;
                                        Button redWin = new Button();
                                        redWin.setPrefWidth(800);
                                        redWin.setPrefHeight(800);
                                        redWin.setText("RED WON YAY!");

                                        root.getChildren().add(redWin);


                                        redWin.setOnAction(new EventHandler<ActionEvent>() {

                                            public void handle(ActionEvent event) {

                                                System.exit(0);
                                            }
                                        });
                                    }
                                    //Tie game check
                                    if (numOfBlues == numOfReds && !gameOver) {
                                        gameOver = true;
                                        Button tie = new Button();
                                        tie.setPrefWidth(800);
                                        tie.setPrefHeight(800);
                                        tie.setText("TIE GAME :(");

                                        root.getChildren().add(tie);


                                        tie.setOnAction(new EventHandler<ActionEvent>() {
                                            public void handle(ActionEvent event) {
                                                System.exit(0);
                                            }
                                        });
                                    }
                                } else {
                                    //If the 1000 iterations of game simulation are not done update the screen and grid
                                    updateScreen(gc);
                                    grid.update();
                                }
                            }
                            //Update last time to keep the time difference calculation updated
                            lastTime = now;
                            iterations++;
                        }
                    }.start();
                }
                updateScreen(gc);

            }
        });

    }

    //Uses graphics context to display the grid to the screen
    private void updateScreen(GraphicsContext gc) {
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                GameOfLifeSlot gols = grid.getCell(row, col);
                gc.setFill(grid.getColor(row, col));
                gc.fillRect(gols.getCoord().getX(), gols.getCoord().getY(), gols.getSize(), gols.getSize());
                gc.setStroke(Color.WHITE);
                gc.strokeRect(gols.getCoord().getX(), gols.getCoord().getY(), gols.getSize(), gols.getSize());
            }
        }
    }
}