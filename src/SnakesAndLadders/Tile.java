package SnakesAndLadders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;


/**
 *
 * @author 
 */
public class Tile extends JPanel
{
    private Point tileSize;
    private Object power;
    private Point screenPosition;
    private Color tileColor;
    private int tileN;
    
    public Tile (Point tileSize, TileRole power) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException 
    {
        this.tileSize = tileSize;
        this.power = ObjectMaker.makeObject(power.toString());
    }
    public Point getTileSize()
    {
        return tileSize;
    }

    public void setTileSize(Point tileSize)
    {
        this.tileSize = tileSize;
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        String printRole = this.getPower().getPowerName().toString();
        super.paintComponent (g);
        g.setColor (Color.BLACK);
        g.drawRect (0, 0, getWidth(), getHeight());
        //g.drawString ((printRole.equals(TileRole.EMPTY.toString()) ? " " : printRole), 20, 20);
        g.drawString(String.valueOf(tileN+1), 30, 30);
    }

    public PowerTile getPower()
    {
        return (PowerTile)power;
    }

    public void setPower(TileRole power)
    {
        try
        {
            this.power = ObjectMaker.makeObject(power.toString());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex)
        {
            Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Point getScreenPosition()
    {
        return screenPosition;
    }

    public void setScreenPosition(Point screenPosition)
    {
        this.screenPosition = screenPosition;
    }

    public Color getTileColor()
    {
        return tileColor;
    }

    public void setTileColor(Color tileColor)
    {
        this.tileColor = tileColor;
    }

    public int getTileN()
    {
        return tileN;
    }

    public void setTileN(int tileN)
    {
        this.tileN = tileN;
    }
}