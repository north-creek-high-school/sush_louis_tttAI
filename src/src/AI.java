import java.util.HashMap;

class AI {

    private HashMap<int[][], Cup> map;

    AI() {
        map = new HashMap<>();
        fillMap();
    }

    HashMap<int[][], Cup> getMap() {
        return this.map;
    }

    Move takeTurn(int[][] boardStatus) {
        Cup currentCup = map.get(boardStatus);
        Move move = currentCup.getBestMove();
        return move;
        //TODO add it here

    }

    private void fillMap() {

    }

}
