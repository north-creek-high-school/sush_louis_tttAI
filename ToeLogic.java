import java.awt.*;
import javax.swing.*;
/**
 * @author Sush Mullur and Louis Cai
 * @since 2019-9-24
 * Period 3 Adv Programming Topics
 * <p>
 * This class is intended to provide the majority of the functionality for the tictactoe board.
 * It handles the logical aspects of the game.
 */

public class ToeLogic {

    //Creates a 2D 3 by 3 array of points that are to store where the x's and y's are on the board
    private Point[][] coordinates = new Point[3][3];
    //Creates a turnNumber variable to store which turn the board is on and who's turn it is. Intended to be less than 9
    private int turnNumber = 0;
    //Initializes the empty board object
    private static ToeBoard board;
    //This stores if the game is now over and if it is time to end the game
    private boolean gameOver = false;

    //Initializes the board to the toeBoard object
    public static void main(String[] args) {
        board = new ToeBoard();
    }

    /**
     * This method prints the place that was clicked and hands it off to decidePlace to decide where to place it, then
     * adds to turnNumber to keep track of which turn it is. The method then checks if 9 turns have passed, and then
     * checks for a draw, then checks if someone has won.
     *
     * @param x The x coordinate of the click
     * @param y The y coordinate of the click
     */
    void onClick(int x, int y) {
        if(!gameOver) {
            decidePlace(x, y);
            this.turnNumber++;
            decideWin();
            if (this.turnNumber >= 8) checkDraw();
        }
    }

    /**
     * This method goes through the status array and checks to see if the board is full of 0s, if the count is
     * not equal to 0, it announces the winner
     */
    private void checkDraw() {
        int[][] status = board.getStatus();
        int emptyCount = 0;
        for (int[] ints : status) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    emptyCount++;
                }
            }
        }
        if (emptyCount == 0 && gameOver) announceWinner(0);
    }

    /**
     * This method takes the location of the board that was clicked and the status of the board, then if the status of
     * the board at that box is empty, it draws a object at the point and increases the turnNumber. Otherwise it
     * decreases the turnNumber to make sure the turnNUmber doesn't increase if there's nothing placed.
     *
     * @param x The x coordinate of the clicked area
     * @param y The y coordinate of the clicked area
     */
    private void decidePlace(int x, int y) {
        int[][] status = board.getStatus();

        //Checks if the box is empty before placing. If it is not empty, it is not placed.
        if (status[x / 200][y / 200] == 0) {
            board.drawObject(new Point(coordinates[x / 200][y / 200]), turnNumber);
        } else {
            turnNumber--;
        }
    }

    /**
     * This method fills the coordinates array with points
     */
    void fillGrid() {
        for (int rows = 0; rows < coordinates.length; rows++) {
            for (int cols = 0; cols < coordinates[rows].length; cols++) {
                coordinates[rows][cols] = new Point(rows * 200, (cols + 1) * 200 - 5);
            }
        }
    }

    /**
     * This method gets the status of the board, then goes through every row and finds a win condition based on if it's
     * in a row. The method then passes the state off to the announceWinner method to print out who has won.
     *
     * 0 if it's a draw, 1 if X wins, 2 if O wins
     */
    private void decideWin() {
        int[][] status = board.getStatus();

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
        int count = checkCount(status);
        if(count == 0) {
            announceWinner(0);
        }
    }

    /**
     * This method checks the count of the empty cells in the grid, then returns it.
     *
     * @param status The status of the grid
     * @return       The count of the empty cells
     */
    private int checkCount(int[][] status) {
        int count = 0;
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                if(status[row][col] == 0) {
                    count++;
                }
            }
        }
        return count;
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
            promptGame("X has won the game!");
        } else if (winner == 2) {
            promptGame("O has won the game!");
        } else {
            promptGame("This game is a draw!");
        }
        this.gameOver = true;

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
            resetGame();
        }
    }

    /**
     * This method resets the board and opens a new window with an empty board.
     */
    private void resetGame() {
        //Sets the turnNumber back to 0
        this.turnNumber = 0;
        board.reset();
    }
}