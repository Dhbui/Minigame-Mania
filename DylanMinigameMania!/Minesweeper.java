//Name______________________________ Date_____________
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.io.*;
import java.util.*;
/*******************************************
* The Minesweeper JPanel. Contains the 
* necessary code to run the game.
*******************************************/
public class Minesweeper extends JPanel
{
   private JButton[][] board;
   private int[][] matrix;
   private int[] timer;
   private int size, prob;
   private JLabel label, score;
   private JButton reset, mark, main, option;
   private JFrame myFrame;
   private boolean marking, won, first;
   private Timer t;
   /*******************************************
   * Constructs a Minesweeper game withing the
   * specified JFrame. Contains nested JPanels,
   * matrices, JButtons, JLabels, and booleans.
   * @param f     JFrame used
   *******************************************/
   public Minesweeper(JFrame f)
   {
      try{
         Scanner infile = new Scanner( new File("MinesweeperOptions.txt") );
         size = infile.nextInt();
         prob = infile.nextInt();
         infile.close();
      }
      catch(FileNotFoundException e)
      {
         System.out.println("ERROR IN MINEOPTIONSPANEL");
         e.printStackTrace();
      }
   
      marking = false;
      first = true;
      setLayout(new BorderLayout());
      myFrame = f;
      timer = new int[3];
      t = new Timer(1000, new ScoreListener());
   
      JPanel north = new JPanel();
      north.setLayout(new FlowLayout());
      add(north, BorderLayout.NORTH);
      
      JButton main = new JButton("Back to Main Menu");
      main.addActionListener(new MainMenuListener());
      north.add(main);
      
      option = new JButton("Minesweeper Options");
      option.addActionListener(new MineOptionsListener());
      north.add(option);
      
      label = new JLabel("Welcome to Minesweeper.");
      north.add(label);
      
      score = new JLabel("Time: 00:00");
      north.add(score);
      
      mark = new JButton("Switch to Mark Mode");
      mark.addActionListener(new MarkListener());
      north.add(mark);
   
      JPanel center = new JPanel();
      center.setLayout(new GridLayout(size,size));
      add(center, BorderLayout.CENTER);
   
      board = new JButton[size][size];
      matrix = new int[size][size];
      for(int r = 0; r < size; r++)
         for(int c = 0; c < size; c++)
         {
            board[r][c] = new JButton();
            board[r][c].setBackground(Color.blue);
            board[r][c].addActionListener( new Handler1(r, c) );
            center.add(board[r][c]);
         }
   
      reset = new JButton("Reset");
      reset.addActionListener( new Handler2() );
      reset.setEnabled(false);
      add(reset, BorderLayout.SOUTH);
   
      randomize();
   }
   /*******************************************
   * Randomizes the bomb placement using the
   * settings from the option text file.
   *******************************************/
   private void randomize()
   {
      for(int x = 0; x < matrix.length; x++)
      {
         for(int y = 0; y < matrix[0].length; y++)
         {
            int coin = (int)(Math.random() * (prob - 1));
            if(coin == 0)
            {
               matrix[x][y] = 1;    
            }
            else
            {
               matrix[x][y] = 0;
            }
         }
      } 
   }
   /*******************************************
   * Checks to see if the game is complete.
   * @return true    if game is won
   *******************************************/
   private boolean check()
   {
      int total = 0;
      for(int x = 0; x < matrix.length; x++)
      {
         for(int y = 0; y < matrix[0].length; y++)
         {
            if(matrix[x][y] == 0 && !(board[x][y].getText().equals("")))
            {
               total++;
            }
            else if(matrix[x][y] == 1 && board[x][y].getText().equals("|>"))
            {
               total++;
            }
         }
      }
      if(total == size * size)
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   /*******************************************
   * Ends the game.
   *******************************************/
   private void endgame()
   {
      for(int x = 0; x < board.length; x++)
      {
         for(int y = 0; y < matrix[0].length; y++)
         {
            board[x][y].setEnabled(false);
            if(matrix[x][y] == 1)
            {
               board[x][y].setText("X");
               board[x][y].setBackground(Color.RED);
            }
            else if(reveal(x, y) == 0)
            {
               board[x][y].setText("");
               board[x][y].setBackground(Color.GRAY);
            }
            else
            {
               board[x][y].setText("" + reveal(x, y));
               board[x][y].setBackground(Color.GRAY);
            }
         }
      }
      if(!check())
      {
         label.setText("Game over! Better luck next time!");
      }
      else
      {
         label.setText("Congratulations! You win!");
      }
      t.stop();
      reset.setEnabled(true);
   }
   /*******************************************
   * The ActionListener attached to the
   * "Mark" Button. Switches between the
   * "Marking" and "Revealing" modes.
   *******************************************/
   private class MarkListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(!marking)
         {
            marking = true;
            mark.setText("Switch to Reveal Mode");
         }
         else if(marking)
         {
            marking = false;
            mark.setText("Switch to Mark Mode");
         }
      }
   }
   /*******************************************
   * The ActionListener attached to the
   * JButtons. Controls the JButtons on the
   * field.
   *******************************************/
   private class Handler1 implements ActionListener
   {
      private int myRow, myCol;
      /*******************************************
      * Constructs an ActionListener specific to
      * each button.
      * @param r     row button is in
      * @param c     column button is in
      *******************************************/
      public Handler1(int r, int c)
      {
         myRow = r;
         myCol = c;
      }
      public void actionPerformed(ActionEvent e)
      {
         if(first)
         {
            t.start();
         }
         if(!marking)
         {
            if(matrix[myRow][myCol] == 1)
            {
               if(first)
               {
                  first = false;
                  do
                  {
                     randomize();
                  }
                  while(matrix[myRow][myCol] == 1);
               }
               else if(!first)
               {
                  endgame();
               }
            }
            else if(reveal(myRow, myCol) != 0)
            {
               first = false;
               board[myRow][myCol].setBackground(Color.GRAY);
               board[myRow][myCol].setText("" + reveal(myRow, myCol));
               board[myRow][myCol].setEnabled(false);
            }
            else
            {
               first = false;
               board[myRow][myCol].setBackground(Color.GRAY);
               board[myRow][myCol].setEnabled(false);
               board[myRow][myCol].setText(" ");
            }
            if(board[myRow][myCol].getText().equals(" "))
            {
               if(myRow == 0 && (myCol != 0 && myCol != matrix.length-1))
               {
                  board[myRow][myCol-1].doClick();
                  board[myRow][myCol+1].doClick();
                  board[myRow+1][myCol-1].doClick();
                  board[myRow+1][myCol].doClick();
                  board[myRow+1][myCol+1].doClick();
               }
               else if(myRow == matrix[1].length-1 && (myCol != 0 && myCol != matrix.length-1))
               {
                  board[myRow][myCol-1].doClick();
                  board[myRow][myCol+1].doClick();
                  board[myRow-1][myCol-1].doClick();
                  board[myRow-1][myCol].doClick();
                  board[myRow-1][myCol+1].doClick();
               }
               else if(myRow == 0 && myCol == 0)
               {
                  board[myRow][myCol+1].doClick();
                  board[myRow+1][myCol].doClick();
                  board[myRow+1][myCol+1].doClick();
               }
               else if(myRow == matrix[0].length-1 && myCol == 0)
               {
                  board[myRow][myCol+1].doClick();
                  board[myRow-1][myCol].doClick();
                  board[myRow-1][myCol+1].doClick();
               }
               else if(myRow == 0 && myCol == matrix.length-1)
               {
                  board[myRow][myCol-1].doClick();
                  board[myRow+1][myCol-1].doClick();
                  board[myRow+1][myCol].doClick();
               }
               else if(myRow == matrix[0].length-1 && myCol == matrix.length-1)
               {
                  board[myRow][myCol-1].doClick();
                  board[myRow-1][myCol].doClick();
                  board[myRow-1][myCol-1].doClick();
               }
               else if((myRow != 0 && myRow != matrix[0].length-1) && myCol == 0)
               {
                  board[myRow][myCol+1].doClick();
                  board[myRow-1][myCol].doClick();
                  board[myRow-1][myCol+1].doClick();
                  board[myRow+1][myCol].doClick();
                  board[myRow+1][myCol+1].doClick();
               }
               else if((myRow != 0 && myRow != matrix[0].length-1) && myCol == matrix.length-1)
               {
                  board[myRow][myCol-1].doClick();
                  board[myRow-1][myCol].doClick();
                  board[myRow-1][myCol-1].doClick();
                  board[myRow+1][myCol].doClick();
                  board[myRow+1][myCol-1].doClick();
               }
               else
               {
                  board[myRow][myCol-1].doClick();
                  board[myRow][myCol+1].doClick();
                  board[myRow-1][myCol-1].doClick();
                  board[myRow-1][myCol].doClick();
                  board[myRow-1][myCol+1].doClick();
                  board[myRow+1][myCol-1].doClick();
                  board[myRow+1][myCol].doClick();
                  board[myRow+1][myCol+1].doClick();
               }
            }
         }
         else if(marking)
         {
            if(board[myRow][myCol].getText().equals(""))
            {   
               board[myRow][myCol].setText("|>");
            }
            else if(board[myRow][myCol].getText().equals("|>"))
            {
               board[myRow][myCol].setText("?");
            }
            else if(board[myRow][myCol].getText().equals("?"))
            {
               board[myRow][myCol].setText("");
            }
         }
         if(check())
         {
            endgame();
         }
      }
   }
   /*******************************************
   * The ActionListener attached to the
   * Reset Button. Resets the game back to
   * starting condition.
   *******************************************/
   private class Handler2 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         for(int x = 0; x < board.length; x++)
         {
            for(int y = 0; y < board[0].length; y++)
            {
               board[x][y].setBackground(Color.BLUE);
               board[x][y].setEnabled(true);
               board[x][y].setText("");
               matrix[x][y] = 0;
            }
         }
         timer[0] = 0;
         timer[1] = 0;
         timer[2] = 0;
         score.setText("Time: 00:00");
         first = true;
         randomize();
         label.setText("Good Luck!");
         reset.setEnabled(false);
      }
   }
   /*******************************************
   * Returns the amount of bombs surrounding
   * a given place on the field.
   * @param r     row button is in
   * @param c     column button is in
   * @return      the number of bombs around a square
   *******************************************/
   public int reveal(int r, int c)
   {
      int area = 0;
      int holder = 0;
      int row = r;
      if(r == 0 && (c != 0 && c != matrix.length-1))
      {
         for(int x = 0; x < 2; x++)
         {
            for(int i = -1; i < 2; i++)
            {
               if(matrix[row][c+i] == 1)
               {
                  area++;
               }
            }
            row++;
         }
      }
      else if(r == matrix[1].length-1 && (c != 0 && c != matrix.length-1))
      {
         for(int x = 0; x < 2; x++)
         {
            for(int i = -1; i < 2; i++)
            {
               if(matrix[row-1][c+i] == 1)
               {
                  area++;
               }
            }
            row++;
         }
      }
      else if(r == 0 && c == 0)
      {
         for(int x = 0; x < 2; x++)
         {
            for(int i = 0; i < 2; i++)
            {
               if(matrix[row][c+i] == 1)
               {
                  area++;
               }
            }
            row++;
         }
      }
      else if(r == matrix[0].length-1 && c == 0)
      {
         for(int x = 0; x < 2; x++)
         {
            for(int i = 0; i < 2; i++)
            {
               if(matrix[row-1][c+i] == 1)
               {
                  area++;
               }
            }
            row++;
         }
      }
      else if(r == 0 && c == matrix.length-1)
      {
         for(int x = 0; x < 2; x++)
         {
            for(int i = -1; i < 1; i++)
            {
               if(matrix[row][c+i] == 1)
               {
                  area++;
               }
            }
            row++;
         }
      }
      else if(r == matrix[0].length-1 && c == matrix.length-1)
      {
         for(int x = 0; x < 2; x++)
         {
            for(int i = -1; i < 1; i++)
            {
               if(matrix[row-1][c+i] == 1)
               {
                  area++;
               }
            }
            row++;
         }
      }
      else if((r != 0 && r != matrix[0].length-1) && c == 0)
      {
         for(int x = 0; x < 3; x++)
         {
            for(int i = 0; i < 2; i++)
            {
               if(matrix[row-1][c+i] == 1)
               {
                  area++;
               }
            }
            row++;
         }
      }
      else if((r != 0 && r != matrix[0].length-1) && c == matrix.length-1)
      {
         for(int x = 0; x < 3; x++)
         {
            for(int i = -1; i < 1; i++)
            {
               if(matrix[row-1][c+i] == 1)
               {
                  area++;
               }
            }
            row++;
         }
      }
      else
      {
         for(int x = 0; x < 3; x++)
         {
            for(int i = -1; i < 2; i++)
            {
               if(matrix[row-1][c+i] == 1)
               {
                  area++;
               }
            }
            row++;
         }
      }
      return area;
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
   * Timer. Updates the Timer based on seconds
   * since your first button press.
   *******************************************/
   public class ScoreListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         timer[2] = timer[2] + 1;
         if(timer[2] == 10)
         {
            timer[2] = 0;
            timer[1] = timer[1] + 1;
         }
         if(timer[1] == 6)
         {
            timer[1] = 0;
            timer[0] = timer[0] + 1;
         }
         score.setText("Time: " + timer[0] + ":" + timer[1] + timer[2]);
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
         myFrame.add(new MineOptionsPanel(myFrame));
         myFrame.setSize(200, 200);
         myFrame.pack();
      }
   }   
}