import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

public class MineSweeperTile extends Button{
    int xCord, yCord;
    boolean safe;
    int height = 45;
    int width = 45;
    boolean faceUp = false;
    int numberOfAdjBombs =0;
    MineSweeperController MScontroller;
    boolean flagged = false;
    
    public MineSweeperTile(){
        initialize();
    }

    public MineSweeperTile(int xCord, int yCord){
        setCords(xCord, yCord);
        initialize();
    }

    public void initialize(){
        setMinHeight(height);
        setMinWidth(width);
        setText("G");
        setStyle("-fx-background-color: #270aa8;");
        setStyle("-fx-border-color: #909090; -fx-border-width: 1px;");
        setOnMouseClicked(e->{
            if(e.getButton()==MouseButton.SECONDARY && !faceUp){
                flag();
            }
        });
        setOnAction(e->{
            if(isFlagged()){
                //do nothing
            }
            else{
                flip();
            }
            MScontroller.checkWinCondition();
        });
    }

    public void setCords(int xCord, int yCord){
        this.xCord = xCord;
        this.yCord = yCord;
        setLayoutX(xCord);
        setLayoutY(yCord);
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
        if(!faceUp){
            faceUp = true;
        }
    }

    public void forceFlip(){
        faceUp=false;
        setText("!");//
    }

    public int getNumberOfAdjBombs(){
        return this.numberOfAdjBombs;
    }

    public boolean hasBeenFlipped(){
        if(faceUp)
            return true;
        else return false;
    }

    public boolean isSafe(){
        return this.safe;
    }

    public void setController(MineSweeperController MScontroller){
        this.MScontroller = MScontroller;
    }

    public void flag(){
        if(!flagged){
            setText("F");
            setStyle("-fx-text-fill: #ff0000; -fx-border-color: #808080; -fx-border-width: 5px;");
            
        }
        else{
            setText(" ");
            setStyle("-fx-border-width: 0px;");
        }
        flagged = !flagged;
    }

    public boolean isFlagged(){
        return this.flagged;
    }
    
}
