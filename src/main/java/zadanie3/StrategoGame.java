package zadanie3;

public class StrategoGame {

    private GameCell[][] board;

    private int whiteScore;
    private int blackScore;

    private GameCell whoseTurn;

    private StrategoMove lastMove;

    private StrategoGame() {
        //for cloning
    }

    private StrategoGame(int boardSize) {
        board = StrategoUtils.createEmptyBoard(boardSize);
        whiteScore = 0;
        blackScore = 0;
        whoseTurn = GameCell.WHITE_PIECE;
    }

    static StrategoGame create(int boardSize) {

        return new StrategoGame(boardSize);
    }

    void makeMoveWithNotification(int row, int column) {
        makeMove(row, column, true);
    }

    void makeMoveSilent(int row, int column) {
        makeMove(row, column, false);
    }

    void makeMove(int row, int column, boolean notify) {
        if (row < 0 || row >= board.length || column < 0 || column >= board.length) {
            return;
        }
        if (board[row][column] != GameCell.EMPTY) {
            return;
        }

        if (notify) {
            System.out.println("Making move for: (" + row + ", " + column + ")");
        }

        board[row][column] = whoseTurn;
        addPointsBasedOnMove(row, column);
        lastMove = new StrategoMove(row, column);

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

    public boolean isCellFree(int row, int column) {
        if (Math.abs(row) >= board.length || Math.abs(column) >= board.length) {
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

    public GameCell[][] getBoard() {
        return board;
    }

    public void setBoard(GameCell[][] board) {
        this.board = board;
    }

    public int getWhiteScore() {
        return whiteScore;
    }

    public void setWhiteScore(int whiteScore) {
        this.whiteScore = whiteScore;
    }

    public int getBlackScore() {
        return blackScore;
    }

    public void setBlackScore(int blackScore) {
        this.blackScore = blackScore;
    }

    public GameCell getWhoseTurn() {
        return whoseTurn;
    }

    public void setWhoseTurn(GameCell whoseTurn) {
        this.whoseTurn = whoseTurn;
    }

    public StrategoMove getLastMove() {
        return lastMove;
    }

    public void setLastMove(StrategoMove lastMove) {
        this.lastMove = lastMove;
    }

    public StrategoGame copy() {
        StrategoGame game = new StrategoGame();

        game.setBoard(StrategoUtils.copy(board));
        game.setWhiteScore(whiteScore);
        game.setBlackScore(blackScore);
        game.setWhoseTurn(whoseTurn);
//        game.setLastMove(lastMove); //not needed

        return game;
    }
}
