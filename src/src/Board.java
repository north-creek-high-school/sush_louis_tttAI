import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

/**
 * @author Sush and Louis
 * Advanced Programming Topics Period 3
 * TicTacToe with AI
 * This class is intended to handle much of the graphics behind the TicTacToe game.
 */
class Board {

    //Creates a array that stores if the point on the grid is occupied or not
    private int[][] boardStatus = new int[3][3];
    //Creates a panel that is 600 pixels by 600 pixels
    private DrawingPanel panel = new DrawingPanel(600, 600);
    //Creates the graphics object
    private Graphics g = panel.getGraphics();
    //Number representing if it is X's or O's turn.
    private boolean drawX = true;

    /**
     * Constructor called when a new Board object is created.
     * Calls the drawLines method, so that the lines are drawn.
     */
    Board() {
        panel.setBackground(Color.GREEN);
        drawLines();
    }

    /**
     * Updates the array with the correct play the user makes.
     * @param x X value of the click.
     * @param y Y value of the click.
     */
    boolean updateTurn(int x, int y) {
        if(boardStatus[x/200][y/200] == 0) {
            if (drawX) {
                boardStatus[x / 200][y / 200] = 1;
            } else {
                boardStatus[x / 200][y / 200] = 2;
            }
            drawObject((x/200)*200, ((y/200) * 200) + 200);
            drawX = !drawX;
            return true;
        }
        return false;
    }

    /**
     *
     * @param x X value of where to draw object
     * @param y Y value of where to draw object
     */
     private void drawObject(int x, int y) {
        g.setFont(new Font("Monospace", Font.PLAIN, 200));
            if (drawX) {
                g.drawString("X", x + 25, y - 10);
            } else {
                g.drawString("O", x + 25, y - 10);
            }
    }

    /**
     * Draws the grid lines for the board
     */
    private void drawLines() {
        g.drawLine(200, 0, 200, 600);
        g.drawLine(400, 0, 400, 600);
        g.drawLine(0, 200, 600, 200);
        g.drawLine(0, 400, 600, 400);
    }

    /**
     * Gets the state of the board containing all the moves.
     * @return 2 dimensional array of integers representing the state of the board.
     */
    int[][] getBoardStatus() {
        return this.boardStatus;
    }

    /**
     *

     /**
     * Returns the DrawingPanel object for the onClick handler.
     * @return DrawingPanel object.
     */
    DrawingPanel getPanel() {
        return this.panel;
    }

    /**
     * This method resets the board state, clears the panel and turn count, and draws fresh new lines.
     * This is for when the user wants to play the game a second time.
     */
    void resetBoard() {
        boardStatus = new int[3][3];
        panel.clear();
        drawLines();
        drawX = true;
    }


}
