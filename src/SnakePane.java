import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SnakePane extends Pane{

    int speed = 20;
    int height = 600;
    int width = 800;

    public SnakePane(){
        System.out.println("New SnakePane created!");

        setHeight(height);
        setWidth(width);
        Rectangle snakeHead = new Rectangle();
        snakeHead.setWidth(20);
        snakeHead.setHeight(20);
        snakeHead.setLayoutX(400);
        snakeHead.setLayoutY(300);
        snakeHead.setFill(Color.RED);
        getChildren().add(snakeHead);
        BackButton backButton = new BackButton();
        backButton.setLayoutY(getHeight()-new BackButton().getHeight());
        getChildren().add(backButton);

        setOnMouseClicked(e->{
            App.cont.stage.getScene();
        });
        setOnKeyPressed( e->{

            if(e.getCode() == KeyCode.SPACE){
                System.out.println("SPACE");
            }

            if(e.getCode()==KeyCode.UP){
                System.out.println("UP");
                snakeHead.setLayoutY(snakeHead.getLayoutY() - speed);
            }

            if(e.getCode()==KeyCode.DOWN){
                System.out.println("DOWN");
                snakeHead.setLayoutY(snakeHead.getLayoutY() + speed);
            }

            if(e.getCode()==KeyCode.LEFT){
                System.out.println("LEFT");
                snakeHead.setLayoutX(snakeHead.getLayoutX() - speed);
            }

            if(e.getCode()==KeyCode.RIGHT){
                System.out.println("RIGHT");
                snakeHead.setLayoutX(snakeHead.getLayoutX() + speed);
            }
        });
    }

    
    
}
