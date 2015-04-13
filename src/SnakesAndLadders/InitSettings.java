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
        ArrayList<Color> colOption = new ArrayList<>(Arrays.asList(Color.YELLOW, Color.GREEN, Color.BLUE, Color.PINK));
        
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
                    Color c = (Color)JOptionPane.showInputDialog(
                            null, "Choose player`s colour",
                            "Settings", JOptionPane.PLAIN_MESSAGE,
                            null, 
                            colOption.toArray(),
                            null);
                    getPlayersList().get(i).setPlayerColour(c);
                    colOption.remove(c);
                }
            }
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
                "Choose number of players",
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
