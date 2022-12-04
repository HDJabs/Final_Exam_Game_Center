import java.lang.Math;

public class RandomTileGenerator {
    int rows;
    int cols;
    boolean[][][] field;
    public RandomTileGenerator(int rows, int cols){
        System.out.println("New RandomCombonationGenerator created!");
        this.rows = rows;
        this.cols = cols;
    }

    public RandomTileGenerator(MineSweeperTile[][] board){
        System.out.println("New RandomCombonationGenerator created!");
        this.rows = board[0].length;
        this.cols = board[1].length;
        field = new boolean[2][rows][cols]; //this is a 3d array!
        //[0][][] is "is this spot free to call from? (each spot will only be called from once)"  
        //[1][][] is "is this spot a dirt tile?"
        
    }

    public void setBombs(int bombs){
        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                if(bombs>0)
                    field[1][r][c] = false; // if there are still bombs to place, the next spot in the 
                else                        // array will be flagged FALSE to later say "place a bomb here"
                    field[1][r][c] = true;
                
                bombs--;
                field[0][r][c] = true; // all spot are initially free/ok to call from
            }
        }
    }

    public MineSweeperTile generateRandomTile(){
        int randRow;
        int randCol;

        while(true){
            randCol = (int)(Math.random()*this.cols);
            randRow = (int)(Math.random()*this.rows);
            
            if(field[0][randCol][randRow]){//if its free to use
                field[0][randCol][randRow]=false;//no longer ok to use this index
                if(field[1][randCol][randRow]){ //if its safe
                    return new DirtTile();
                }
                else{
                    return new BombTile();
                }
                
            }

        }//END WHILE LOOP

    }// END GENERATE RANDOM TILE

}
