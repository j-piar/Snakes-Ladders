/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnakesAndLadders;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author vaio
 */
public class Die {
    
    private Color colour;
    private int size;
    
    public int rollDie()
    {
        return new Random().nextInt(6);
    }

    public Color getColour()
    {
        return colour;
    }

    public void setColour(Color colour)
    {
        this.colour = colour;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }
}
