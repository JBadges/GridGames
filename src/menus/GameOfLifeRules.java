package menus;

import GameOfLife.GameOfLife;
import TicTacToe.TicTacToe;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameOfLifeRules extends Application{

    public GameOfLifeRules (Stage name) {
        start(name);
    }

    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        Button btn = new Button();
        btn.relocate(0, 0);
        btn.setPrefWidth(800);
        btn.setPrefHeight(800);
        btn.setText("Rules: \nPlayer 1 controls a population of blue cells, and Player 2 controls red cells" +
                "\nThe goal of the game is to have your population survive longer than the other player's population" +
                "\nA player clicks an empty black tile to place one of their cells and turns alternate each click\n" +
                "\nOnce each player has placed 25 cells, a simulation occurs where the cells live their life under the following conditions:" +
                "\nAny live cell with fewer than two live neighbours dies, as if caused by underpopulation.\n" +
                "Any live cell with two or three live neighbours lives on to the next generation.\n" +
                "Any live cell with more than three live neighbours dies, as if by overpopulation.\n" +
                "Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.");

        //Set up the Game of Life.
        btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                new GameOfLife(primaryStage);
            }
        });

        Group root = new Group();
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
