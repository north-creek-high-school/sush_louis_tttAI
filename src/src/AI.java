import java.util.HashMap;

class AI {

    private HashMap<int[][], Cup> map;

    AI() {
        map = new HashMap<>();
    }

    HashMap<int[][], Cup> getMap() {
        return this.map;
    }

    void takeTurn(int[][] boardStatus) {
        Cup currentCup = map.get(boardStatus);
        Move move = currentCup.getBestMove();
        //TODO add it here

    }

}
