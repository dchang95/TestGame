package game;

import game.GameMain.CellState;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import game.GameMain;

//The GUI component that actually draws the game
class DrawCanvas extends JPanel implements MouseListener
{
    //need a copy of main for the update() function
    GameMain      myMain;

    //updates itself based on myBoard
    CellState[][] myBoard;

    public DrawCanvas(GameMain main)
    {
        myMain = main;
        myBoard = main.getBoard();
        this.setPreferredSize(new Dimension(GameMain.CANVAS_WIDTH, GameMain.CANVAS_HEIGHT));
        this.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        System.out.println("printing");
        // invoke via repaint()
        // super.paintComponent(g); // fill background
        // setBackground(Color.WHITE); // set its background color

        // Draw the state of all the cells if they are not empty

        Graphics2D g2d = (Graphics2D)g;

        for (int row = 0; row < GameMain.ROWS; row++)
        {
            for (int col = 0; col < GameMain.COLS; col++)
            {
                int x1 = col * GameMain.CELL_SIZE;
                int y1 = row * GameMain.CELL_SIZE;
                int x2 = (col + 1) * GameMain.CELL_SIZE;
                int y2 = (row + 1) * GameMain.CELL_SIZE;
                if (myBoard[row][col] == CellState.BLACK)
                {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(x1, y1, x2, y2);

                }
                else if (myBoard[row][col] == CellState.WHITE)
                {
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(x1, y1, x2, y2);
                }
            }
        }

        // Print status-bar message

    }

    public void update(CellState[][] board)
    {
        myBoard = board;
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("Action: DrawCanvas, Mouse Click");
        int mouseX = e.getX();
        int mouseY = e.getY();

        // Get the row and column clicked

        int rowSelected = mouseY / GameMain.CELL_SIZE;
        int colSelected = mouseX / GameMain.CELL_SIZE;

        //call hierarchy: GameMain.updateGame -> GameGui.update -> DrawCanvas.update
        myMain.updateGame(rowSelected, colSelected);

        // Refresh the drawing canvas
        // repaint(); // Call-back paintComponent().

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

}