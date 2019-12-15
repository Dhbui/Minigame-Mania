import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
/**********************************************
* The ButtonPanel is the panel of buttons that 
* can be seen on the Main Menu Screen. It has
* three buttons, a "Select Game", "Options", 
* and "View Highscores" button.
**********************************************/
public class ButtonPanel extends JPanel
{
   private JButton select;
   private JButton options;
   private JButton highscores;
   private JButton play;
   private JFrame myFrame;
   /*****************************************
   * Constructs a panel with three different
   * buttons in a frame specified by f.
   * @param f     JFrame used
   *****************************************/
   public ButtonPanel(JFrame f)
   {
         myFrame = f;
         setLayout(new GridLayout(3, 1));
         
         select = new JButton("Select Game");
         select.addActionListener(new GameSelectListener());
         
         options = new JButton("Options");
         options.addActionListener(new OptionListener());
         
         highscores = new JButton("View Highscores");
         
         add(select);
         add(options);
         add(highscores);
   }
   /******************************************
   * ActionListener attached to the "Options"
   * JButton. Takes you to the Options Screen.
   ******************************************/
   public class OptionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         myFrame.getContentPane().removeAll();
         myFrame.add(new OptionPanel(myFrame));
         myFrame.pack();
      }
   }
   /**********************************************
   * ActionListener attached to the "Game Select"
   * JButton. Takes you to the Game Select Screen.
   **********************************************/
   public class GameSelectListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         myFrame.getContentPane().removeAll();
         myFrame.add(new GameSelectPanel(myFrame));
         myFrame.setLocation(0, 0);
         myFrame.setSize(1200, 500);
         myFrame.pack();
      }
   }
}