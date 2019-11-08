import java.awt.*;

class Board {

    //Creates a array that stores if the point on the grid is occupied or not
    private int[][] occupyStatus = new int[3][3];
    //Creates a panel that is 600 pixels by 600 pixels
    private DrawingPanel panel = new DrawingPanel(600, 600);
    //Creates the graphics object
    private Graphics g = panel.getGraphics();

    Board() {
        drawLines();
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

}
