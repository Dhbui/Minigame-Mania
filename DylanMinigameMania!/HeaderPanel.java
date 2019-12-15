import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*********************************************
* The HeaderPanel is the JPanel used in all
* of the Games. It has JButtons that can take
* you to the Main Menu and the generic Options
* Screen.
*********************************************/
public class HeaderPanel extends JPanel
{
   private JFrame myFrame;
   private JButton options, main;
   /*********************************************
   * Constructs a HeaderPanel in a specified
   * JFrame. Has two JButtons, a Options and a 
   * Back to Main Menu JButton.
   * @param f     JFrame used
   *********************************************/
   public HeaderPanel(JFrame f)
   {
      myFrame = f;
      setLayout(new FlowLayout());
      
      options = new JButton("Options");
      options.addActionListener(new OptionListener());
      
      main = new JButton("Back to Main Menu");
      main.addActionListener(new MainMenuListener());
      
      add(main);
      add(options);
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
         myFrame.setSize(300, 300);
         myFrame.pack();
      }
   }
   /*********************************************
   * The ActionListener attached to the Options
   * JButton. Takes you to the generic Options
   * Screen.
   *********************************************/
   public class OptionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         myFrame.getContentPane().removeAll();
         myFrame.add(new OptionPanel(myFrame));
         myFrame.pack();
      }
   }
}