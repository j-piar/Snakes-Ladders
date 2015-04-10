package SnakesAndLadders;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author Juraj Piar <juraj2.piar@live.uwe.ac.uk>
 */
public class Board extends JFrame
{
    private int tileN;
    private Tile tile;
    private ArrayList<ArrayList<Tile>> board;
    private int numberOfTiles = 100;
    private int sideA;
    private int tileSize;
    private GridLayout gridLayout;
    private Point boardSize = new Point(800, 600);
    
    private JPanel boardPanel;
    private JPanel[] panels;
    private JLabel[] labels;
     
    public Board(int tileSize, int numberOfTiles)
    {
        this.tileSize = tileSize;
        this.numberOfTiles = numberOfTiles;
        boardPanel = new JPanel();
        boardPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        panels = new Tile[numberOfTiles];
        //labels = new Tile[numberOfTiles];
        sideA = calcSideA(numberOfTiles);
        System.out.printf("%dx%d\n", sideA, sideA);
        
        board = new ArrayList<>(numberOfTiles);
        //createTileArrL();
        createAndShowGui();
        System.out.println("\n");
    }
    
    private int calcSideA(int n)
    {
        numberOfTiles = n;
        if (numberOfTiles == Math.pow((int)Math.sqrt(numberOfTiles), 2))
            return (int)Math.sqrt(numberOfTiles);
        else
            return calcSideA(numberOfTiles - 1);
    }
    
    /**
     *
     * @return
     */
    public int[][] getBoardGrid()
    {
//        for (ArrayList<Tile> board1 : board)
//        {
//            for (Tile tile : board1)
//            {
//                System.out.println(tile.toString());
//            }
//        }
        return (int[][]) this.board.toArray();
    }
    
    /**
     *
     * @param xyCoordinates
     * @return
     */
    public TileRole getTileRole(Point xyCoordinates)
    {
        return board.get(xyCoordinates.x).get(xyCoordinates.y).getPowerRole();
    }
    
    public Point getTileSize(Point xyCoordinates)
    {
        return board.get(xyCoordinates.x).get(xyCoordinates.y).getTileSize();
    }
    
    public int getTileN ()
    {
        return tileN;
    }
    
    public void setTileN (int tileN)
    {
        this.tileN = tileN;
    }

    private void createAndShowGui()
    {
        setTitle("Snake&Ladders");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addComponentsToPane(getContentPane());

        setSize(boardSize.x, boardSize.y);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addComponentsToPane(Container contentPane) {

        gridLayout = new GridLayout(sideA, sideA);
        boardPanel.setLayout(gridLayout);
        createTileArrL();
        //add all panels to frame
        contentPane.add(boardPanel, BorderLayout.CENTER);
    }
    private void createTileArrL()
    {
        int count = 0;
        int snakeCount = 0;
        int ladderCount = 0;
        ArrayList<TileRole> blackListRoles = new ArrayList<>();;
        for (int i = 0; i < sideA ; i++)
        {
            System.out.println("");
            board.add(new ArrayList<>());
            for (int j = 0; j < sideA; j++)
            {
                blackListRoles.clear();
                if (count <= sideA)
                    blackListRoles.add(TileRole.SNAKE);
                else if (count >= numberOfTiles - sideA)
                    blackListRoles.add(TileRole.LADDER);
                blackListRoles.add(TileRole.START);
                blackListRoles.add(TileRole.END);
                try
                {
                    TileRole tmpRole = TileRole.randRole(blackListRoles);
                    board.get(i).add(new Tile(new Point(boardSize.x/sideA, boardSize.y/sideA), tmpRole));
                    panels[count] = board.get(i).get(j);
                    board.get(0).get(0).setBackground(Color.red);
                    Tile tmpTile = board.get((j == 0 && i > 0 ? i - 1 : i)).get((j == 0 ? 0 : j - 1));
                    if (tmpTile.getPowerRole() == tmpRole && !tmpTile.getPowerRole().equals(TileRole.EMPTY))
                        tmpTile.setPowerRole(TileRole.EMPTY);
                    if (tmpTile.getPowerRole().equals(TileRole.SNAKE))
                        snakeCount++;
                    else if (tmpTile.getPowerRole().equals(TileRole.LADDER))
                        ladderCount++;
                    panels[count].setBounds(new Rectangle(new Dimension(tileSize, tileSize)));
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex)
                {
                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                }
                count++;
            }
        }
        System.out.println("Ladders: " + ladderCount + " Snakes: " + snakeCount);
        board.get(0).get(0).setPowerRole(TileRole.START);
        board.get(sideA-1).get(sideA-1).setPowerRole(TileRole.END);
        
        for (int i = 0; i < numberOfTiles; i++)
        {
            //labels[i] = new Tile(tileSize);
            boardPanel.add(panels[i]);
        }
    }
}