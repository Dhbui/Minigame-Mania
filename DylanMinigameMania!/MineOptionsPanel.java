import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import java.io.*;
import java.util.*;
/*********************************************
* The MineOptionsPanel contains all of the
* options for the Minesweeper game. It has
* options for the size of the field, and the
* probability of a square being a bomb.
*********************************************/
public class MineOptionsPanel extends JPanel
{
   private JSlider size, prob;
   private JLabel sz, pr;
   private int si, pb;
   private JButton main, save, options, mine;
   private JFrame myFrame;
   /*********************************************
   * Constructs a MineOptionsPanel in the 
   * specified JFrame. MineOptionsPanel has two
   * JSliders and four JButtons.
   * @param f     JFrame used 
   *********************************************/
   public MineOptionsPanel(JFrame f)
   {
      try{
         Scanner infile = new Scanner( new File("MinesweeperOptions.txt") );
      si = infile.nextInt();
      pb = infile.nextInt();
      infile.close();
      }
      catch(FileNotFoundException e)
      {
         System.out.println("ERROR IN MINEOPTIONSPANEL");
         e.printStackTrace();
      }
      myFrame = f;
      
      setLayout(new GridLayout(8,1));
      
      options = new JButton("Back");
      options.addActionListener(new OptionListener());
      add(options);
      
      sz = new JLabel("Grid Size");
      add(sz);
      
      size = new JSlider(10, 25);
      size.setPaintLabels(true);
      size.setPaintTicks(true);
      size.setMajorTickSpacing(1);
      size.addChangeListener(new SizeListener());
      size.setValue(si);
      add(size);
      
      pr = new JLabel("Bomb Probability");
      add(pr);
      
      prob = new JSlider(4, 8);
      prob.setPaintLabels(true);
      prob.setPaintTicks(true);
      prob.setMajorTickSpacing(1);
      prob.addChangeListener(new ProbListener());
      prob.setValue(pb);
      add(prob);
      
      save = new JButton("Save");
  		save.addActionListener(new SaveListener());    
		add(save);
      
      main = new JButton("Back to Main Menu");
      main.addActionListener(new MainMenuListener());
      add(main);
      
      mine = new JButton("Play Minesweeper");
      mine.addActionListener(new MineListener());
      add(mine);
   }
   /*******************************************
   * The ActionListener attached to the
   * "Main Menu" Button. Takes you to the
   * Main Menu Screen.
   *******************************************/
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
   * The ActionListener attached to the
   * "Options" Button. Takes you to the
   * generic Options Screen
   *******************************************/
   public class OptionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         myFrame.getContentPane().removeAll();
         myFrame.add(new OptionPanel(myFrame));
         myFrame.setSize(200,200);
         myFrame.pack();
      }
   }
   /*******************************************
   * The ActionListener attached to the
   * "Minesweeper" Button. Takes you to the
   * Minesweeper Game.
   *******************************************/
   public class MineListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         myFrame.getContentPane().removeAll();
         myFrame.add(new Minesweeper(myFrame));
         myFrame.setSize(200,200);
         myFrame.pack();
      }
   }
   /*******************************************
   * The ChangeListener attached to the
   * size JSlider. Adjusts the size of the
   * field anywhere from 10 by 10 to 25 by 25.
   *******************************************/
   public class SizeListener implements ChangeListener
   {
      public void stateChanged(ChangeEvent e)
      {
         JSlider source = (JSlider)e.getSource();
         if(!source.getValueIsAdjusting())
         {
            si = (int)source.getValue();
         }
      }
   }
   /*******************************************
   * The ChangeListener attached to the
   * probability JSlider. Adjusts the
   * probability that a specific square will
   * be a bomb.
   *******************************************/
   public class ProbListener implements ChangeListener
   {
      public void stateChanged(ChangeEvent e)
      {
         JSlider source = (JSlider)e.getSource();
         if(!source.getValueIsAdjusting())
         {
            pb = (int)source.getValue();
         }
      }

   }
   /*******************************************
   * The ActionListener attached to the
   * "Save" Button. Saves the current options
   * to a text file to be stored.
   *******************************************/
	public class SaveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
         try
         {
			save();
         }
         catch(Exception a)
         {
            System.out.println("ERROR IN MINEOPTIONSPANEL");
            a.printStackTrace();
         }
		}
	}
   /*******************************************
   * The save method. Saves the options from
   * the JSliders into a text file.
   * @throws Exception     if file is not found
   *******************************************/
	public void save() throws Exception
	{
		System.setOut(new PrintStream(new FileOutputStream("MinesweeperOptions.txt")));
		System.out.println("" + si);
      System.out.println("" + pb);
	}
}