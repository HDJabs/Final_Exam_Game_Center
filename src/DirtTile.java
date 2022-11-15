public class DirtTile extends MineSweeperTile{
    public DirtTile(int xCord, int yCord){
        super(xCord, yCord);
    }

    @Override
    public void flip(){
        System.out.println("Dirt Tile a " + getXCord() + ", " + getYCord());
        if(!faceUp){
            faceUp = true;
            setText(null);
        }
    }
}
