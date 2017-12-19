package GameOfLife;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;


/**
 * Created by jacks on 2017-12-16.
 */
public class GameOfLife extends Application {

    double lastTime = 0;
    int iterations = 0;
    GameOfLifeGrid grid;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        grid = new GameOfLifeGrid(800/GameOfLifeGrid.SIZE,800/GameOfLifeGrid.SIZE);
        primaryStage.setTitle("Game Of Life");
        Group root = new Group();
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        new AnimationTimer(){
            @Override
            public void handle(long now) {
                if(now - lastTime > 0 * 1_000_000) {
                    if(iterations > 1000){
                        grid.resetGrid(grid.getRows(), grid.getCols());
                        iterations = 0;
                    }
                    updateScreen(gc);
                    grid.update();
                    lastTime = now;
                    iterations++;
                }
            }
        }.start();
    }

    private void updateScreen(GraphicsContext gc) {
        for(int row = 0; row < grid.getRows(); row++){
            for(int col = 0; col < grid.getCols(); col++){
                GameOfLifeSlot gols = grid.getCell(row, col);
                gc.setFill(grid.getColor(row, col));
                gc.fillRect(gols.getCoord().getX(), gols.getCoord().getY(), gols.getSize(), gols.getSize());
            }
        }
    }
}