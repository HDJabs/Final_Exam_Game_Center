import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class GamePane extends BorderPane{
    Button backBtn;
    public GamePane(){
        backBtn = new Button("Back To Game Selection");
        backBtn.setLayoutX(1);
        backBtn.setLayoutY(1);
        backBtn.setMinHeight(20);
        backBtn.setMinWidth(50);
        getChildren().add(backBtn);
        
    }
}
