import javafx.scene.control.Button;

public class SnakeButton extends Button{
    
    public SnakeButton(String name, int height, int width, int x, int y){
        super(name);
        setMinHeight(height);
        setMinWidth(width);
        setLayoutX(x);
        setLayoutY(y);

        setOnAction( e->{
            System.out.println("Snake Button hasbeen pressed!");
            App.cont.openSnakeGame();
        });
    }   
}