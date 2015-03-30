/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnakesAndLadders;

/**
 *
 * @author Juraj Piar <juraj2.piar@live.uwe.ac.uk>
 */
enum TileProperties
{ 
    SIZE,
    ROLE
}

public enum TileRole
{
    LADDER("Ladder"), 
    SNAKE("Snake");
    
    private final String text;
    
    private TileRole(final String text)
    {
        this.text = text;
    }
    @Override
    public String toString() {
        return text;
    }
}
/*
----------------------------------------------------

<<enumeration>>
TileProperties
================
SIZE, ROLE

----------------------------------------------------
*/