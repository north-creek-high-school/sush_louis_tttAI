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
        for(int rows = 0; rows < boardStatus.length; rows++) {
            for(int cols = 0; cols < boardStatus[rows].length; cols++) {
                if(boardStatus[rows][cols] == 0) {
                    moves.add(new Move(rows, cols));
                }
            }
        }
    }

    Move getBestMove() {
        return moves.get((int) (Math.random() * moves.size()));
    }
}
