import java.util.zip.DataFormatException;

import javax.net.ssl.TrustManager;
import javax.swing.border.EmptyBorder;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MineSweeperPane extends Pane{
    int cols = 15;
    int rows = 15;
    boolean digging = true;
    int numberOfBombs = 40; // out of 225

    int xStart = 30;
    int yStart = 60;

    int xFreeSpace;
    int yFreeSace;

    MineSweeperTile[][] board = new MineSweeperTile[cols][rows];
    MineSweeperController MScontroller = new MineSweeperController(board);
    Text emoticonText = new Text("._.");


    public MineSweeperPane(){

        createStartingGrid();

        MineSweeperTile unimportantTile = new MineSweeperTile();
        emoticonText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50)); 
        emoticonText.setX(xStart + (cols * unimportantTile.getTileWidth())/2);
        emoticonText.setY(40);
        getChildren().add(emoticonText);
        
        BackButton btnBack = new BackButton();
        btnBack.setLayoutX(10);

        //setting Clear Button
        ClearButton btnClear = new ClearButton();
        btnClear.setLayoutX(btnBack.getLayoutX() + btnBack.getWidth() +10);
        btnClear.setOnAction(e->{
            for(int y=rows; y>0; y--){
                for(int x = 1; x<=cols; x++){
                    board[x-1][y-1].flip();
                }
            }
        });

        Button btnReset = new Button("Reset");
        btnReset.setLayoutX(btnClear.getLayoutX() + 160);
        btnReset.setFocusTraversable(true);
        btnReset.setOnAction(e->{
            removeMineSweeperButtons();
            createStartingGrid();
        });

        //setting Button Pane
        Pane buttPane = new Pane(btnBack, btnReset, btnClear);
        buttPane.setFocusTraversable(true);

        getChildren().add(buttPane);

        
        //App.cont.bPane.setBottom(buttPane);
        setWidth(yStart*2 + new MineSweeperTile(0, 0).getTileWidth()*cols);
        setHeight(xStart*2 + new MineSweeperTile(0, 0).getTileHeight()*rows + new BackButton().getHeight());

        buttPane.setLayoutY(getHeight() - btnBack.getHeight());
        MScontroller.addEmoticonText(emoticonText);

    }

    public void startFlagging(){
        digging = false;
    }

    public void startDigging(){
        this.digging = true;
    }

    public void createStartingGrid(){//needs a better name
        emoticonText.setText("._.");
        MScontroller.gameOver=false;
        for(int y=rows; y>0; y--){
            for(int x = 1; x<=cols; x++){
                MineSweeperTile tile;

                tile = new MineSweeperTile(x, y);
                tile.setController(MScontroller);
                tile.setLayoutX(((x-1)*tile.getTileWidth())+xStart);
                tile.setLayoutY(((rows-y)*tile.getTileHeight())+yStart);
                tile.setText(" ");

                board[x-1][y-1] = tile;
                getChildren().add(board[x-1][y-1]);

                tile.setOnAction(e->{
                    //vv  removes board of temporary buttons 
                    removeMineSweeperButtons();
                    xFreeSpace = tile.getXCord();
                    yFreeSace = tile.getYCord();
                    setGame();
                    board[xFreeSpace-1][yFreeSace-1].fire();
                });

            }
        }
    }

    public void removeMineSweeperButtons(){
        for(int y=rows; y>0; y--){
            for(int x = 1; x<=cols; x++){
                getChildren().remove(board[x-1][y-1]);
                
            }
        }
    }

    public void setGame(){

        RandomTileGenerator randTile = new RandomTileGenerator(board);
        randTile.setBombs(numberOfBombs);


        //DESIGNATING THE FIRST 9 0 SQUARES
        int indexOfTilesNotToInitialize[][] = new int[9][2];//array of arrays. [array 1, 2, 3, etc][1st, or 2nd number]
        int index =0;
        for(int ymod=-1; ymod<=1; ymod++){    
            for(int xmod=-1; xmod<=1; xmod++){
                int tmpy = ymod+yFreeSace;
                int tmpx = xmod+xFreeSpace;
                if(tmpx>0 && tmpx < rows && tmpy>0 && tmpy< cols){
                    DirtTile dirtTile = new DirtTile(tmpx, tmpy, MScontroller);
                    dirtTile.setLayoutX(((tmpx-1)*dirtTile.getTileWidth())+xStart);
                    dirtTile.setLayoutY(((rows-tmpy)*dirtTile.getTileHeight())+yStart);  
                    dirtTile.setText(" ");  
                    board[tmpx-1][tmpy-1] = dirtTile;  
     
                    getChildren().add(board[tmpx-1][tmpy-1]);
                    indexOfTilesNotToInitialize[index][0] += tmpx;
                    indexOfTilesNotToInitialize[index][1] += tmpy;
                    index++;
                }
            }
        }

        //NOW TO THE REST OF THE GRID
        for(int y=rows; y>0; y--){
            for(int x = 1; x<=cols; x++){
                MineSweeperTile tile;
                //first we have to check if x, y can be initialized
                boolean reserved = false;
                for(int i=0; i<9; i++){
                    if(indexOfTilesNotToInitialize[i][0]==x && indexOfTilesNotToInitialize[i][1]==y){//if current x,y is ever in the array list
                        reserved = true;
                    }
                }


                if(!reserved){//not the free space
                    tile = randTile.generateRandomTile();
                    tile.setCords(x, y);
                    if(tile.isSafe())
                        tile.setText(" ");
                    else
                        tile.setText(" ");

                    tile.setController(MScontroller);
                    tile.setLayoutX(((x-1)*tile.getTileWidth())+xStart);
                    tile.setLayoutY(((rows-y)*tile.getTileHeight())+yStart);    
                    board[x-1][y-1] = tile;

                    getChildren().add(board[x-1][y-1]);
                }
            }
        }
    MScontroller.updateNumberOfAdjacentBombs();
    }
}
