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
public class Ladder implements PowerTile
{
    private Point ladderBottom, ladderTop;    
    
    public Ladder(){}
    
    @Override
    public TileRole getPowerName ()
    {
        return TileRole.valueOf(this.getClass().getName().substring(this.getClass().getName().indexOf(".")+1).trim().toUpperCase());
    }
    
    @Override
    public boolean IsDirectional ()
    {
        return true;
    }
    
    @Override
    public Point getStartPosition ()
    {
        return ladderBottom;
    }    
    @Override
    public void setStartPosition (Point startPosition)
    {
        this.ladderBottom = startPosition;
    }
    
    @Override
    public Point getEndPosition ()
    {
        return ladderTop;
    }
    @Override
    public void setEndPosition (Point endPosition)
    {
        this.ladderTop = endPosition;
    }
    
}
