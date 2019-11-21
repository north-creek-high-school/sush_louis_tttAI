import java.util.HashMap;

/**
 * This class represents an artificial player that can play against the user.
 * It is intended to learn by winning against itself after multiple games.
 * Once trained, it can play against the user.
 */
class AI {

    //HashMap containing all the Cups for each possible Board state.
    private HashMap<int[][], Cup> map;

    /**
     * Constructor for the AI object, which, for now, instantiates the HashMap.
     */
    AI() {
        map = new HashMap<>();
    }

    /**
     * Takes a turn using the Cups in the map.
     * @param boardStatus Occupancy status of the game board.
     * @return The Move the AI decides to make.
     */
    Move takeTurn(int[][] boardStatus) {
        Cup currentCup = map.get(boardStatus);
        //If there is no cup in the location, a new one is made.
        if(currentCup == null) {
            map.put(boardStatus, new Cup(boardStatus));
            currentCup = map.get(boardStatus);
        }
        //Move is added to the cup.
        Move move = currentCup.getBestMove();
        currentCup.addMove(move);
        //Cup is added back to the Map.
        map.put(boardStatus, currentCup);
        return move;
    }

}
