package zadanie3;

public class StrategoGame {

    private GameCell[][] board;

    private int whiteScore;
    private int blackScore;

    private GameCell whoseTurn;

    private StrategoGame(int boardSize) {
        board = StrategoUtils.createEmptyBoard(boardSize);
        whiteScore = 0;
        blackScore = 0;
        whoseTurn = GameCell.WHITE_PIECE;
    }

    static StrategoGame create(int boardSize) {

        return new StrategoGame(boardSize);
    }

    void makeMove(int row, int column) {
        if (row < 0 || row >= board.length || column < 0 || column >= board.length) {
            return;
        }
        if (board[row][column] != GameCell.EMPTY) {
            return;
        }

        System.out.println("Making move for: (" + row + ", " + column + ")");

        board[row][column] = whoseTurn;
        addPointsBasedOnMove(row, column);


        whoseTurn = StrategoUtils.nextPlayerFor(whoseTurn);
    }

    private void addPointsBasedOnMove(int row, int column) {
        int pointsToAdd = StrategoUtils.pointsToAdd(board, row, column);

        if (whoseTurn == GameCell.WHITE_PIECE) {
            whiteScore += pointsToAdd;
        } else if (whoseTurn == GameCell.BLACK_PIECE) {
            blackScore += pointsToAdd;
        }

    }

    public boolean isCellFree(int row, int column){
        if(Math.abs(row) >= board.length || Math.abs(column) >= board.length){
            return false;
        }
        return board[row][column] == GameCell.EMPTY;
    }

    boolean isOver() {
        return StrategoUtils.isGameOver(board);
    }

    void printBoardAndScore() {
        StrategoUtils.printBoardAndScore(board, whiteScore, blackScore, whoseTurn);
    }

    void printAtEnd() {
        StrategoUtils.printAtEnd(whiteScore, blackScore);
    }

}
