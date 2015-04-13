/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnakesAndLadders;

import java.awt.Point;
import java.awt.Color;

/**
 *
 * @author Juraj Piar <juraj2.piar@live.uwe.ac.uk>
 */
public class Player
{ 
    private Color playerColour;
    private String playerName;
    private final boolean inGame;
    private Point playerPosition;
    
    public Player(String playerName)
    {
        this.playerName = playerName;
        inGame = true;
        playerPosition = new Point(0,0);
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public Color getPlayerColour()
    {
        return playerColour;
    }

    public void setPlayerColour(Color playerColour)
    {
        this.playerColour = playerColour;
    }

    public Point getPlayerPosition()
    {
        return playerPosition;
    }

    public void setPlayerPosition(Point playerPosition)
    {
        this.playerPosition = playerPosition;
    }
    
}
