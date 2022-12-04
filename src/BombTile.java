import javafx.scene.text.Font;

public class BombTile extends MineSweeperTile{

    public BombTile(int xCord, int yCord){
        super(xCord, yCord);
        System.out.println("New BombTile created!");
        safe = false;

    }

    public BombTile(){
        System.out.println("New BombTile created!");
        safe = false;
    }

    @Override
    public void flip(){
        //setFont(new Font(20));
        setStyle("-fx-background-color: #808080;");
        System.out.println("Kaboom!");
        setText("X");
        if (!MScontroller.gameOver){
            MScontroller.igniteAllBombs();
        }
    }

    @Override
    public boolean isSafe(){
        return false;
    }
}
