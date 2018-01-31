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

public class TicTacToeRules extends Application {

    public TicTacToeRules(Stage name) {
        start(name);
    }

    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        Button btn = new Button();
        btn.relocate(0, 0);
        btn.setPrefWidth(800);
        btn.setPrefHeight(800);
        btn.setText("Rules: \nPlayer 1 uses black pieces, Player 2 uses yellow pieces\n" +
                "Player 1 goes first and turns alternate after a blank (gray) piece has been chosen" +
                "\nTiles cannot change colour once a player has taken it" +
                "\nGet three tiles of the same colour horizontally, vertically, or diagonally to win\nHave fun!");

        //Set up the Game of Life.
        btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                new TicTacToe(primaryStage);
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
