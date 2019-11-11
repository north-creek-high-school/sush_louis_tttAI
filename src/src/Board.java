import java.awt.*;

class Board {
    //TODO: Some bug with number of X/O's you can place.
    //Creates a array that stores if the point on the grid is occupied or not
    private int[][] occupyStatus = new int[3][3];
    //Creates a panel that is 600 pixels by 600 pixels
    private DrawingPanel panel = new DrawingPanel(600, 600);
    //Creates the graphics object
    private Graphics g = panel.getGraphics();
    private int turnNumber;

    /**
     * Constructor called when a new Board object is created.
     * It has the onClick handler on it, and draws the lines on the grid.
     */
    Board() {
        drawLines();
        panel.onClick(this::onClick);

    }

    /**
     * Called whenever the user clicks on the panel.
     * @param x X value of user click
     * @param y Y value of user click
     */
    private void onClick(int x, int y) {
        System.out.println(x + ", " + y);
        decidePlace(x, y);
    }

    /**
     * Decides where to place the X or the O.
     * @param x X value of user click
     * @param y Y value of user click
     */
    private void decidePlace(int x, int y) {
        //Checks if the box is empty before placing. If it is not empty, it is not placed.
        if (occupyStatus[x / 200][y / 200] == 0) {
            drawObject((x/200) * 200, ((y/200) * 200) + 200, turnNumber);
            turnNumber++;
        }

    }

    /**
     *
     * @param x X value of where to draw object
     * @param y Y value of where to draw object
     * @param turnNumber Integer representing X or O's turn
     */
    private void drawObject(int x, int y, int turnNumber) {
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
     * Draws the grid lines for the board
     */
    private void drawLines() {
        g.drawLine(200, 0, 200, 600);
        g.drawLine(400, 0, 400, 600);
        g.drawLine(0, 200, 600, 200);
        g.drawLine(0, 400, 600, 400);
    }

}
