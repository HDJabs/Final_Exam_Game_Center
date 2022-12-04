import javafx.scene.control.Button;

public class ClearButton extends Button{
    boolean cleared = false;

    public ClearButton(){
        super("Clear Board (debug)");
        System.out.println("New Clear Button has been created");
        setFocusTraversable(true);
    }

    public boolean isCleared(){
        return this.cleared;
    }

    public void setCleared(boolean b){
        this.cleared=b;
    }

    
        
}
