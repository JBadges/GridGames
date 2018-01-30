package menus;

import GameOfLife.GameOfLife;
import GameOfLife.GameOfLifeSlot;
import TicTacToe.TicTacToe;
import TicTacToe.TicTacToeGrid;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//Main class to be run.
public class Main extends Application {

    //Main method that launches the application.
    public static void main(String[] args) {
        launch();
    }

    //Create the main menu with two buttons to give user the option to play either game.
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Jackson and Kyle's Games");
        Button btn = new Button();
        btn.relocate(12, 0);
        btn.setPrefWidth(800);
        btn.setPrefHeight(400);
        btn.setText("Game Of Life");

        //Set up the Game of Life.
        btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                new GameOfLife(primaryStage);

            }
        });

        Button btn2 = new Button();
        btn2.relocate(12, 400);
        btn2.setPrefWidth(800);
        btn2.setPrefHeight(400);
        btn2.setText("Tic Tac Toe");

        //Set up Tic Tac Toe.
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                new TicTacToe(primaryStage);
            }
        });

        Group root = new Group();
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        root.getChildren().add(btn);
        root.getChildren().add(btn2);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
