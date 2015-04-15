package SnakesAndLadders;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
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
    private final ArrayList<ArrayList<Tile>> board;
    private int numberOfTiles = 100;
    private final int sideA;
    private GridLayout gridLayout;
    private final Point boardSize = new Point(800, 600);
    private boolean gameSet = false;
    private final JPanel boardPanel;
    private final JPanel[] panels;
    private JLabel[] labels;
    private int numberOfPlayers;
    private ArrayList<Player> listOPlayers;
    
    ArrayList<Point> powerEndPoints = new ArrayList<>();
     
    public Board(int numberOfTiles)
    {
        this.numberOfTiles = numberOfTiles;
        boardPanel = new JPanel();
        boardPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        panels = new Tile[numberOfTiles];
        //labels = new Tile[numberOfTiles];
        sideA = calcSideA(numberOfTiles);
        System.out.printf("%dx%d\n", sideA, sideA);
        
        board = new ArrayList<>(numberOfTiles);
        startInit();
        createAndShowGui();
        System.out.println("\n");
    }
    
    private int calcSideA(int n)
    {
        numberOfTiles = n;
        if (getNumberOfTiles() == Math.pow((int)Math.sqrt(getNumberOfTiles()), 2))
            return (int)Math.sqrt(getNumberOfTiles());
        else
            return calcSideA(getNumberOfTiles() - 1);
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        addComponentsToPane(getContentPane());

        setSize(boardSize.x, boardSize.y);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }

    private void addComponentsToPane(Container contentPane) 
    {
        gridLayout = new GridLayout(getSideA(), getSideA());
        boardPanel.setLayout(gridLayout);
        createTileArrL();
        
        //add all panels to frame
        contentPane.add(boardPanel, BorderLayout.CENTER);
    }
    private void createTileArrL()
    {
        powerEndPoints.add(new Point (getSideA(), getSideA()));
        int count = 0;
        int colCount;
        
        ArrayList<TileRole> blackListRoles = new ArrayList<>();
        // Create rows
        for (int i = 0; i < getSideA() ; i++)
        {
            colCount = 0;
            System.out.println("");
            getBoard().add(new ArrayList<>());
            // Create columns
            for (int j = 0; j < getSideA(); j++)
            {
                blackListRoles.clear();
                if (colCount%(getSideA()/(getSideA()/1.5)) == 0)
                {
                    blackListRoles.add(TileRole.SNAKE);
                    blackListRoles.add(TileRole.LADDER);
                }
                else if (count <= getSideA())
                    blackListRoles.add(TileRole.SNAKE);
                else if (count >= getNumberOfTiles() - getSideA())
                    blackListRoles.add(TileRole.LADDER);
                
                blackListRoles.add(TileRole.START);
                blackListRoles.add(TileRole.END);
                try
                {
                    TileRole tmpRole = TileRole.randRole(blackListRoles);
                    getBoard().get(i).add(new Tile(new Point(getWidth()/getSideA(), getHeight()/getSideA()), tmpRole));
                    ((Tile) getBoard().get(i).get(j)).setTileN(count);
                    System.out.println("tile " + getBoard().get(i).get(j).getTileN() + " created");
                    panels[count] = getBoard().get(i).get(j);
                    Tile curTile = getBoard().get(i).get(j);
                    
                    // Ensure that no consecutive powerTiles exist
                    Tile prevTile = getBoard().get(( (j == 0 && i > 0) ? i - 1 : i)).get((j == 0 ? 0 : j - 1));
                    if (prevTile.getPower().getPowerName() == tmpRole && prevTile.getPower().IsDirectional())
                        prevTile.setPower(TileRole.EMPTY);
                    
                    curTile.getPower().setStartPosition(new Point(j,i));
                    // Generate end-points of directional tiles
                    if (curTile.getPower().IsDirectional())
                    {
                        powerEndPoints.add(curTile.getPower().getStartPosition());
                        int lowerBound = 0, upperBound = 0, bound = 0;
                        int tmpX, tmpY;
                        do
                        {
                            if (curTile.getPower().getPowerName().equals(TileRole.SNAKE))
                            {
                                lowerBound = 0;
                                upperBound = curTile.getPower().getStartPosition().y;
                                bound = upperBound - lowerBound;
                            }
                            else if (curTile.getPower().getPowerName().equals(TileRole.LADDER))
                            {
                                lowerBound = curTile.getPower().getStartPosition().y;
                                upperBound = getSideA();
                                bound = upperBound - lowerBound;
                            }
                            tmpY = new Random().nextInt(bound) + lowerBound ;
                            if (tmpY == curTile.getPower().getStartPosition().y)
                                tmpX = new Random().nextInt(getSideA() - curTile.getPower().getStartPosition().x) +
                                    curTile.getPower().getStartPosition().x;
                            else
                                tmpX = new Random().nextInt(getSideA());
                            curTile.getPower().setEndPosition(new Point (tmpX, tmpY));                               
                        }while (powerEndPoints.contains(curTile.getPower().getEndPosition()));
                        powerEndPoints.add(curTile.getPower().getEndPosition());                        
                    }
                   //panels[count].setBounds(new Rectangle(new Dimension(getWidth()/sideA, getHeight()/sideA)));
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex)
                {
                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                }
                colCount++;
                count++;
            }
        }
        getBoard().get(0).get(0).setPower(TileRole.START);
        getBoard().get(getSideA()-1).get(getSideA()-1).setPower(TileRole.END);
        getBoard().get(getSideA() - 1).get(getSideA() - 1).setBackground(Color.green);
        
        Collections.reverse(Arrays.asList(panels));
        for (int i = 0; i < getNumberOfTiles(); i++)
        {           
            boardPanel.add(panels[i]);
        }
    } 
    
    private void startInit()
    {
        InitSettings settings = new InitSettings();
        settings.choosePlayers();
        listOPlayers = settings.getPlayersList();
    }
    
    @Override
    public void paint(Graphics g)
    {
        Point tmpStart, tmpEnd;
        ArrayList<Polygon> rolePolies = new ArrayList<>();
        super.paint(g);
        int pCount = 0;
        
        for (Player p  : getListOPlayers())
        {
            Tile pTile = getBoard().get(p.getPlayerPosition().x).get(p.getPlayerPosition().y);
            g.setColor(p.getPlayerColour());
            g.fill3DRect(pTile.getX()+(pTile.getWidth()/2) - pCount,
                    pTile.getY()+(pTile.getHeight()/2)+ pCount,
                    20, 20, false);
            pCount += (getWidth()/getSideA())/8;
        }
        
        for (ArrayList<Tile> board1 : getBoard())
        {
            for (Tile tile1 : board1)
            {
                if (tile1.getPower().IsDirectional())
                {
                    tmpStart = new Point (tile1.getX() + (getWidth()/getSideA()/2), tile1.getY() + (getWidth()/getSideA()/2));
                    tmpEnd = getBoard().get(tile1.getPower().getEndPosition().y).get(tile1.getPower().getEndPosition().x).getLocation();
                    if (tile1.getPower().getPowerName().equals(TileRole.LADDER))
                    {
                    }
                    else if (tile1.getPower().getPowerName().equals(TileRole.SNAKE))
                    {
                    }
                }
            }
        }
    }

    public ArrayList<Player> getListOPlayers()
    {
        return listOPlayers;
    }

    public int getSideA()
    {
        return sideA;
    }

    public int getNumberOfTiles()
    {
        return numberOfTiles;
    }
    
    public ArrayList<ArrayList<Tile>> getBoard()
    {
        return board;
    }
}