import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
/*******************************************
* The Simon JPanel. Contains the necessary
* code to run the game.
*******************************************/ 
public class Simon extends JPanel
{
   private int[] output, input;
   private JButton red, yellow, green, blue, ops, main, views, play;
   private Color r, g, b, y;
   private int length, score, place;
   private JFrame myFrame;
   private JLabel b1;
   private Timer timer, timer2;
   /*******************************************
   * Constructs a Simon game within the
   * specified JFrame. Contains arrays,
   * JButtons, and JLabels.
   * @param f     JFrame used
   *******************************************/
   public Simon(JFrame f)
   {
      myFrame = f;
   
      setLayout(new GridLayout(4,2));
   
      length = 5;
      timer = new Timer(1000, new OutputListener());
      timer2 = new Timer(1000, new ColorListener());
      output = new int[length];
      randomize();
   
      r = Color.red;
      r = r.darker();
      b = Color.blue;
      b = b.darker();
      g = Color.green;
      g = g.darker();
      y = Color.yellow;
      y = y.darker();
   
      main = new JButton("Back to Main Menu");
      main.addActionListener(new MainMenuListener());
      add(main);
   
      ops = new JButton("Options");
      ops.addActionListener(new SimonOptionsListener());
      add(ops);
   
   //high = new HighScorePanel(myFrame);
      b1 = new JLabel();
      add(b1);
   	
      play = new JButton("Play");
      play.addActionListener(new PlayListener());
      add(play);
   
      green = new JButton("");
      green.setBackground(g);
      add(green);
   
      red = new JButton("");
      red.setBackground(r);
      add(red);
   
      blue = new JButton("");
      blue.setBackground(b);
      add(blue);
   
      yellow = new JButton("");
      yellow.setBackground(y);
      add(yellow);
   }
   /*******************************************
   * Constructs a Simon game within the
   * specified JFrame with a certain starting
   * length. Contains arrays, JButtons,
   * and JLabels.
   * @param f     JFrame used
   *******************************************/
   public Simon(JFrame f, int l)
   {
      myFrame = f;
   
      setLayout(new GridLayout(3,2));
   
      length = l;
      output = new int[length];
      randomize();
   
      main = new JButton("Back to Main Menu");
      main.addActionListener(new MainMenuListener());
      add(main);
   
      ops = new JButton("Options");
      ops.addActionListener(new SimonOptionsListener());
      add(ops);
      
      green = new JButton("");
      green.setBackground(Color.GREEN);
      add(green);
   
      red = new JButton("");
      red.setBackground(Color.RED);
      add(red);
   
      blue = new JButton("");
      blue.setBackground(Color.BLUE);
      add(blue);
   
      yellow = new JButton("");
      yellow.setBackground(Color.YELLOW);
      add(yellow);
   
   }
   /*******************************************
   * Randomized the light order.
   *******************************************/
   public void randomize()
   {
      for(int x = 0; x < output.length; x++)
      {
         output[x] = (int)(Math.random() * 3 + 1);         
      }  
   }
   /*******************************************
   * Causes all lights to go back to resting
   * state.
   *******************************************/
   public void darken()
   {
      red.setBackground(r);
      blue.setBackground(b);
      green.setBackground(g);
      yellow.setBackground(y);
      try {
            Thread.currentThread().sleep(150);
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         
         }
   }
   /*******************************************
   * Causes the lights to brighten based on the
   * randomized code.
   * @param x     Corresponds to a light
   *******************************************/
   public void output(int x)
   {
      if(output[x] == 1)
      {
         red.setBackground(r.brighter());
         try {
            Thread.currentThread().sleep(1250);
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         
         }
      }
      if(output[x] == 2)
      {
         green.setBackground(g.brighter());
         try {
            Thread.currentThread().sleep(1250);
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         
         }
      }
      if(output[x] == 3)
      {
         blue.setBackground(b.brighter());
         try {
            Thread.currentThread().sleep(1250);
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      if(output[x] == 4)
      {
         yellow.setBackground(y.brighter());
         try {
            Thread.currentThread().sleep(1250);
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      place++;
   }
   /*******************************************
   * The ActionListener attached to the
   * "Play" Button. Starts various timers
   * that are connected to other
   * ActionListeners.
   *******************************************/
   public class PlayListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         play.setEnabled(false);
         place = 0;
         timer2.start();
         try {
            Thread.currentThread().sleep(250);
         }
         catch (InterruptedException a) {
            a.printStackTrace();
         }
         timer.start();
      
      }
   }
   /*******************************************
   * The ActionListener attached to a timer. 
   * Darkens the lights in the correct order.
   *******************************************/
   public class OutputListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(place < output.length)
         {
            output(place);
            try {
            Thread.currentThread().sleep(250);
         }
         catch (InterruptedException a) {
            a.printStackTrace();
         
         }
            darken();
         }
         else
         {
            timer.stop();
            play.setEnabled(true);
            timer2.stop();
            place = 0;
         }
      }
   }
   /*******************************************
   * The ActionListener attached to the
   * second timer. Sets the background of the
   * Lisghts to resting color.
   *******************************************/ 
   public class ColorListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         /*red.setBackground(r);
         blue.setBackground(b);
         green.setBackground(g);
         yellow.setBackground(y);*/
      }
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
   * "Simon Options" Button. Takes you
   * to the Simon Options Screen.
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
}
