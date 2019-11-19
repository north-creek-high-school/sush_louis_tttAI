import javax.swing.*;

/**
 * @author Sush and Louis
 * Advanced Programming Topics Period 3
 * TicTacToe with AI
 * This class is intended to handle much of the logic behind the TicTacToe game.
 */
public class Logic {

    //Board object to handle the graphic aspects of the game.
    private static Board board;
    private int gameType;
    private int turnNumber = 1;

    /**
     * Main method run when the game is started.
     * @param args String array of arguments.
     */
    public static void main(String[] args) {
        board = new Board();
        new Logic();
    }

    /**
     * Constructor created when Logic object is created.
     */
    private Logic() {
        promptGameType();
    }

    /**
     * Called whenever the user clicks on the DrawingPanel screen.
     * @param x X position of the click.
     * @param y Y position on the click.
     */
    private void handleClick(int x, int y) {
        if(board.updateTurn(x, y)) {
            System.out.println(turnNumber);
            turnNumber++;
        }
        if(gameType == 0) checkWin();
    }

    private void decideGame() {
        switch (gameType) {
            case 0:
                playerVPlayer();
                break;
            case 1:
                playerVLearningAI();
                break;
            case 2:
                playerVRecurAI();
                break;
            default:
                break;
        }
    }

    /**
     * TODO
     */
    private void playerVRecurAI() {

    }

    /**
     * TODO
     */
    private void playerVLearningAI() {
        board.getPanel().onClick(this::handleClick);
        while(!checkWin()) {

        }
    }


    private void playerVPlayer() {
        //On click handler.
        board.getPanel().onClick(this::handleClick);
    }
    /**
     * This method gets the status of the board, then goes through every row and finds a win condition based on if it's
     * in a row. The method then passes the state off to the announceWinner method to print out who has won.
     * 0 if it's a draw, 1 if X wins, 2 if O wins
     * */
    private boolean checkWin() {
        int[][] status = board.getBoardStatus();
            //Checks for horizontal winning scenarios.
            for (int[] ints : status) {
                if (ints[0] != 0 && ints[0] == ints[1] && ints[2] == ints[1]) {
                    announceWinner(ints[0]);
                    return true;
                }
            }
            //Checks for vertical winning scenarios.
            for (int col = 0; col < status.length; col++) {
                if (status[0][col] != 0 && status[0][col] == status[1][col] && status[1][col] == status[2][col]) {
                    announceWinner(status[0][col]);
                    return true;
                }
            }
            //Checks for diagonal winning scenarios.
            if (status[0][0] != 0 && status[1][1] == status[2][2] && status[2][2] == status[0][0]) {
                announceWinner(status[1][1]);
                return true;
            }
            //Checks for another diagonal winning scenario.
            if (status[0][2] != 0 && status[1][1] == status[2][0] && status[2][0] == status[0][2]) {
                announceWinner(status[1][1]);
                return true;
            }
            //Checks for a draw
            if(checkDraw(status)) {
                announceWinner(0);
                return true;
            }
            return false;
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
        int result = JOptionPane.showConfirmDialog (null, endText + "\nWould you like to play again?",
                "Thanks for playing!",JOptionPane.YES_NO_OPTION);
        if(result != 0) {
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
        turnNumber = 1;
        promptGameType();
    }

    private void promptGameType() {
        String[] choices = {"Player vs Player", "Player vs Learning AI", "Player vs GameTree AI"};
        gameType = JOptionPane.showOptionDialog(null, "What type of game would you like to play?",
                "Prompt", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, choices, null);
        decideGame();
    }

}
