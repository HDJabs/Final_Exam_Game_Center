import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DirtTile extends MineSweeperTile{
    public DirtTile(){}
    
    public DirtTile(int xCord, int yCord, MineSweeperController MScontroller){
        super(xCord, yCord);
        super.setController(MScontroller);
        System.out.println("New DirtTile created!");
    }

    @Override
    public void flip(){
        if(!faceUp){ // if face down, 
            faceUp = true; // , turn face up
            setText(Integer.toString(numberOfAdjBombs));
            setFont(new Font(20));
            setStyle("-fx-border-color: #c7c7c7; -fx-border-width: 1px; -fx-background-color: #b1b1b1;");

            switch(numberOfAdjBombs){
                case 0:
                    setText(" ");
                    MScontroller.updateSurroundingTiles(xCord-1, yCord-1);//this method is used to start a recursive loop
                    break;
                
                case 1:
                    setTextFill(Color.BLUE);
                    break;

                case 2:
                    setTextFill(Color.GREEN);
                    break;

                case 3:
                    setTextFill(Color.RED);
                    break;

                case 4:
                    setTextFill(Color.PURPLE);
                    break;

                case 5:
                    setTextFill(Color.MAROON);
                    break;

                case 6:
                    setTextFill(Color.TURQUOISE);
                    break;

                case 7:
                    setTextFill(Color.BLACK);
                    break;

                case 8:
                    setTextFill(Color.GREY);
                    break;

            }//end switch(numberOfAdjBombs)
            
        }//end if(!faceUp)
        
    }//end flip()

    @Override
    public boolean isSafe(){
        return true;
    }
}
