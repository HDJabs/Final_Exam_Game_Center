import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MineSweeperPane extends Pane{
    int cols = 15;
    int rows = 15;
    boolean digging = true;
    int numberOfBombs = 10; // out of 81

    int xStart = 30;
    int yStart = 30;
    MineSweeperController MScontroller;

    MineSweeperTile[][] board = new MineSweeperTile[cols][rows];
    public MineSweeperPane(){
        MScontroller = new MineSweeperController(cols, rows);
        int rando = 0;
        for(int y=rows; y>0; y--){
            for(int x = 1; x<=cols; x++){
                MineSweeperTile tile;
                if(!(rando%5==0)){
                    tile = new DirtTile(x, y);
                }
                else{
                    tile = new BombTile(x, y);
                }
                
                tile.setLayoutX(((x-1)*tile.getTileWidth())+xStart);
                tile.setLayoutY(((rows-y)*tile.getTileHeight())+yStart);
                //System.out.println("The tile, (" + tile + ") is at x:" + tile.getLayoutX() + "  y:" + tile.getLayoutY());
                //tile.setText(tile.toString());                
                tile.setText("?");
                board[x-1][y-1] = tile;
                getChildren().add(board[x-1][y-1]);
                rando++;
            }
        }

        board[0][0].setOnAction(e->{
            System.out.println("test");
            for(int y=rows; y>0; y--){
                for(int x = 1; x<=cols; x++){
                    board[x-1][y-1].flip();
                }
            }
        });

        Button flag = new Button("FLAG");
        flag.setLayoutX(100);
        flag.setFocusTraversable(true);

        flag.setOnAction(e->{
            if(digging){
                startFlagging();
                System.out.println(flag.getStyle()+"peepoo");
                flag.setStyle("-fx-background-color: #ff0000;");//red
            }
            else{
                startDigging();
                flag.setStyle("-fx-background-color: #808080;");//grey

            }
        });
        
        Pane buttPane = new Pane(new BackButton(), flag);
        buttPane.setFocusTraversable(true);

        //StackPane stack = new StackPane();
        //stack.getChildren().add(buttPane);
        //stack.getChildren().add(MSPane);
        //stack.setAlignment(buttPane, Pos.BOTTOM_LEFT);
        //stack.setAlignment(MSPane, Pos.CENTER_LEFT);
        getChildren().add(buttPane);

        
        //App.cont.bPane.setBottom(buttPane);
        setWidth(yStart*2 + new MineSweeperTile(0, 0).getTileWidth()*cols);
        System.out.println("Set width of MineSweeperPane to " + getWidth());
        System.out.println("the current height of the pane is " + getHeight());
        setHeight(xStart*2 + new MineSweeperTile(0, 0).getTileHeight()*rows + new BackButton().getHeight());
        System.out.println("Set the heigt of MineSweeperPane to " + getHeight());

        buttPane.setLayoutY(getHeight()-new BackButton().getHeight());


    }

    public void startFlagging(){
        digging = false;
    }

    public void startDigging(){
        this.digging = true;
    }
}
