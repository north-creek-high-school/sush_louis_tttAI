import javax.swing.*;

public class Logic {

    private static Board board;

    public static void main(String[] args) {
        new Logic();
    }

    private Logic() {
        board = new Board();
        board.getPanel().onClick(this::handleClick);
    }

    private void handleClick(int x, int y) {
        board.updateArray(x, y);
        checkWin();
    }

    /**
     * This method gets the status of the board, then goes through every row and finds a win condition based on if it's
     * in a row. The method then passes the state off to the announceWinner method to print out who has won.
     *
     * 0 if it's a draw, 1 if X wins, 2 if O wins
     * */
    private void checkWin() {
        int[][] status = board.getBoardStatus();
        //Checks for horizontal winning scenarios.
        for (int[] ints : status) {
            if (ints[0] != 0 && ints[0] == ints[1] && ints[2] == ints[1]) {
                announceWinner(ints[0]);
            }
        }
        //Checks for vertical winning scenarios.
        for (int col = 0; col < status.length; col++) {
            if (status[0][col] != 0 && status[0][col] == status[1][col] && status[1][col] == status[2][col]) {
                announceWinner(status[0][col]);
            }
        }
        //Checks for diagonal winning scenarios.
        if (status[0][0] != 0 && status[1][1] == status[2][2] && status[2][2] == status[0][0]) {
            announceWinner(status[1][1]);
        }
        //Checks for another diagonal winning scenario.
        if (status[0][2] != 0 && status[1][1] == status[2][0] && status[2][0] == status[0][2]) {
            announceWinner(status[1][1]);
        }
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

        }
    }

}
