package zadanie3;

class StrategoUtils {

    static PlayerColor[][] createEmptyBoard(int boardSize) {
        PlayerColor[][] board = new PlayerColor[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = PlayerColor.EMPTY;
            }
        }
        return board;
    }

    static boolean isGameOver(PlayerColor[][] board) {
        for (PlayerColor[] row : board) {
            for (PlayerColor cell : row) {
                if (cell == PlayerColor.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    static void printBoardAndScore(PlayerColor[][] board, int whiteScore, int blackScore, PlayerColor whoseTurn) {
        System.out.print("\t");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            PlayerColor[] row = board[i];
            System.out.print(i + "\t");
            for (PlayerColor cell : row) {
                switch (cell) {
                    case WHITE:
                        System.out.print("⚪\t");
                        break;
                    case BLACK:
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

    static PlayerColor nextPlayerFor(PlayerColor whoseTurn) {
        if (whoseTurn == PlayerColor.WHITE) {
            return PlayerColor.BLACK;
        } else if (whoseTurn == PlayerColor.BLACK) {
            return PlayerColor.WHITE;
        }
        throw new IllegalArgumentException("whoseTurn should be WHITE or BLACK piece enum from PlayerColor");
    }

    static int pointsToAdd(PlayerColor[][] board, int row, int column) {
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

    private static boolean upDownDiagonalIsFilled(PlayerColor[][] board, int row, int column) {
        int min = Math.min(row, column);
        row -= min;
        column -= min;
        while (row < board.length && column < board.length) {
            if (board[row][column] == PlayerColor.EMPTY) {
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

    private static boolean downUpDiagonalIsFilled(PlayerColor[][] board, int row, int column) {
        row += column; //there is some math magic to find most left-down point.
        column -= column;
        if (row >= board.length) {
            int correct = (row - board.length) + 1;
            row -= correct;
            column += correct;
        }
        while (row >= 0 && column < board.length) {
            if (board[row][column] == PlayerColor.EMPTY) {
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

    private static boolean rowIsFilled(PlayerColor[][] board, int rowIndex) {
        PlayerColor[] row = board[rowIndex];
        for (PlayerColor cell : row) {
            if (cell == PlayerColor.EMPTY) {
                return false;
            }
        }
        return true;
    }

    private static boolean columnIsFilled(PlayerColor[][] board, int column) {
        for (PlayerColor[] row : board) {
            if (row[column] == PlayerColor.EMPTY) {
                return false;
            }
        }
        return true;
    }

    static PlayerColor[][] copy(PlayerColor[][] oldBoard) {
        PlayerColor[][] board = new PlayerColor[oldBoard.length][oldBoard.length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(oldBoard[i], 0, board[i], 0, board.length);
        }
        return board;
    }
}
