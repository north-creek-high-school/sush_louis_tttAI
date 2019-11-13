public class Logic {

    private static Board board;

    public static void main(String[] args) {
        new Logic();
    }

    private Logic() {
        board = new Board();
        board.getPanel().onClick(this::handleClick);
    }

    private void handleClick(int x, int y) {
        System.out.println(x + ", " + y);
        System.out.println("In handleClick");
        board.updateArray(x, y);
    }

}
