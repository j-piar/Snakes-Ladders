package SnakesAndLadders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.swing.JPanel;


/**
 *
 * @author 
 */
public class Tile extends JPanel
{
    private Point tileSize;
    private TileRole powerRole;
    private Object power;
    
    public Tile (Point tileSize, TileRole power) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException 
    {
        this.tileSize = tileSize;
        this.powerRole = power;
        this.power = ObjectMaker.makeObject(power);
    }
    public Point getTileSize()
    {
        return tileSize;
    }

    public void setTileSize(Point tileSize)
    {
        this.tileSize = tileSize;
    }
    
    
    public TileRole getPowerRole()
    {
        return powerRole;
    }

    public void setPowerRole (TileRole power)
    {
        this.powerRole = power;
    }
    
    public Point getPowerDirection()
    {
       return ((PowerTile)power).getEndPosition();
    }
    
    public void setPowerDirection (Point powerEnd)
    {
        ((PowerTile)power).setEndPosition(powerEnd);
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        String printRole = this.getPowerRole().toString();
        super.paintComponent (g);
        g.setColor (Color.BLACK);
        g.drawRect (0, 0, tileSize.x, tileSize.y);
        g.drawString ((printRole.equals(TileRole.EMPTY.toString()) ? " " : printRole), 20, 20);
    }
}