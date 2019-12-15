import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
/*******************************************
* The GameSelectPanel is the panel that is
* used to show the games to the user. It
* has pictures and JButtons leading to each
* game.
*******************************************/
public class GameSelectPanel extends JPanel
{
   private HeaderPanel h;
   private JButton simon, minesweeper, mazeescape, main;
   private JLabel b2, b3, sim, mine, wip;
   private JFrame myFrame;
   /*******************************************
   * Constructs a JPanel with a BorderLayout
   * inside the specified JFrame. There are
   * various nested JPanels within this panel
   * as well. The HeaderPanel is north on the
   * BorderLayout. See HeaderPanel for more 
   * details. The center contains the three 
   * pictures and games.
   * @param f     JFrame used
   *******************************************/
   public GameSelectPanel(JFrame f)
   {
      myFrame = f;
      
      setLayout(new BorderLayout());
      
      h = new HeaderPanel(f);
      add(h, BorderLayout.NORTH);
      
      JPanel center = new JPanel();
      center.setLayout(new GridLayout(2, 3));
      
      sim = new JLabel(new ImageIcon("Simon.jpg"));
      center.add(sim);
      
      mine = new JLabel(new ImageIcon("Minesweeper.png"));
      center.add(mine);
      
      wip = new JLabel(new ImageIcon("wip.jpg"));
      center.add(wip);
      
      simon = new JButton("Play Simon");
      simon.addActionListener(new SimonListener());
      center.add(simon);
      
      minesweeper = new JButton("Play Minesweeper");
      minesweeper.addActionListener(new MinesweeperListener());
      center.add(minesweeper);
      
      mazeescape = new JButton("Play Maze Escape");
      center.add(mazeescape);
      
      add(center);
   }
   /*******************************************
   * The ActionListener attached to the "Simon"
   * Button. Takes you to the Simon Game.
   *******************************************/
   public class SimonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         myFrame.getContentPane().removeAll();
         myFrame.add(new Simon(myFrame));
         myFrame.setSize(200,200);
         myFrame.pack();
      }
   }
   /*******************************************
   * The ActionListener attached to the
   * "Minesweeper" Button. Takes you to the
   * Minesweeper Game.
   *******************************************/
   public class MinesweeperListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         myFrame.getContentPane().removeAll();
         myFrame.add(new Minesweeper(myFrame));
         myFrame.setSize(200,200);
         myFrame.pack();
      }
   }

}