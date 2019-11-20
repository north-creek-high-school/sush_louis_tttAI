import java.util.ArrayList;

class Cup {

    private ArrayList<Move> moves;

    Cup(int[][] boardStatus) {
        moves = new ArrayList<>();
        addPossibleMoves(boardStatus);
    }

    void addMove(Move userChoice) {
        moves.add(userChoice);
    }

    private void addPossibleMoves(int[][] boardStatus) {

    }

    Move getBestMove() {
        return moves.get((int) (Math.random() * moves.size()));
    }
}
