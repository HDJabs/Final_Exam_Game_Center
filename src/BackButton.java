import javafx.scene.control.Button;

public class BackButton extends Button{
  //public BackButton(int height, int width, int x, int y){
    public BackButton(){
        super("Back To Games");
        setHeight(30);
        setWidth(90);
        setOnAction( e->{
            System.out.println("Back Button has been pressed!");
            App.cont.openGameSelectMenu();
        });
    }   
}