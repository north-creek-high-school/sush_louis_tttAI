import java.awt.Graphics;
import java.awt.Font;

class Board {

    //Creates a array that stores if the point on the grid is occupied or not
    private int[][] boardStatus = new int[3][3];
    //Creates a panel that is 600 pixels by 600 pixels
    private DrawingPanel panel = new DrawingPanel(600, 600);
    //Creates the graphics object
    private Graphics g = panel.getGraphics();
    private int turnNumber;

    /**
     * Constructor called when a new Board object is created.
     * Calls the drawLines method, so that the lines are drawn.
     */
    Board() {
        drawLines();
    }

    void updateArray(int x, int y) {
        if(boardStatus[x/200][y/200] == 0) {
            if (turnNumber % 2 == 0) {
                boardStatus[x / 200][y / 200] = 2;
                drawObject((x/200)*200, ((y/200) * 200) + 200);
            } else {
                boardStatus[x / 200][y / 200] = 1;
            }
            drawObject((x/200)*200, ((y/200) * 200) + 200);
            turnNumber++;
        }
    }

    /**
     *
     * @param x X value of where to draw object
     * @param y Y value of where to draw object
     */
     private void drawObject(int x, int y) {
        g.setFont(new Font("Monospace", Font.PLAIN, 200));
            if (turnNumber % 2 == 0) {
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

    DrawingPanel getPanel() {
        return this.panel;
    }

    int[][] getBoardStatus() {
        return this.boardStatus;
    }

}
