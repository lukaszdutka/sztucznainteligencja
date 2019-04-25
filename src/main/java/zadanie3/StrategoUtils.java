package zadanie3;

class StrategoUtils {

    static GameCell[][] createEmptyBoard(int boardSize) {
        GameCell[][] board = new GameCell[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = GameCell.EMPTY;
            }
        }
        return board;
    }

    static boolean isGameOver(GameCell[][] board) {
        for (GameCell[] row : board) {
            for (GameCell cell : row) {
                if (cell == GameCell.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    static void printBoardAndScore(GameCell[][] board, int whiteScore, int blackScore, GameCell whoseTurn) {
        System.out.print("\t");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            GameCell[] row = board[i];
            System.out.print(i + "\t");
            for (GameCell cell : row) {
                switch (cell) {
                    case WHITE_PIECE:
                        System.out.print("⚪\t");
                        break;
                    case BLACK_PIECE:
                        System.out.print("⚫\t");
                        break;
                    case EMPTY:
                    default:
                        System.out.print("⃞\t");
                        break;
                }
            }
            if (i == 0) {
                System.out.print("  White score: " + whiteScore);
            } else if (i == 1) {
                System.out.print("  Black score: " + blackScore);
            } else if (i == 3) {
                System.out.print("  Now is turn for: " + whoseTurn);
            }
            System.out.println();
        }
        System.out.println();
    }

    static void printAtEnd(int whiteScore, int blackScore) {
        System.out.println("\n-------------------------");
        System.out.println("Final score is: " + whiteScore + ":" + blackScore);
        if (whiteScore > blackScore) {
            System.out.println("White wins!");
        } else if (blackScore > whiteScore) {
            System.out.println("Black wins!");
        } else {
            System.out.println("It's a draw!");
        }
        System.out.println("-------------------------");
    }

    static GameCell nextPlayerFor(GameCell whoseTurn) {
        if (whoseTurn == GameCell.WHITE_PIECE) {
            return GameCell.BLACK_PIECE;
        } else if (whoseTurn == GameCell.BLACK_PIECE) {
            return GameCell.WHITE_PIECE;
        }
        throw new IllegalArgumentException("whoseTurn should be WHITE or BLACK piece enum from GameCell");
    }

    static int pointsToAdd(GameCell[][] board, int row, int column) {
        int pointsToAdd = 0;

        if (columnIsFilled(board, column)) {
            pointsToAdd += board.length;
        }
        if (rowIsFilled(board, row)) {
            pointsToAdd += board.length;
        }
        if (upDownDiagonalIsFilled(board, row, column)) {
            pointsToAdd += upDownCalculatePoints(board.length, row, column);
        }
        if (downUpDiagonalIsFilled(board, row, column)) {
            pointsToAdd += downUpCalculatePoints(board.length, row, column);
        }
        return pointsToAdd;
    }

    private static boolean upDownDiagonalIsFilled(GameCell[][] board, int row, int column) {
        int min = Math.min(row, column);
        row -= min;
        column -= min;
        while (row < board.length && column < board.length) {
            if (board[row][column] == GameCell.EMPTY) {
                return false;
            }
            row++;
            column++;
        }
        return true;
    }

    private static int upDownCalculatePoints(int length, int row, int column) {
        int min = Math.min(row, column);
        row -= min;
        column -= min;
        int points = length - Math.max(row, column);
        return points > 1 ? points : 0;
    }

    private static boolean downUpDiagonalIsFilled(GameCell[][] board, int row, int column) {
        row += column; //there is some math magic to find most left-down point.
        column -= column;
        if (row >= board.length) {
            int correct = (row - board.length) + 1;
            row -= correct;
            column += correct;
        }
        while (row >= 0 && column < board.length) {
            if (board[row][column] == GameCell.EMPTY) {
                return false;
            }
            row--;
            column++;
        }
        return true;
    }

    private static int downUpCalculatePoints(int length, int row, int column) {
        row = (length - row) - 1; // mirror image in rows so it is treated as up-down
        return upDownCalculatePoints(length, row, column);
    }

    private static boolean rowIsFilled(GameCell[][] board, int rowIndex) {
        GameCell[] row = board[rowIndex];
        for (GameCell cell : row) {
            if (cell == GameCell.EMPTY) {
                return false;
            }
        }
        return true;
    }

    private static boolean columnIsFilled(GameCell[][] board, int column) {
        for (GameCell[] row : board) {
            if (row[column] == GameCell.EMPTY) {
                return false;
            }
        }
        return true;
    }

}
