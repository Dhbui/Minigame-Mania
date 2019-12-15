   //Torbert, e-mail: mr@torbert.com, website: www.mr.torbert.com
	//version 4.4.2003

   import javax.swing.JFrame;
/************************************************************
* The Driver Class for the program. This runs everything.
************************************************************/
   public class GameDriver
   {
      public static void main(String[] args)
      {
         JFrame frame = new JFrame("Minigame Mania!");
         frame.setSize(400, 400);
         frame.setLocation(200, 100);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(new MainPanel(frame));
         frame.setVisible(true);
      }
   }