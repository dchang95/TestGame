package game;

import game.GameMain.CellState;
import game.GameMain.GameState;

// The GameModel class is our Model in the MVC framework
// It contains our board and our current state
// It contains functions that analyze our board for wins
// GameModel does NOT modify itself

public class GameModel
{
    private int           myRows;

    private int           myCols;

    private CellState[][] myBoard;

    private GameState     myCurrentState;

    public GameModel(int rows, int cols, GameState c)
    {
        myRows = rows;
        myCols = cols;
        myCurrentState = c;
    }

    // public GameModel(CellState[][] board, GameState state)
    // {
    // myBoard = board;
    // myCurrentState = state;
    // }

    /**
     * Returns a random board
     */
    public CellState[][] returnRandomBoard(int rows, int cols)
    {
        System.out.println("Function: GameModel, getRandomBoard()");
        CellState[][] board = new CellState[rows][cols];

        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                board[i][j] = Math.random() < .5 ? CellState.WHITE : CellState.BLACK;

            }
        }
        return board;
    }

    /**
     * Checks a board for a win (All cells must be the same value)
     * Returns a GameState (PLAYING or WON)
     */
    public GameState returnGameState(CellState[][] board)
    {
        System.out.println("Function: GameModel, checkWin()");
        int numRows = board.length;
        int numCols = board[0].length;

        // a winning board has the same CellState for all cells
        for (int row = 0; row < numRows; row++)
        {
            for (int col = 0; col < numCols; col++)
            {
                if (board[row][col] != board[0][0])
                {
                    return GameState.PLAYING;
                }
            }
        }
        return GameState.WON;

    }

    /**
     * Returns an updated board
     */
    public CellState[][] updateBoard(int rowSelect, int colSelect, CellState[][] board)
    {
        // we can easily implement different "modes" by creating a factory class
        System.out.println("Function: GameModel, updateBoard()");
        // FOR CROSS (+) MODE
        // flips tiles in a cross
        // flipTile(rowSelected, colSelected);
        //
        // int lowerRow = rowSelected - 1;
        // int lowerCol = colSelected - 1;
        // int upperRow = rowSelected + 1;
        // int upperCol = colSelected + 1;
        //
        // if (lowerRow >= 0)
        // flipTile(lowerRow, colSelected);
        // if (upperRow < ROWS)
        // flipTile(upperRow, colSelected);
        // if (lowerCol >= 0)
        // flipTile(rowSelected, lowerCol);
        // if (upperCol < COLS)
        // flipTile(rowSelected, upperCol);

        // FOR 3x3 MINESWEEPER MODE
        // update rows first

        int numRows = board.length;
        int numCols = board[0].length;
        int newTile = rowSelect - 1;
        int row = newTile >= 0 ? newTile : 0;
        newTile = rowSelect + 1;
        int upperRowBound = newTile < numRows ? newTile : numRows - 1;

        // then update columns
        newTile = colSelect - 1;
        int col = newTile >= 0 ? newTile : 0;
        newTile = colSelect + 1;
        int upperColBound = newTile < numCols ? newTile : numCols - 1;

        for (; row <= upperRowBound; row++)
        {
            for (int newCol = col; newCol <= upperColBound; newCol++)
            {
                // swap the Seed color
                if (board[row][newCol] == CellState.BLACK)
                {
                    board[row][newCol] = CellState.WHITE;
                }
                else
                {
                    board[row][newCol] = CellState.BLACK;
                }
            }
        }

        return board;
    }

    /**************************************** Getters and Setters *********************************/

    public CellState[][] getBoard()
    {
        return myBoard;
    }

    public void setBoard(CellState[][] board)
    {
        this.myBoard = board;
    }

    public CellState getPiece(int row, int col)
    {
        return myBoard[row][col];
    }

    public GameState getCurrentState()
    {
        return myCurrentState;
    }

    public void setCurrentState(GameState currentState)
    {
        this.myCurrentState = currentState;
    }

    public int getCols()
    {
        return myCols;
    }

    public void setCols(int cols)
    {
        this.myCols = cols;
    }

    public int getRows()
    {
        return myRows;
    }

    public void setRows(int rows)
    {
        this.myRows = rows;
    }

}
