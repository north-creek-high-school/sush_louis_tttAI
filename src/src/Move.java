/**
 * This class is a way to represent a move as more than just 2 coordinates, using a form of abstraction.
 */
class Move {

    //Column of the move placement.
    private int col;
    //Row of the move placement.
    private int row;

    /**
     * Creates a new move to be added to the map, to be used for AI learning.
     * @param row Row of the move placement.
     * @param col Column of the move placement.
     */
    Move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Gets an X value on the board to place the move.
     * @return Corresponding X value
     */
    int getX() {
        return (this.col/200) * 200;
    }

    /**
     * Gets a Y value on the board to place the move.
     * @return Corresponding Y value.
     */
    int getY() {
        return (this.col/200) * 200 + 200;
    }
}
