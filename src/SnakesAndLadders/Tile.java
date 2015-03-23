package SnakesAndLadders;

/**
 *test2
 * @author 
 */
public class Tile
{
    private int tileSize;
    private boolean snake;
    private boolean ladder;

    public boolean isLadder()
    {
        return ladder;
    }

    public void setLadder(boolean ladder)
    {
        this.ladder = ladder;
    }


    public boolean isSnake()
    {
        return snake;
    }

    public void setSnake(boolean snake)
    {
        this.snake = snake;
    }


    public int getTileSize()
    {
        return tileSize;
    }

    public void setTileSize(int tileSize)
    {
        this.tileSize = tileSize;
    }

    
    public Tile (int tileSize)
    {
        this.tileSize = tileSize;
    }
}
