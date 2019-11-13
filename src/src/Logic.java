import javax.swing.JOptionPane;

/**
 * @author Sush and Louis
 * Advanced Programming Topics Period 3
 * TicTacToe with AI
 * This class is intended to handle much of the logic behind the TicTacToe game.
 */
public class Logic {

    //Board object to handle the graphic aspects of the game.
    private static Board board;

    /**
     * Main method run when the game is started.
     * @param args String array of arguments.
     */
    public static void main(String[] args) {
        new Logic(true);
    }

    /**
     * Constructor created when Logic object is created.
     * @param isFirstGame Boolean representing whether this is the first game or not.
     */
    private Logic(boolean isFirstGame) {
        //If this is the first game, a whole new board is created.
        if(isFirstGame) {
            board = new Board();
        }
        //On click handler.
        board.getPanel().onClick(this::handleClick);
    }

    /**
     * Called whenever the user clicks on the DrawingPanel screen.
     * @param x X position of the click.
     * @param y Y position on the click.
     */
    private void handleClick(int x, int y) {
        board.updateTurn(x, y);
        checkWin();
    }

    /**
     * This method gets the status of the board, then goes through every row and finds a win condition based on if it's
     * in a row. The method then passes the state off to the announceWinner method to print out who has won.
     * 0 if it's a draw, 1 if X wins, 2 if O wins
     * */
    private void checkWin() {
        int[][] status = board.getBoardStatus();
            //Checks for horizontal winning scenarios.
            for (int[] ints : status) {
                if (ints[0] != 0 && ints[0] == ints[1] && ints[2] == ints[1]) {
                    announceWinner(ints[0]);
                    return;
                }
            }
            //Checks for vertical winning scenarios.
            for (int col = 0; col < status.length; col++) {
                if (status[0][col] != 0 && status[0][col] == status[1][col] && status[1][col] == status[2][col]) {
                    announceWinner(status[0][col]);
                    return;
                }
            }
            //Checks for diagonal winning scenarios.
            if (status[0][0] != 0 && status[1][1] == status[2][2] && status[2][2] == status[0][0]) {
                announceWinner(status[1][1]);
                return;
            }
            //Checks for another diagonal winning scenario.
            if (status[0][2] != 0 && status[1][1] == status[2][0] && status[2][0] == status[0][2]) {
                announceWinner(status[1][1]);
                return;
            }
            //Checks for a draw
            if(checkDraw(status)) {
                announceWinner(0);
            }
    }

    /**
     * Checks if there is a draw in the game.
     * @param status Integer array representing the state of the board.
     * @return Boolean representing a draw or not.
     */
    private boolean checkDraw(int[][] status) {
        for (int[] rows : status) {
            for (int cols : rows) {
                if (cols == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method inherits the win state of the status array and prints out a message based on the state, then asks
     * the user if they want to play again
     * <p>
     * 0 = draw, 1 = x, 2 = o
     * @param winner the state of the winning side
     */
    private void announceWinner(int winner) {
        if (winner == 1) {
            promptGame("O has won the game!");
        } else if (winner == 2) {
            promptGame("X has won the game!");
        } else {
            promptGame("This game is a draw!");
        }
    }

    /**
     * This method asks the user if they want to play again. It closes the dialog and exits the window if no or cancel
     * is selected. Otherwise a new window with a new board is opened.
     */
    private void promptGame(String endText) {
        int s = JOptionPane.showConfirmDialog(null, endText + " " +
                "Would you like to play again?");
        if(s != 0) {
            System.exit(0);
        } else {
            //Reset the game here
            resetGame();
        }
    }

    /**
     * Resets the game if the user wants to play again.
     */
    private void resetGame() {
        board.resetBoard();
        new Logic(false);
    }

}
