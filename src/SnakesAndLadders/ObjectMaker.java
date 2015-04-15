/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnakesAndLadders;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Juraj Piar <juraj2.piar@live.uwe.ac.uk>
 */
public class ObjectMaker
{
    public static Object makeObject(String className) 
            throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException 
    {
        Object o = null;

        try {
            
           // o = Class.forName(power.toString()).newInstance();
             Class clazz = Class.forName("SnakesAndLadders." + className);
                Constructor<?> constructor = clazz.getConstructor();
                o = constructor.newInstance();
                
        } catch (ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return o;
    }
}
