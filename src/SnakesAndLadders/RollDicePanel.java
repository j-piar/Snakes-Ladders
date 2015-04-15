package SnakesAndLadders;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//////////////////////////////////////////////////////////// class RollDicePanel
public class RollDicePanel extends JPanel {
    //======================================================= instance variables
    private Die _leftDie;     // component for one die 
    private Die _rightDie;
    private int diceValueSum;
    private JButton rollButton;

    //============================================================== constructor
    /** Create border layout panel with one button and two dice. */
    RollDicePanel() {
        //... Create the dice
        _leftDie  = new Die();
        _rightDie = new Die();
        
        //...Create the button to roll the dice
        rollButton = new JButton("New Roll");
        rollButton.setFont(new Font("Sansserif", Font.PLAIN, 24));
        
        //... Add listener.
       // rollButton.addActionListener(new RollListener());
        //... Layout components
        this.setLayout(new BorderLayout(5, 5));
        this.add(rollButton, BorderLayout.NORTH);
        this.add(_leftDie , BorderLayout.WEST);
        this.add(_rightDie, BorderLayout.EAST);
        
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    public int getDiceValueSum()
    {
        return diceValueSum;
    }

    public void setDiceValueSum(int diceValueSum)
    {
        this.diceValueSum = diceValueSum;
    }
    
    ////////////////////////////////////////// inner listener class RollListener
//    private class RollListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            setDiceValueSum(0);
//            setDiceValueSum(_leftDie.rollDie() +
//                    _rightDie.rollDie());
//        }
//    }

    public JButton getRollButton()
    {
        return rollButton;
    }

    public Die getRightDie()
    {
        return _rightDie;
    }

    public Die getLeftDie()
    {
        return _rightDie;
    }
}