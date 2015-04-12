/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnakesAndLadders;

import java.awt.Point;

/**
 *
 * @author Juraj Piar <juraj2.piar@live.uwe.ac.uk>
 */
public class End implements PowerTile
{
    Point position;
    @Override
    public boolean IsDirectional() { return false; }

    @Override
    public Point getStartPosition()
    {
        return position;
    }

    @Override
    public void setStartPosition(Point startPosition)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Point getEndPosition()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEndPosition(Point endPosition)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TileRole getPowerName()
    {
        return TileRole.valueOf(this.getClass().getName().substring(this.getClass().getName().indexOf(".")+1).trim().toUpperCase());
    }
    
}
