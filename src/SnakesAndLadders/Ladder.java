/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnakesAndLadders;

import java.awt.Point;
import java.net.URL;

/**
 *
 * @author Juraj Piar <juraj2.piar@live.uwe.ac.uk>
 */
public class Ladder implements PowerTile
{
    private Point ladderBottom, ladderTop;
    private String imageName = "ladder.png";
    
    public Ladder()
    {
    }
    
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
    
    
    
//    @Override
//    public void paintComponent(Graphics g)
//    {
//        super.paintComponent(g);
//        g.drawPolygon(new int[] {ladderBottom.x, ladderTop.x} , new int[] {ladderBottom.y, ladderTop.y}, 2);
//            g.fillOval(ladderTop.x, ladderTop.y, 20, 20);
//    }

//    public Shape getLadder()
//    {
//        return ladder;
//    }

    public String getImageName()
    {
        return imageName;
    }

    public void setImageName(String imageName)
    {
        this.imageName = imageName;
    }
    
//    @Override
//    public URL getImgURL()
//    {
//        URL url = null;
//        try {
//            url = this.getClass().getResource(imageName);
//        }
//        catch(Exception e){}
//        return url;
//    }
}
