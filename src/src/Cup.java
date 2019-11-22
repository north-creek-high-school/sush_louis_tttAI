import java.util.ArrayList;

/**
 * This class is supposed to be a data structure that stores all the previous moves made by the AI.
 * The reason for this is so that it can be used to train the AI and make it more intelligent.
 */
class Cup {

    //A list of all moves made is stored.
    private ArrayList<Move> moves;

    /**
     * Constructor of the Cup object that fills in possible moves based on board status.
     * @param boardStatus Two dimensional array containing the status of occupancy of the board.
     */
    Cup(int[][] boardStatus) {
        moves = new ArrayList<>();
        addPossibleMoves(boardStatus);
    }

    /**
     * Adds a move to the cup when AI takes a turn.
     * @param userChoice Move representing what the AI chose.
     */
    void addMove(Move userChoice) {
        moves.add(userChoice);
    }

    /**
     * Adds all possible moves inside the Cup in order to make sure all possible moves are being represented.
     * @param boardStatus Array representing the occupancy of the board.
     */
    private void addPossibleMoves(int[][] boardStatus) {
        //Iterates through two dimensional array.
        for(int rows = 0; rows < boardStatus.length; rows++) {
            for(int cols = 0; cols < boardStatus[rows].length; cols++) {
                //If the spot is empty, a possible move is added.
                if(boardStatus[rows][cols] == 0) {
                    moves.add(new Move(rows, cols));
                }
            }
        }
    }

    /**
     * Gets the "best" possible move the AI can make.
     * For now, it chooses a random Move from the list.
     * @return The Move the AI can make.
     */
    Move getBestMove() {

        for(Move move: moves) {
            System.out.println(move.toString());
        }
        System.out.println("Done with ArrayList");

        Move m = moves.get((int) (Math.random() * moves.size()));
        return m;
    }

    public String toString() {
        return moves.toString();
    }
}
