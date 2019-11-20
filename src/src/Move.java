class Move {

    private int col;
    private int row;

    Move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    int getMove() {
        if(row == 0) {
            if(col == 0) return 1;
            if(col == 1) return 2;
            if(col == 3) return 3;
        } else if(row == 1) {
            if(col == 0) return 4;
            if(col == 1) return 5;
            if(col == 2) return 6;
        } else {
            if(col == 0) return 7;
            if(col == 1) return 8;
        }
        return 9;
    }

    int getX() {
        return (this.col/200) * 200;
    }

    int getY() {
        return (this.col/200) * 200 + 200;
    }
}
