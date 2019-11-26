import javax.swing.*;
import java.util.HashMap;

/**
 * @author Sush Mullur
 * @author Louis Cai
 * Advanced Programming Topics Period 3
 * TicTacToe with AI
 * This class is intended to handle much of the logic behind the TicTacToe game.
 */
public class Logic {

    //Board object to handle the graphic aspects of the game.
    private static Board board;
    //Stores the type of the game as an integer.
    private int gameType;
    //Integer to keep track of the number of turns completed.
    private int turnNumber = 1;
    private HashMap<int[][], Cup> globalTrainingData;

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
        globalTrainingData = new HashMap<>();
    }

    /**
     * Called whenever the user clicks on the DrawingPanel screen.
     * @param x X position of the click.
     * @param y Y position on the click.
     */
    private void handleClick(int x, int y) {
        int[] loc = convertToRowCol(x, y);
        if(board.updateTurn(loc[0], loc[1])) turnNumber++;
        if(gameType == 0) checkWin();
    }

    private int[] convertToRowCol(int x, int y) {
        //Col as first value
        //Row as second value
        int[] place = new int[2];
        place[0] = x/200;
        place[1] = y/200;
        return place;
    }

    /**
     * Uses a series of switch statements to determine which game to initiate.
     */
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
     * This method provides functionality for player vs learning AI.
     */
    private void playerVLearningAI() {
        AI ai = new AI();
        board.getPanel().onClick(this::handleClick);
        while(!checkWin()) {
            if(turnNumber % 2 == 0) {
                try {
                    Thread.sleep(10);
                } catch (Exception ignored) {
                }
                Move turn = ai.takeTurn(board.getBoardStatus());
                board.updateTurn(turn.getCol(), turn.getRow());
                turnNumber++;

            }
        }
        updateTrainingData(ai.getMap());
    }

    private void updateTrainingData(HashMap<int[][], Cup> map) {

    }

    /**
     * Initiates a player vs player game.
     * For now, it just has the onClick handler, but more functionality might be added.
     * One such functionality could be learning from a player v player game.
     */
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
            promptGame("X has won the game!");
        } else if (winner == 2) {
            promptGame("O has won the game!");
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

    /**
     * Prompts the user for the type of game to play.
     */
    private void promptGameType() {
        String[] choices = {"Player vs Player", "Player vs Learning AI", "Player vs GameTree AI"};
        gameType = JOptionPane.showOptionDialog(null, "What type of game would you like to play?",
                "Prompt", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, choices, null);
        decideGame();
    }

}
