import javafx.scene.control.Button;

public class MineSweeperButton extends Button{
    
    public MineSweeperButton(String name, int height, int width, int x, int y){
        super(name);
        setMinHeight(height);
        setMinWidth(width);
        setLayoutX(x);
        setLayoutY(y);

        setOnAction( e->{
            System.out.println("MineSweeper Button hasbeen pressed!");
            App.cont.openMineSweeperGame();
        });
    }   
}
