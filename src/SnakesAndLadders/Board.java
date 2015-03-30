package SnakesAndLadders;

import java.util.ArrayList;

/**
 *
 * @author Juraj Piar <juraj2.piar@live.uwe.ac.uk>
 */
public class Board
{
    int tileN;
    Tile tile;
    ArrayList<ArrayList<Tile>> board;
    int numberOfTiles = 100;
    int nOfTilesOnSide;
    
    public Board(int tileSize, int numberOfTiles)
    {
        try
        {   nOfTilesOnSide = (int)Math.pow(numberOfTiles, 2);
        }catch(Exception e)
        { return;
        }
        
        board = new ArrayList<>();
        for (int i = 0; i < nOfTilesOnSide ; i++)
        {
            board.add(new ArrayList<>());
            for (int j = 0; j < nOfTilesOnSide; j++)
            {
                board.get(i).add(new Tile(tileSize));
            }
        }
    }
    
    public int getTileProperties (TileProperties propertyToGet, int posX, int posY)
    {
        switch (propertyToGet)
        {
            case SIZE:
                board.get(posX).get(posY).getTileSize();
                break;
            case ROLE:
                board.get(posX).get(posY).getPowerRole();
                break;
            default:
                break;
        }
        return 0;
    }
    
    public int getTileN ()
    {
        return tileN;
    }
    
    public void setTileN (int tileN)
    {
        this.tileN = tileN;
    }
}