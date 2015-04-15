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
    private boolean myTurn = false;
    private int playerTileN = 1;
    
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

    public boolean isMyTurn()
    {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn)
    {
        this.myTurn = myTurn;
    }

    public int getPlayerTileN()
    {
        return playerTileN;
    }

    public void setPlayerTileN(int playerTileN)
    {
        this.playerTileN = playerTileN;
    }
    
}
