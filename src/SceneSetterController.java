import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SceneSetterController {
    Stage stage;
    BackButton backButton = new BackButton();


    public SceneSetterController(Stage primaryStage){
        backButton.setFocusTraversable(false);
        this.stage = primaryStage;
    }

    public void openGameSelectMenu(){
        BorderPane bPane = new BorderPane();
        GameSelectPane GSPane = new GameSelectPane();
        bPane.setCenter(GSPane);
        stage.setTitle("Game Select!");
        Scene scene = new Scene(bPane, 400, 400);

        stage.setScene(scene);

    }

    public void openMineSweeperGame(){
        MineSweeperPane MSPane = new MineSweeperPane();
        MSPane.setFocusTraversable(true);
        stage.setTitle("MineSweeper!");
        
        Scene scene = new Scene(MSPane, MSPane.getWidth(), MSPane.getHeight());
        stage.setScene(scene);
        MSPane.requestFocus();

    }

    public void openSnakeGame(){
        SnakePane SPane = new SnakePane();
        SPane.setFocusTraversable(true);        
        stage.setTitle("Snake!");

        Scene scene = new Scene(SPane, SPane.getWidth(), SPane.getHeight());
        stage.setScene(scene);
        SPane.requestFocus();
        SnakePane snakePane = new SnakePane();
        snakePane.start(stage);
    }
}
