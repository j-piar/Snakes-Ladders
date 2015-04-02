package SnakesAndLadders;

import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author
 */
public class GameMain extends javax.swing.JFrame
{
    private JLabel jLabel1;
    private int[][] boardGrid;
    
    public static void main(String[] args) {
        Board board = new Board(5, 10);
        System.out.println("role: " + board.getTileRole(new Point(0,0)));
        
        SwingUtilities.invokeLater(() ->
        {
            GameMain inst = new GameMain();
            inst.setLocationRelativeTo(null);
            inst.setVisible(true);
        });
    }
    public GameMain()
    {
        super();
        initGUI();
    }
    
    private void initGUI()
    {
        try
        {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);
            {
                jLabel1 = new JLabel();
                getContentPane().add(jLabel1);
                jLabel1.setText("jLabel1");
                jLabel1.setIcon(null);
                jLabel1.setBounds(199, 0, 742, 484);
            }
            pack();
            this.setSize(963, 523);
        }catch (Exception e)
        {
        }
        
    }
}
