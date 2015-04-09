package SnakesAndLadders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JPanel;


/**
 *
 * @author 
 */
public class Tile extends JPanel
{
    private int tileSize;
    private TileRole powerRole;
    private Object power = null;

    public Tile (int tileSize)
    {
        this.tileSize = tileSize;
    }
    
    public Tile (int tileSize, TileRole power) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException 
    {
        this.tileSize = tileSize;
        this.powerRole = power;
        this.power = ObjectMaker.makeObject(power);
    }
    public int getTileSize()
    {
        return tileSize;
    }

    public void setTileSize(int tileSize)
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
    
    public Point getPowerDirection ()
    {
       return ((PowerTile)power).getEndPosition();
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(tileSize, tileSize, tileSize, tileSize);
    }
}
