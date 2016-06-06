package game;

import javax.swing.SwingUtilities;

// The GameMain class manages the constants, enums, and all other classes
public class GameMain
{

    // Named-constants for the game board

    public static final int ROWS                = 5;                           // ROWS by COLS cells

    public static final int COLS                = 5;

    // Named-constants of the various dimensions used for graphics drawing

    public static final int CELL_SIZE           = 100;                         // cell width and height (square)

    public static final int CANVAS_WIDTH        = CELL_SIZE * COLS;            // the drawing canvas

    public static final int CANVAS_HEIGHT       = CELL_SIZE * ROWS;

    public static final int GRID_WIDTH          = 8;                           // Grid-line's width

    public static final int GRID_WIDHT_HALF     = GRID_WIDTH / 2;              // Grid-line's half-width

    // Squares are displayed inside a cell, with padding from border
    public static final int CELL_PADDING        = CELL_SIZE / 6;

    public static final int SYMBOL_SIZE         = CELL_SIZE - CELL_PADDING * 2; // width/height

    public static final int SYMBOL_STROKE_WIDTH = 8;                           // pen's stroke width

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
        initGame(); // initialize the game board contents and game variables
        gameGui = new GameGui(this);
    }

    /**
     * Sets the GameModel board
     */
    public void initGame()
    {
        System.out.println("Function: GameMain, initGame()");
        game.setBoard(game.getRandomBoard(ROWS, COLS));
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
        game.setBoard(game.updateBoard(rowSelected, colSelected, game.getBoard()));
        game.setCurrentState(game.checkWin(game.getBoard(), game.getPiece(rowSelected, colSelected)));

        gameGui.update(game.getBoard(), game.getCurrentState());

        if (game.getCurrentState() == GameState.WON)
        {
            initGame(); // restart the game
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