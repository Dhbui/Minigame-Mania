import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*********************************************
* The MainPanel is the Main Menu Screen. It
* has a title card and a Button Panel. 
*********************************************/
public class MainPanel extends JPanel
{
   private ButtonPanel buttons;
   private HeaderPanel header;
   private JLabel title;
   private JFrame myFrame;
   /*********************************************
   * Constructs a MainPanel in the specified
   * JFrame.
   * @param f     JFrame used 
   *********************************************/
   public MainPanel(JFrame f)
   {
      myFrame = f;
      
      setLayout(new BorderLayout());
      
      title = new JLabel(new ImageIcon("TitleCard.png"));
      add(title, BorderLayout.CENTER);
      
      buttons = new ButtonPanel(f);
      add(buttons, BorderLayout.SOUTH);
   }
}