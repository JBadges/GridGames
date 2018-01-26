package TicTacToe;

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


public class TicTacToe extends Application {

    TicTacToeGrid grid;
    boolean isPlayerOneTurn = true;
    int turnNumber = 1;

    public TicTacToe (Stage name) {
        start(name);
    }
    @Override
    public void start(Stage primaryStage) {
        grid = new TicTacToeGrid(primaryStage);
        primaryStage.setTitle("Tic Tac Toe");
        Group root = new Group();
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        grid.resetGrid();
        updateScreen(gc);
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (grid.whoWon() == "\u0000" && turnNumber <= 9) {
                    int[] hoveredLoc = grid.findHoveredSlot();
                    TicTacToePiece ticTacToePiece = grid.getCell(hoveredLoc[0], hoveredLoc[1]);
                    if (isPlayerOneTurn) {
                        if(grid.getPiece(hoveredLoc[0], hoveredLoc[1]) == '\u0000' ) {
                            grid.setPiece(hoveredLoc[0], hoveredLoc[1], 'B');
                            isPlayerOneTurn = !isPlayerOneTurn;
                            turnNumber++;
                        }
                    } else {
                        if(grid.getPiece(hoveredLoc[0], hoveredLoc[1]) == '\u0000' ) {
                            grid.setPiece(hoveredLoc[0], hoveredLoc[1], 'Y');
                            isPlayerOneTurn = !isPlayerOneTurn;
                            turnNumber++;
                        }
                    }
                    updateScreen(gc);
                    if (grid.whoWon() != "\u0000") {
                        Button btnWin = new Button();
                        btnWin.setPrefWidth(800);
                        btnWin.setPrefHeight(800);
                        btnWin.setText(grid.whoWon() + " WON YAY!");

                        root.getChildren().add(btnWin);


                        btnWin.setOnAction(new EventHandler<ActionEvent>() {

                            public void handle(ActionEvent event) {

                                System.exit(0);                            }
                        });

                    }

                    if (turnNumber == 10 && grid.whoWon() == "\u0000") {
                        Button btnTie = new Button();
                        btnTie.setPrefWidth(800);
                        btnTie.setPrefHeight(800);
                        btnTie.setText("TIE GAME!");

                        root.getChildren().add(btnTie);


                        btnTie.setOnAction(new EventHandler<ActionEvent>() {

                            public void handle(ActionEvent event) {

                                System.exit(0);                            }
                        });

                    }

                }
            }
        });

    }

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
