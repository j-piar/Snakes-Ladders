/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnakesAndLadders;

import java.util.ArrayList;
import java.util.regex.Matcher;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;
import java.awt.Color;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 *
 * @author Juraj Piar <juraj2.piar@live.uwe.ac.uk>
 */
public class InitSettings
{
    private int nOPlayers = 0;
    private ArrayList<Player> playersList = new ArrayList<>();
    
    private int chooseNumberOPlayers()
    {
        Object[] options = {"1", "2", "3", "4"};
        String s; 
        do
        {
            s = (String)JOptionPane.showInputDialog(
                null,
                "Choose number of players",
                "Settings",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                "4");
        }
        while ((s == null) && (!(s.length() > 0)));
        return Integer.parseInt(s);
    }
    
    public void choosePlayers()
    {
        nOPlayers = chooseNumberOPlayers();
        Pattern p = Pattern.compile("^[A-Z]'?[- a-zA-Z]( [a-zA-Z])*$");
        ArrayList<String> colours = new ArrayList<>(Arrays.asList("Yellow", "Green", "Blue", "Pink"));
        do
        {
            for (int i = 0; i < nOPlayers; i++)
            {
                getPlayersList().add(new Player((String) 
                        JOptionPane.showInputDialog(
                    null, "Choose player`s name",
                    "Settings", JOptionPane.PLAIN_MESSAGE,
                    null, null, null)));
                Matcher m = p.matcher(getPlayersList().get(i).getPlayerName());
                if (m.matches())
                    getPlayersList().remove(i);
                else
                {
                    Color c;
                    String s = (String)JOptionPane.showInputDialog(
                            null, "Choose player`s colour",
                            "Settings", JOptionPane.PLAIN_MESSAGE,
                            null, colours.toArray(),
//                            colOption.toArray(),
                            null);
                    try
                    {
                        Field field = Class.forName("java.awt.Color").getField(s.toLowerCase());
                        c = (Color) field.get(null);
                    }catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
                    {c = null;}
                    getPlayersList().get(i).setPlayerColour(c);
                    colours.remove(s);
                }
            }
            getPlayersList().get(0).setMyTurn(true);
        }
        while (getPlayersList().size() != nOPlayers);
    }
            
    /**
     *
     * @return
     */
    public static int chooseBoardSize()
    {
        String s; 
        do
        {
            s = (String)JOptionPane.showInputDialog(
                null,
                "Choose game size",
                "Settings",
                JOptionPane.PLAIN_MESSAGE,
                null, null, "100");
        }
        while ((s == null) && (!(s.length() > 0)));
        return Integer.parseInt(s);
    }

    public ArrayList<Player> getPlayersList()
    {
        return playersList;
    }
}
