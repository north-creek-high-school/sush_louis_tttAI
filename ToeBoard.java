import java.awt.*;

/**
 * @author Sush Mullur and Louis Cai
 * @since 2019-09-24
 * Period 3 Adv Programming Topics
 * <p>
 * This project is intended to create a two player tictactoe game that creates a dialog that asks if players would like
 * to play again or exit
 */

class ToeBoard {
    //Creates a array that stores if the point on the grid is occupied or not
    private int[][] occupyStatus = new int[3][3];
    //Creates a panel that is 600 pixels by 600 pixels
    private DrawingPanel panel = new DrawingPanel(600, 600);
    //Creates the graphics object
    private Graphics g = panel.getGraphics();
    private ToeLogic logic;

    /**
     * This method creates a logic object, then draws the board.
     * <p>
     * It creates a lambda expression to initialize the onClickHandler inside the ToeLogic class, then fills the grid of
     * the board with points
     */
    ToeBoard() {
        logic = new ToeLogic();
        drawLines();
        panel.onClick(logic::onClick);
        logic.fillGrid();
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
     * This method takes the point and turnNumber, then draws the marker based on who's turn it is.
     * @param p          the point where the marker is to be drawn
     * @param turnNumber the number of turns that have passed since the game has begun
     */
    void drawObject(Point p, int turnNumber) {
        int x = (int) p.getX();
        int y = (int) p.getY();
        g.setFont(new Font("Monospace", Font.PLAIN, 200));
        if (turnNumber % 2 == 0) {
            g.drawString("X", x + 25, y - 10);
            occupyStatus[x / 200][y / 200] = 1;
        } else {
            g.drawString("O", x + 25, y - 10);
            occupyStatus[x / 200][y / 200] = 2;
        }
    }

    /**
     * 	Allows the logic class to get status of the status array
     */
    int[][] getStatus() {
        return this.occupyStatus;
    }

    /**
     * This method clears the panel and resets it to an empty state
     */
    void reset() {
        panel.clear();
        drawLines();
        panel.onClick(logic::onClick);
        logic.fillGrid();
    }
}