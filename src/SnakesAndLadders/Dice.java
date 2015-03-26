/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnakesAndLadders;

/**
 *
 * @author vaio
 */
public class Dice {
    private int GeneratedNumber;
    
    public int getGeneratedNumber()
    {
         return GeneratedNumber;
    }
    
    public void setGeneratedNumber(int GeneratedNumber)
    {
        this.GeneratedNumber = GeneratedNumber;
    }
    
    public Dice (int GeneratedNumber)
    {
        this.GeneratedNumber = GeneratedNumber;
    }
}
