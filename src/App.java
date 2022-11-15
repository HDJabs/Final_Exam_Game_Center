import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{

    static SceneSetterController cont;

    @Override
    public void start(Stage primaryStage){
        //primaryStage has a Scene has a Pane
        cont = new SceneSetterController(primaryStage);

        cont.openGameSelectMenu();


        //END LAMBDA
        /////////////////



        primaryStage.show();
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        launch(args);
    }


}
