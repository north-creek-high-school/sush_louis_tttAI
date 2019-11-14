import java.awt.*;

class AI {

    Point takeRandomTurn(int[][] status) {
        int randomRow = (int)(Math.random()*3);
        int randomCol = (int)(Math.random()*3);
        while(status[randomRow][randomCol] != 0) {
            randomRow = (int)(Math.random()*3);
            randomCol = (int)(Math.random()*3);
        }
        return new Point(randomRow*200 + 5, randomCol * 200 + 5);
    }
}
