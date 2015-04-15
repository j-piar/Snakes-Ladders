/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnakesAndLadders;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Juraj Piar <juraj2.piar@live.uwe.ac.uk>
 */
public final class Game extends JApplet 
{
    private int lD, rD;
    private Board board;
    private boolean gameOver;
    private RollDicePanel diceRoll = new RollDicePanel();
    //=============================================== applet constructor
    /** Applet constructor requires putting the panel in applet.*/
    public Game() 
    {
        this.setContentPane(diceRoll);
        board = new Board(InitSettings.chooseBoardSize());
        
        showDice();
        diceRoll.getRollButton().addActionListener(new RollListener());
        
    }
    
    //====================================================== method main
    /** Create JFrame and set content pane to a RollDicePanel. */
    public void showDice() 
    {
        JFrame window = new JFrame("Dice");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(diceRoll);
        window.pack();
        System.out.println(window.getContentPane().getSize());
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    private void playerGo(Player p)
    {
        
        int deltaTile = board.getNumberOfTiles() - (p.getPlayerTileN() + diceRoll.getDiceValueSum());
        while (diceRoll.getDiceValueSum() > 0)
        {
//            int deltaTile = (board.getNumberOfTiles()) - 
//                    (board.getBoard().get(p.getPlayerPosition().y).
//                            get(p.getPlayerPosition().x).getTileN() + 
//                    diceRoll.getDiceValueSum());
            
            //Point boardMax = new Point(board.getSideA() - 1, board.getSideA() - 1);
//            int a = diceRoll.getDiceValueSum() - board.getSideA() <= 0 ? 
//                    diceRoll.getDiceValueSum() :
//                    diceRoll.getDiceValueSum() - board.getSideA();
//            int b = a + p.getPlayerPosition().y > board.getSideA() ?
//                    a/2 : a;
//            int c = a + p.getPlayerPosition().y > board.getSideA() ?
//                    a/2 : 0;
            System.out.println("nOTiles - playerT + diceRoll: " + 
                    board.getNumberOfTiles() + " - " + 
                    p.getPlayerTileN() + " + " +
                    diceRoll.getDiceValueSum() + " = " + deltaTile);
            
            if (deltaTile == 0)
            {
                gameOver = true;
                break;
            }
            else if (deltaTile > 0)
            {
                if (p.getPlayerPosition().y < board.getSideA() - 1)
                {
                    System.out.println(p.getPlayerPosition().x + " : " + p.getPlayerPosition().y);
                    
                    //board.getBoard().get(p.getPlayerPosition().y).get(p.getPlayerPosition().x).setBackground(Color.red);
                    p.setPlayerPosition(new Point (p.getPlayerPosition().x, p.getPlayerPosition().y + 1));
                    p.setPlayerTileN(p.getPlayerTileN() + 1);
                    
                    System.out.println(diceRoll.getDiceValueSum() +
                            "Player`s current board tileN: " + p.getPlayerTileN());
                }
                else
                {
                    p.setPlayerPosition(new Point (p.getPlayerPosition().x + 1, 0));
                    p.setPlayerTileN(p.getPlayerTileN() + 1);
                }
                if (board.getNumberOfTiles() <= (p.getPlayerTileN() + diceRoll.getDiceValueSum()))
                {
                        JOptionPane.showMessageDialog(this, "Too far man.");
                        break;
                }
                diceRoll.setDiceValueSum(diceRoll.getDiceValueSum() - 1);
                board.repaint();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Too far man.");
                break;
            }
        }
        diceRoll.setDiceValueSum(0);
    }
    
    private Player choosePlayerTurn(ArrayList<Player> list)
    {
        Player p = null;
         for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).isMyTurn())
            {
                p = list.get(i);
                list.get(i).setMyTurn(false);
                list.get(i == list.size()-1 ? 0 : i+1).setMyTurn(true);
                break;
            }
        }
        return p;
    }
    
    private class RollListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            diceRoll.setDiceValueSum(0);
            lD = diceRoll.getLeftDie().rollDie();
            rD = diceRoll.getRightDie().rollDie();
            diceRoll.setDiceValueSum( lD + rD);
                    
            System.out.println("DiceSum: lD + rD : " + lD + " + " + rD + " = " + diceRoll.getDiceValueSum());
            Player p = choosePlayerTurn(board.getListOPlayers());
            
            //System.out.println("Player`s RollListener tileN: " + board.getBoard().get(p.getPlayerPosition().y).get(p.getPlayerPosition().x).getTileN()+1);
            playerGo(p);
            checkForTraps(p);
            board.repaint();
        }

        private void checkForTraps(Player p)
        {
            Tile playersTile;
            Point tmpS, tmpE;
            int deltaTmp;
            do
            {
                playersTile = (Tile)board.getBoard().get(p.getPlayerPosition().y).get(p.getPlayerPosition().x);
                if (playersTile.getPower().IsDirectional())
                {
//                    if (playersTile.getPower().toString() == TileRole.SNAKE.toString())
//                        playersTile.setBackground(Color.red);
//                    else
//                        playersTile.setBackground(Color.GREEN);
                    System.out.println("tile fit check: " + playersTile.getPower().getStartPosition().toString() + " : " + p.getPlayerPosition().toString());
                    System.out.println("tileN before trap: " + p.getPlayerTileN());
                    tmpS = playersTile.getPower().getStartPosition();
                    tmpE = playersTile.getPower().getEndPosition();
                    deltaTmp = (tmpE.x * tmpE.y) - (tmpS.x * tmpS.y);
                    System.out.println("deltaTmp: " + deltaTmp);
                    p.setPlayerTileN(p.getPlayerTileN() + deltaTmp);
                    p.setPlayerTileN(playersTile.getPower().getEndPosition().x+1 *
                            playersTile.getPower().getEndPosition().y+1);
                    p.setPlayerPosition(playersTile.getPower().getEndPosition());
                    System.out.println("tileN after trap: " + p. getPlayerTileN());
                }
            }while (playersTile.getPower().IsDirectional());
        }
    }
}
