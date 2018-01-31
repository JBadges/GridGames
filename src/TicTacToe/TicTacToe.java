package TicTacToe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.geom.Point2D;

//Concrete class that has the GUI from JavaFX
public class TicTacToe extends Application {

    TicTacToeGrid grid;
    boolean isPlayerOneTurn = true;
    int turnNumber = 1;

    //Non-default constructor that calls the start method
    public TicTacToe(Stage name) {
        start(name);
    }

    //Set up the screen for Tic Tac Toe.
    @Override
    public void start(Stage primaryStage) {
        grid = new TicTacToeGrid(primaryStage);
        primaryStage.setTitle("Tic Tac Toe");
        //Declaring a subclass object.
        Parent root = new Group();
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //Downcast
        ((Group) root).getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        grid.resetGrid();
        updateScreen(gc);

        //What happens when the mouse clicks a piece.
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //If the game has no winner yet and game has not tied
                if (grid.whoWon() == "\u0000" && turnNumber <= 9) {
                    //Find the piece that was clicked on.
                    int[] hoveredLoc = grid.findHoveredSlot();
                    TicTacToePiece ticTacToePiece = grid.getCell(hoveredLoc[0], hoveredLoc[1]);
                    //If it is player's one turn and the space is free, set the piece to 'B'.
                    if (isPlayerOneTurn) {
                        if (grid.getPiece(hoveredLoc[0], hoveredLoc[1]) == '\u0000') {
                            grid.setPiece(hoveredLoc[0], hoveredLoc[1], 'B');
                            isPlayerOneTurn = !isPlayerOneTurn;
                            turnNumber++;
                        }
                    }
                    //If it is not player's one turn, and the space is free, set the piece to 'Y'.
                    else {
                        if (grid.getPiece(hoveredLoc[0], hoveredLoc[1]) == '\u0000') {
                            grid.setPiece(new Point2D.Double(hoveredLoc[0], hoveredLoc[1]), 'Y');
                            isPlayerOneTurn = !isPlayerOneTurn;
                            turnNumber++;
                        }
                    }

                    updateScreen(gc);

                    //When a winner has been found display a win message.
                    if (grid.whoWon() != "\u0000") {
                        Button btnWin = new Button();
                        btnWin.setPrefWidth(800);
                        btnWin.setPrefHeight(800);
                        btnWin.setText(grid.whoWon() + " WON YAY!");

                        //Downcast
                        ((Group) root).getChildren().add(btnWin);

                        //When the win message is clicked, exit the application.
                        btnWin.setOnAction(new EventHandler<ActionEvent>() {

                            public void handle(ActionEvent event) {

                                System.exit(0);
                            }
                        });

                    }

                    //When a tie game has occured, display an appropriate message.
                    if (turnNumber == 10 && grid.whoWon() == "\u0000") {
                        Button btnTie = new Button();
                        btnTie.setPrefWidth(800);
                        btnTie.setPrefHeight(800);
                        btnTie.setText("TIE GAME!");

                        //Downcast
                        ((Group) root).getChildren().add(btnTie);

                        //When the tie message is clicked, exit the application.
                        btnTie.setOnAction(new EventHandler<ActionEvent>() {

                            public void handle(ActionEvent event) {

                                System.exit(0);
                            }
                        });

                    }

                }
            }
        });

    }

    //Method to update screen every time a valid piece is clicked.
    private void updateScreen(GraphicsContext gc) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                TicTacToePiece tttp = grid.getCell(row, col);
                gc.setFill(grid.getPiece(row, col) == 'B' ? Color.BLACK : grid.getPiece(row, col) == 'Y' ? Color.YELLOW : Color.GRAY);
                gc.fillRect(tttp.getCoord().getX(), tttp.getCoord().getY(), tttp.getSize(), tttp.getSize());
                gc.setStroke(Color.WHITE);
                gc.strokeRect(tttp.getCoord().getX(), tttp.getCoord().getY(), tttp.getSize(), tttp.getSize());
            }
        }
    }

}
