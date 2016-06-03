import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


@SuppressWarnings("serial")

public class GameMain extends JFrame 
{
	
   // Named-constants for the game board
	
   public static final int ROWS = 3;  // ROWS by COLS cells
   public static final int COLS = 3;
 
   // Named-constants of the various dimensions used for graphics drawing
   
   public static final int CELL_SIZE = 100; // cell width and height (square)
   public static final int CANVAS_WIDTH = CELL_SIZE * COLS;  // the drawing canvas
   public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
   public static final int GRID_WIDTH = 8;                   // Grid-line's width
   public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2; // Grid-line's half-width
   
   // Squares are displayed inside a cell, with padding from border
   public static final int CELL_PADDING = CELL_SIZE / 6;
   public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2; // width/height
   public static final int SYMBOL_STROKE_WIDTH = 8; // pen's stroke width
 
   // Use an enumeration (inner class) to represent the various states of the game
   
   public enum GameState {
      PLAYING, WON
   }
   private GameState currentState;  // the current game state
 
   // Use an enumeration (inner class) to represent the seeds/state of each cell
   
   public enum Seed 
   {
      BLACK, WHITE
   }
   private Seed currentPlayer;  // the current player
 
   private Seed[][] board   ; // Game board of ROWS-by-COLS cells
   private DrawCanvas canvas; // Drawing canvas (JPanel) for the game board
   private JLabel statusBar;  // Status Bar
 
   /** Constructor to setup the game and the GUI components */
   
   public GameMain() 
   {
      canvas = new DrawCanvas();  // Construct a drawing canvas (a JPanel)
      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
 
      // MouseEvent upon mouse-click
      
      canvas.addMouseListener(new MouseAdapter() 
      {
         @Override
         public void mouseClicked(MouseEvent e) 
         {  // mouse-clicked handler
            int mouseX = e.getX();
            int mouseY = e.getY();
            
            // Get the row and column clicked
            
            int rowSelected = mouseY / CELL_SIZE;
            int colSelected = mouseX / CELL_SIZE;
 
            if (currentState == GameState.PLAYING) 
            {
               if (rowSelected >= 0 && rowSelected < ROWS && colSelected >= 0
                     && colSelected < COLS && ( board[rowSelected][colSelected] == Seed.BLACK || board[rowSelected][colSelected] == Seed.WHITE)) 
               {
                  board[rowSelected][colSelected] = currentPlayer; // Make a move
                  updateGame(currentPlayer, rowSelected, colSelected); // update state
       
                  
               }
            } 
            else 
            {       
               initGame(); // restart the game
            }
            
            // Refresh the drawing canvas
            repaint();  // Call-back paintComponent().
         }
      });
 
      // Setup the status bar (JLabel) to display status message
      
      statusBar = new JLabel("  ");
      statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
      statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
 
      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());
      cp.add(canvas, BorderLayout.CENTER);
      cp.add(statusBar, BorderLayout.PAGE_END); // same as SOUTH
 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();  // pack all the components in this JFrame
      setTitle("Tic Tac Toe");
      setVisible(true);  // show this JFrame
 
      board = new Seed[ROWS][COLS]; // allocate array
      initGame(); // initialize the game board contents and game variables
   }
 
   /** Initialize the game-board contents and the status */
   public void initGame() {
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            board[row][col] = Seed.BLACK; // all cells black
         }
      }
      currentState = GameState.PLAYING; // ready to play
      
   }
 
   /** Update the currentState after the player has clicked
       (rowSelected, colSelected). */
   
   public void updateGame(Seed theSeed, int rowSelected, int colSelected) 
   {
      if (hasWon(theSeed)) 
      {  
    	  // check for win
         
      }
      // Otherwise, no change to current state (still GameState.PLAYING).
   }
 
  
 
   /** Return true if the player has won after clicking at
       (rowSelected, colSelected) */
   
   
   public boolean hasWon(Seed theSeed) 
   {
	   return true;
	   //todo!
   }
 
   /**
    *  Inner class DrawCanvas (extends JPanel) used for custom graphics drawing.
    */
   
   class DrawCanvas extends JPanel 
   {
      @Override
      public void paintComponent(Graphics g)
      {  // invoke via repaint()
         super.paintComponent(g);    // fill background
         setBackground(Color.WHITE); // set its background color
 
 
         // Draw the state of all the cells if they are not empty
         
         Graphics2D g2d = (Graphics2D)g;
         g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND,
               BasicStroke.JOIN_ROUND)); 
         
         for (int row = 0; row < ROWS; ++row) 
         {
            for (int col = 0; col < COLS; ++col) 
            {
               int x1 = col * CELL_SIZE ;
               int y1 = row * CELL_SIZE ;
               if (board[row][col] == Seed.BLACK) 
               {
                  g2d.setColor(Color.BLACK);
                  int x2 = (col + 1) * CELL_SIZE ;
                  int y2 = (row + 1) * CELL_SIZE ;
                  g2d.fillRect(x1, y1, x2, y2);
               
               } else if (board[row][col] == Seed.WHITE) 
               {
                  g2d.setColor(Color.WHITE);
                  g2d.fillRect(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
               }
            }
         }
 
         // Print status-bar message
         if (currentState == GameState.PLAYING) 
         {
            statusBar.setForeground(Color.GREEN);
            statusBar.setText("LOL");
         } 
         else if (currentState == GameState.WON) 
         {
            statusBar.setForeground(Color.RED);
            statusBar.setText("Click to play again.");
         }
      }
   }
 
   
   public static void main(String[] args) {
      
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new GameMain(); 
         }
      });
   }
}