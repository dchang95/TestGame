package game;

import javax.swing.SwingUtilities;

// The GameMain class manages the constants, enums, and all other classes
public class GameMain
{
    /**************************************** CONSTANTS ******************************************/
    public static final int ROWS          = 5;               // ROWS by COLS cells

    public static final int COLS          = 5;

    // Named-constants of the various dimensions used for graphics drawing

    public static final int CELL_SIZE     = 100;             // cell width and height (square)

    public static final int CANVAS_WIDTH  = CELL_SIZE * COLS;            // the drawing canvas

    public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;

    /**************************************** ENUMS ******************************************/

    // Represents the State of the game

    public enum GameState
    {
        PLAYING, WON
    }

    // Represents the State of each cell

    public enum CellState
    {
        BLACK, WHITE
    }

    /**************************************** VARIABLES ******************************************/

    private GameModel game;

    private GameGui   gameGui;

    /**
     * Creates a game Model
     * Starts the game
     * Creates the GUI
     * MUST be in this order
     */
    public GameMain()
    {
        game = new GameModel(ROWS, COLS, GameState.PLAYING);
        cleanBoard(); // initialize the game board contents and game variables
        gameGui = new GameGui(this);
    }

    /**
     * Sets the GameModel board
     */
    public void cleanBoard()
    {
        System.out.println("Function: GameMain, cleanBoard()");
        game.setBoard(game.returnRandomBoard(ROWS, COLS));
    }

    /**
     * Occurs after a player click
     * First update GameModel board
     * Then update GameModel state
     * Then update GUI
     * Restart game if applicable
     */
    public void updateGame(int rowSelected, int colSelected)
    {
        System.out.println("Function: GameMain, updateGame()");
        game.setBoard(game.updateBoard(rowSelected, colSelected, game.getBoard()));
        game.setCurrentState(game.returnGameState(game.getBoard()));

        gameGui.update(game.getBoard(), game.getCurrentState());

        if (game.getCurrentState() == GameState.WON)
        {
            cleanBoard();
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new GameMain();
            }
        });
    }

    public CellState[][] getBoard()
    {
        return game.getBoard();
    }
}