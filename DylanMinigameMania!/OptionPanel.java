import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
/*******************************************
* The OptionPanel is the generic options
* screen. It allows you to access all of
* the options for each game from one place.
*******************************************/
public class OptionPanel extends JPanel
{
   private JButton sops, meops, mineops, mm;
   private JSlider volume;
   private JLabel select;
   private JFrame myFrame;
   /*******************************************
   * Constructs the OptionPanel withing the
   * specified JFrame.
   * @param f     JFrame used
   *******************************************/
   public OptionPanel(JFrame f)
   {
      myFrame = f;
      setLayout(new GridLayout(6, 1));
      
      select = new JLabel("Select Game");
      add(select);
      
      sops = new JButton("Simon Options");
      sops.addActionListener(new SimonOptionsListener());
      add(sops);
      
      mineops = new JButton("Minesweeper Options");
		mineops.addActionListener(new MineOptionsListener());
      add(mineops);
      
      meops = new JButton("Maze Escape Options");
      add(meops);
      
      mm = new JButton("Back to Main Menu");
      mm.addActionListener(new MainMenuListener());
      add(mm);
   }
   /*********************************************
   * The ActionListener attached to the Main
   * Menu JButton. Takes you back to the Main
   * Menu.
   *********************************************/
   public class MainMenuListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         myFrame.getContentPane().removeAll();
         myFrame.add(new MainPanel(myFrame));
         myFrame.setSize(200,200);
         myFrame.pack();
      }
   }
   /*******************************************
   * The ActionListener attached to the "Simon"
   * Button. Takes you to the Simon Game.
   *******************************************/
   public class SimonOptionsListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         myFrame.getContentPane().removeAll();
         myFrame.add(new SimonOptionsPanel(myFrame));
         myFrame.setSize(200,200);
         myFrame.pack();
      }
   }
   /*******************************************
   * The ActionListener attached to the
   * "Minesweeper Options" Button. Takes you
   * to the Minesweeper Options Screen.
   *******************************************/ 
	public class MineOptionsListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			myFrame.getContentPane().removeAll();
         myFrame.setSize(1000, 300);
			myFrame.add(new MineOptionsPanel(myFrame));
			myFrame.pack();
		}
	}
}     