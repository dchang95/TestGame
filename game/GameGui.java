package game;

import game.GameMain.CellState;
import game.GameMain.GameState;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameGui extends JFrame
{
    private JLabel     statusBar;   // Status Bar

    private GameMain   myMain;

    private DrawCanvas canvas;   // Drawing canvas (JPanel) for the game board

    public GameGui(GameMain main)
    {
        myMain = main;

        canvas = new DrawCanvas(myMain);

        statusBar = new JLabel("  ");
        statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
        statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));

        this.setLayout(new BorderLayout());
        this.add(canvas, BorderLayout.CENTER);
        this.add(statusBar, BorderLayout.PAGE_END); // same as SOUTH
        

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); // pack all the components in this JFrame
        this.setTitle("Tic Tac Toe");
        this.setVisible(true); // show this JFrame

    }

    public void update(CellState[][] board, GameState currentState)
    {
        canvas.update(board);
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
