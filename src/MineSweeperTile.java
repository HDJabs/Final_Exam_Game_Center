import javafx.scene.control.Button;

public class MineSweeperTile extends Button{
    int xCord, yCord;
    boolean safe;
    int height = 30;
    int width = 30;
    boolean faceUp = false;
    int numberOfAdjBombs =0;
    
    public MineSweeperTile(int xCord, int yCord){
        this.xCord = xCord;
        this.yCord = yCord;
        setLayoutX(xCord);
        setLayoutY(yCord);
        setMinHeight(height);
        setMinWidth(width);
        setText("G");
        setOnAction(e->{
            flip();
        });
    }

    public void setNumberOfAdjBombs(int bombs){
        numberOfAdjBombs = bombs;
    }

    public int getXCord(){
        return this.xCord;
    }

    public int getYCord(){
        return this.yCord;
    }

    public int getTileHeight(){ //had to rename because another method uses the same name
        return this.height;
    }

    public int getTileWidth(){ //^^
        return this.width;
    }

    public String toString(){
        return getXCord() + "," + getYCord();
    }

    public void flip(){
        System.out.println("Flipped Tile at " + getXCord() + ", " + getYCord());
        if(!faceUp){
            faceUp = true;
            setText("GF");
            System.out.println("GF - Generic MinesWeeperTile Face up");
        }
    }
    
}
