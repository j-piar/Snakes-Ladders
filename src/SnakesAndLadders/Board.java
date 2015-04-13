package SnakesAndLadders;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
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
    
    private final JPanel boardPanel;
    private final JPanel[] panels;
    private JLabel[] labels;
    
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
     * @param xyCoordinates
     * @return
     */
//    public TileRole getTileRole(Point xyCoordinates)
//    {
//        return board.get(xyCoordinates.x).get(xyCoordinates.y).getPower().;
//    }
//    
//    public Point getTileSize(Point xyCoordinates)
//    {
//        return board.get(xyCoordinates.x).get(xyCoordinates.y).getTileSize();
//    }
    
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

    private void addComponentsToPane(Container contentPane) 
    {
        gridLayout = new GridLayout(sideA, sideA);
        boardPanel.setLayout(gridLayout);
        createTileArrL();
        
        //add all panels to frame
        contentPane.add(boardPanel, BorderLayout.CENTER);
    }
    private void createTileArrL()
    {
        int count = 0;
        int colCount;
        
        ArrayList<TileRole> blackListRoles = new ArrayList<>();
        // Create rows
        for (int i = 0; i < sideA ; i++)
        {
            colCount = 0;
            System.out.println("");
            board.add(new ArrayList<>());
            // Create columns
            for (int j = 0; j < sideA; j++)
            {
                blackListRoles.clear();
                if (colCount%(sideA/(sideA/1.5)) == 0)
                {
                    blackListRoles.add(TileRole.SNAKE);
                    blackListRoles.add(TileRole.LADDER);
                }
                else if (count <= sideA)
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
                    Tile curTile = board.get(i).get(j);
                    
                    // Ensure that no consecutive powerTiles exist
                    Tile prevTile = board.get(( (j == 0 && i > 0) ? i - 1 : i)).get((j == 0 ? 0 : j - 1));
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
                                upperBound = sideA;
                                bound = upperBound - lowerBound;
                            }
                            tmpY = new Random().nextInt(bound) + lowerBound ;
                            if (tmpY == curTile.getPower().getStartPosition().y)
                                tmpX = new Random().nextInt(sideA - curTile.getPower().getStartPosition().x) +
                                    curTile.getPower().getStartPosition().x;
                            else
                                tmpX = new Random().nextInt(sideA);
                            curTile.getPower().setEndPosition(new Point (tmpX, tmpY));
                        }while (powerEndPoints.contains(curTile.getPower().getEndPosition()));
                        powerEndPoints.add(curTile.getPower().getEndPosition());
                    }
                   // panels[count].setBounds(new Rectangle(new Dimension(tileSize, tileSize)));
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex)
                {
                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                }
                colCount++;
                count++;
            }
        }
        board.get(0).get(0).setPower(TileRole.START);
        board.get(sideA-1).get(sideA-1).setPower(TileRole.END);
        board.get(sideA - 1).get(sideA - 1).setBackground(Color.green);
        
        for (int i = 0; i < numberOfTiles; i++)
        {
            boardPanel.add(panels[i]);
        }
    }
    
    @Override
    public void paint(Graphics g)
    {
        Point tmpStart, tmpEnd;
        ArrayList<Polygon> rolePolies = new ArrayList<>();
        super.paint(g);
        for (ArrayList<Tile> board1 : board)
        {
            for (Tile tile : board1)
            {
                if (tile.getPower().IsDirectional())
                {
                    tmpStart = new Point (tile.getX() + (getWidth()/sideA/2), tile.getY() + (getWidth()/sideA/2));
                    tmpEnd = board.get(tile.getPower().getEndPosition().y).get(tile.getPower().getEndPosition().x).getLocation();
                    if (tile.getPower().getPowerName().equals(TileRole.LADDER))
                    {
//                        g.drawPolygon(new Polygon(new int[]{tmpStart.x, tmpEnd.x + (getWidth()/sideA/2)},
//                            new int[]{tmpStart.y, tmpEnd.y + (getWidth()/sideA/2)}, 2));
//                        g.fillOval(tmpEnd.x + (getWidth()/sideA/2), tmpEnd.y + (getWidth()/sideA/2), 20, 20);
                        g.drawLine(tmpStart.x - 2, tmpStart.y, tmpEnd.x - 2 + (getWidth()/sideA/2), tmpEnd.y + (getWidth()/sideA/2));
                        g.drawLine(tmpStart.x + 2, tmpStart.y, tmpEnd.x + 2 + (getWidth()/sideA/2), tmpEnd.y + (getWidth()/sideA/2));
                    }
                    else if (tile.getPower().getPowerName().equals(TileRole.SNAKE))
                    {
                         g.drawPolygon(new Polygon(new int[]{tmpStart.x, tmpEnd.x + (getWidth()/sideA/2)},
                            new int[]{tmpStart.y, tmpEnd.y + (getWidth()/sideA/2)}, 2));
                        g.fillOval(tmpEnd.x + (getWidth()/sideA/2), tmpEnd.y + (getWidth()/sideA/2), 20, 20);
                    }
                }
            }
        }
//        rolePolies.stream().forEach((rolePoly) ->
//        {
//            g.drawPolygon(rolePoly);
//            int x1 = rolePoly.xpoints[rolePoly.xpoints.length -1];
//            int y1 = rolePoly.ypoints[rolePoly.ypoints.length -1];
//            g.fillOval(x1, y1, 20, 20);
//        });
    }
}