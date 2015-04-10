/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnakesAndLadders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    SNAKE("Snake"),
    EMPTY("Empty"),
    START("Start"),
    END("End");
        
    private final String text;
    
    private TileRole(final String text)
    {
        this.text = text;
    }
    
    private static final List<TileRole> VALUES =
        Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
  
    public static TileRole randRole()
    {
        TileRole tempRole = START;
        while (tempRole == START)
        {
            tempRole = VALUES.get(RANDOM.nextInt(4));
        }
        return tempRole;
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