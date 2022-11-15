public class BombTile extends MineSweeperTile{
    public BombTile(int xCord, int yCord){
        super(xCord, yCord);
    }

    @Override
    public void flip(){
        System.out.println("Bomb Tile at " + getXCord() + ", " + getYCord());
        System.out.println("Kaboom!");
        setText("!!");
    }
}
