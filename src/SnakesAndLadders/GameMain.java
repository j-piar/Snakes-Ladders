package SnakesAndLadders;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author
 */
public class GameMain
{
    private static final ArrayList<Die> dice = new ArrayList<>(Arrays.asList(new Die(), new Die()));
    
    public static void main(String[] args) {
        Board board = new Board(InitSettings.chooseBoardSize());
        
        do
        {
            int rolledValue = 0;
            for (Die die : dice)
            {
                rolledValue += die.rollDie();
            }
            
        }
        while (board.getListOPlayers().size() >= 2);
    }
}

