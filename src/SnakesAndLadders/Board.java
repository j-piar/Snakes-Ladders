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
        
        board = new ArrayList<>();
        createTileArrL();
        
        board.get(0).get(0).setPowerRole(TileRole.START);
        board.get(sideA-1).get(sideA-1).setPowerRole(TileRole.END);
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
    
    public int getTileSize(Point xyCoordinates)
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

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addComponentsToPane(Container contentPane) {

        GridLayout gridLayout = new GridLayout(8, 8);
        boardPanel.setLayout(gridLayout);
        createTiles();
        //add all panels to frame
        contentPane.add(boardPanel, BorderLayout.CENTER);
    }
    
    private void createTiles()
    {
        int row;
        int count = 0;
        for (row = 0; row < sideA; row++) {
            for (int col = 0; col < sideA; col++) {
                panels[count] = new Tile(tileSize);
                panels[count].setBounds(new Rectangle(new Dimension(tileSize, tileSize)));
                if (count%2 == 0)
                panels[count].setBackground(Color.red);
                else
                    panels[count].setBackground(Color.BLACK);
                count++;
            }
        }
        
        for (int i = 0; i < numberOfTiles; i++)
        {
            //labels[i] = new Tile(tileSize);
            boardPanel.add(panels[i]);
        }
    }
    private void createTileArrL()
    {
        for (int i = 0; i < sideA ; i++)
        {
            System.out.println("");
            board.add(new ArrayList<>());
            for (int j = 0; j < sideA; j++)
            {
                try
                {
                    board.get(i).add(new Tile(tileSize, TileRole.randRole()));
                    Tile tempTile = board.get(i).get(j);
                    System.out.printf(tempTile.getPowerRole().toString());
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex)
                {
                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}