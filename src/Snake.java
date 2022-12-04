import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Snake  extends Circle
{
    private List<Circle> tails;
    private int length = 0;
    private Moving current;
    private static final int CHANGE = 5;

    public Snake(double d1, double d2, double d3)
    {
        super(d1,d2,d3);
        tails = new ArrayList<>();
        current = Moving.UP;

    }

    public void step()
    {
        for(int i = length - 1; i >= 0; i--)
        {
            if (i == 0) {
            tails.get(i).setCenterX(getCenterX());
            tails.get(i).setCenterY(getCenterY());
            }
            else {
                tails.get(i).setCenterX(tails.get(i - 1).getCenterX());
                tails.get(i).setCenterY(tails.get(i - 1).getCenterY());
            }
        }

        if(current == Moving.UP) {
            setCenterY(getCenterY() - CHANGE);
        }
        else if(current == Moving.DOWN) {
            setCenterY(getCenterY() + CHANGE);
        }
        else if(current == Moving.LEFT) {
            setCenterX(getCenterX() - CHANGE);
        }
        else if(current == Moving.RIGHT) {
            setCenterX(getCenterX() + CHANGE);
        }

    }

    public boolean hitSnake()
    {
        for(int i = 0; i < length; i++)
        {
            if(this.getCenterX()==tails.get(i).getCenterX()
            && this.getCenterY()==tails.get(i).getCenterY()){
            return true;
            }      
        }
        return false;
    }

    public int getLength()
    {
        return length;
    }

    public Moving getMoving()
    {
        return current;
    }
    
    public void setCurrent(Moving current) {
        this.current = current;
    }

    private Circle endTail()
    {
        if(length == 0)
        {
            return this;
        }
        return tails.get(length-1);
    }

    public void eat(Circle food)
    {
        Circle tail = endTail();
        food.setCenterX(tail.getCenterX());
        food.setCenterY(tail.getCenterY());
        food.setFill(Color.GREEN);
        tails.add(length++, food);
    }

}
