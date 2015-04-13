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
    
    public Tile (Point tileSize, TileRole power) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException 
    {
        this.tileSize = tileSize;
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
    
    @Override
    protected void paintComponent(Graphics g)
    {
        String printRole = this.getPower().getPowerName().toString();
        super.paintComponent (g);
        g.setColor (Color.BLACK);
        g.drawRect (0, 0, tileSize.x, tileSize.y);
        g.drawString ((printRole.equals(TileRole.EMPTY.toString()) ? " " : printRole), 20, 20);
    }

    public PowerTile getPower()
    {
        return (PowerTile)power;
    }

    public void setPower(TileRole power)
    {
        try
        {
            this.power = ObjectMaker.makeObject(power);
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
}