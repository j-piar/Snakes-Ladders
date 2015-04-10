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
public class Empty implements PowerTile
{
    
    @Override
    public TileRole getPowerName ()
    {
        return TileRole.valueOf(this.getClass().getName().substring(this.getClass().getName().indexOf(".")+1));
    }
    
    @Override
    public boolean IsDirectional ()
    {
        return false;
    }
    
    @Override
    public Point getStartPosition ()
    {
        return null;
    }    
    @Override
    public void setStartPosition (Point startPosition)
    {
        
    }
    
    @Override
    public Point getEndPosition ()
    {
        return null;
    }
    @Override
    public void setEndPosition (Point endPosition)
    {
        
    }
    
}
