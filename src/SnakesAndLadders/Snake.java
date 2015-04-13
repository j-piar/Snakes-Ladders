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
public class Snake implements PowerTile
{
    private Point snakeHead, snakeEnd;
    
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
        return snakeHead;
    }   
    @Override
    public void setStartPosition (Point startPosition)
    {
        this.snakeHead = startPosition;
    }
    
    @Override
    public Point getEndPosition ()
    {
        return snakeEnd;
    }
    @Override
    public void setEndPosition (Point endPosition)
    {
        this.snakeEnd = endPosition;
    }
}
