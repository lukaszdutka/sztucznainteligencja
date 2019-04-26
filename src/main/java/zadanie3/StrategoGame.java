package zadanie3;

public class StrategoGame {

    private PlayerColor[][] board;

    private int whiteScore;
    private int blackScore;

    private PlayerColor whoseTurn;

    private StrategoMove lastMove;

    private StrategoGame() {
        //for cloning
    }

    private StrategoGame(int boardSize) {
        board = StrategoUtils.createEmptyBoard(boardSize);
        whiteScore = 0;
        blackScore = 0;
        whoseTurn = PlayerColor.WHITE;
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

    private void makeMove(int row, int column, boolean notify) {
        if (!isCellFree(row, column)) {
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

        if (whoseTurn == PlayerColor.WHITE) {
            whiteScore += pointsToAdd;
        } else if (whoseTurn == PlayerColor.BLACK) {
            blackScore += pointsToAdd;
        }

    }

    boolean isCellFree(int row, int column) {
        if (Math.abs(row) >= board.length || Math.abs(column) >= board.length) {
            return false;
        }
        return board[row][column] == PlayerColor.EMPTY;
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

    public PlayerColor[][] getBoard() {
        return board;
    }

    public void setBoard(PlayerColor[][] board) {
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

    public PlayerColor getWhoseTurn() {
        return whoseTurn;
    }

    public void setWhoseTurn(PlayerColor whoseTurn) {
        this.whoseTurn = whoseTurn;
    }

    public StrategoMove getLastMove() {
        return lastMove;
    }

    public void setLastMove(StrategoMove lastMove) {
        this.lastMove = lastMove;
    }

    StrategoGame copy() {
        StrategoGame game = new StrategoGame();

        game.setBoard(StrategoUtils.copy(board));
        game.setWhiteScore(whiteScore);
        game.setBlackScore(blackScore);
        game.setWhoseTurn(whoseTurn);
//        game.setLastMove(lastMove); //not needed

        return game;
    }
}
