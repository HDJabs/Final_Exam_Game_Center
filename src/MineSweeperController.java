import javax.swing.text.DefaultStyledDocument;

import javafx.scene.text.Text;

public class MineSweeperController {
    int cols;
    int rows;
    boolean gameOver = false;
    Text emoticonText;

    MineSweeperTile[][] board;
    public MineSweeperController(MineSweeperTile[][] board){
        System.out.println("New MineSweeperController created!");
        this.cols = board[0].length;
        this.rows=board[1].length; 
        this.board = board;
    }



    public void updateNumberOfAdjacentBombs(){ 
        for(int c=0; c<cols; c++){
            for(int r=0; r<rows; r++){
                //GOES THROUGH WHOLE BOARD 
                if(board[c][r].getNumberOfAdjBombs()==0){
                //nested for loop to "go around" tile at [c][r]
                    for(int cmod=-1; cmod <= 1; cmod++){
                        for(int rmod=-1; rmod<=1; rmod++){

                            if(c+cmod<cols && c+cmod>=0 && r+rmod<rows && r+rmod>=0){//if within margins of board, also excluding self-tile
                                if(!board[c+cmod][r+rmod].isSafe()){ // if adj tile is a bomb
                                    board[c][r].setNumberOfAdjBombs(board[c][r].getNumberOfAdjBombs()+1);
                                }
                            }

                        }
                    }
                }
            }
        }
   
    }

    public void updateSurroundingTiles(int x, int y){ //this method is a recursive loop
        if(board[x][y].getNumberOfAdjBombs()==0){
            for(int xmod =-1; xmod <= 1; xmod++){
                for(int ymod = -1; ymod<=1; ymod++){
                    int tmpx = x+xmod;//hypothetical x
                    int tmpy = y+ymod;
                    if(tmpx>=0 && tmpx < rows && tmpy>=0 && tmpy < cols){
                        board[tmpx][tmpy].fire();
                        if(board[tmpx][tmpy].getNumberOfAdjBombs()==0 && !board[tmpx][tmpy].hasBeenFlipped()){
                            updateSurroundingTiles(tmpx, tmpy);
                        }
                    }
                }
            }
        }
    }

    public void igniteAllBombs(){
        gameOver = true;
        for(int c=0; c<cols; c++){
            for(int r=0; r<rows; r++){
                if(!board[c][r].isSafe())
                    board[c][r].flip();
            }
        }
        emoticonText.setText(":(");
    }

    public void checkWinCondition(){
        boolean gameIsFinished = true;
        for(int x=0; x<cols; x++){
            for(int y=0; y<rows; y++){
                if((board[x][y].isSafe() && !board[x][y].faceUp) || (!board[x][y].isSafe() && board[x][y].faceUp)){
                    gameIsFinished = false;
                }
            }
        }

        if(gameIsFinished){
            System.out.println("game is finished");
            emoticonText.setText(":)");
        }
    }

    public void addEmoticonText(Text emoticonText){
        this.emoticonText = emoticonText;

    }

}
