import java.util.ArrayList;

class Cup {

    private ArrayList<Move> moves;

    Cup() {
        moves = new ArrayList<>();
    }

    void addMove(Move userChoice) {
        moves.add(userChoice);
    }

    private void addPossibleMoves() {

    }

    Move getBestMove() {
        return moves.get((int) (Math.random() * moves.size()));
    }
}
