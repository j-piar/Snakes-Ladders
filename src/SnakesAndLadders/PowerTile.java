/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnakesAndLadders;

import java.awt.Point;
import java.util.Random;

/**
 *
 * @author Juraj Piar <juraj2.piar@live.uwe.ac.uk>
 */
public interface PowerTile
{   
    static final Random RANDOM = new Random();
    boolean IsDirectional ();
    Point getStartPosition ();
    void setStartPosition (Point startPosition);
    Point getEndPosition ();
    void setEndPosition (Point endPosition);
    TileRole getPowerName();
}
