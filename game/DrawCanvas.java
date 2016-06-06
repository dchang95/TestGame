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

// The GUI component that actually draws the game
class DrawCanvas extends JPanel implements MouseListener
{
    // need a copy of main for the update() function
    GameMain      myMain;

    // updates itself based on myBoard
    CellState[][] myBoard;

    public DrawCanvas(GameMain main)
    {
        myMain = main;
        myBoard = main.getBoard();
        this.setPreferredSize(new Dimension(GameMain.CANVAS_WIDTH, GameMain.CANVAS_HEIGHT));
        this.addMouseListener(this);
    }

    /**
     * invoked during repaint
     * I have no clue what this does
     */
    @Override
    public void paintComponent(Graphics g)
    {
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
                    // g2d.fillRoundRect(x1, y1, x2, y2, 5, 5);

                }
                else if (myBoard[row][col] == CellState.WHITE)
                {
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(x1, y1, x2, y2);
                }
            }
        }
    }

    /**
     * redraws the board
     */
    public void update(CellState[][] board)
    {
        System.out.println("Function: DrawCanvas, update()");
        myBoard = board;
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
    }

    /**
     * Determines which cell should be updated
     * Used mouseReleased because mouseClicked was finnicky (if you move your mouse at all during the click it does not register)
     * Must handle rowSelect/colSelect going out of the frame
     * 
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
        int mouseX = e.getX();
        int mouseY = e.getY();

        int rowSelected = mouseY / GameMain.CELL_SIZE;
        int colSelected = mouseX / GameMain.CELL_SIZE;

        System.out.println("Action: DrawCanvas, mouseReleased(), rowSelected: " + rowSelected + ", colSelected: "
                + colSelected);

        // test if rowSelected/colSelected are within the frame
        if (rowSelected >= 0 && colSelected >= 0 && rowSelected < myBoard.length && colSelected < myBoard[0].length)
        {
            myMain.updateGame(rowSelected, colSelected);
        }
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