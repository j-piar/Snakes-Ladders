package SnakesAndLadders;

/**
 *
 * @author Juraj Piar <juraj2.piar@live.uwe.ac.uk>
 */
public class Board
{
    int tileN;
    
    TileProperties tileProps;
    
    public Board(TileProperties propertyToAdd)
    {
        
    }
    
    public void getTileProperties (TileProperties propertyToGet)
    {
        
    }
}

enum TileProperties
{
    SIZE, ROLE
}

/*
----------------------------------------------------
Board
=====
- tileN : int
---------------
+ Board (TileProperties propertyToAdd)
+ getTileProperties (TileProperties propertyToGet) : void
--------------------------------------------------
----------------------------------------------------

<<enumeration>>
TileProperties
================
SIZE, ROLE

----------------------------------------------------
*/