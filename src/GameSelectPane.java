import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class GameSelectPane extends Pane{
    Button snakeBtn, mineSweeperBtn;
    int h = 30;
    int w = 120;

    public GameSelectPane(){
        SnakeButton snakeButton = new SnakeButton("Snake", h, w, 120, 60);
        getChildren().add(snakeButton);
        mineSweeperBtn = new MineSweeperButton("MineSweeper", h, w, 120, 120);
        getChildren().add(mineSweeperBtn);
    }
}
