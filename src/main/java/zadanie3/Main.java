package zadanie3;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<StrategoMove> nextComputerMove;

    //should be even
    private final static int DEPTH_OF_WHITE_PLAYER = 2;
    private final static int DEPTH_OF_BLACK_PLAYER = 2;

    public static void main(String[] args) {
        //⚪, ⚫, ⃞

        int n = 8;

        StrategoGame game = StrategoGame.create(n);

        Scanner s = new Scanner(System.in);

        while (!game.isOver()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            game.printBoardAndScore();

            if (game.getWhoseTurn() == GameCell.WHITE_PIECE) {
                System.out.println("Computer is calculating move.");

                //not sure about this bot playing, might be bad
//                minimax(game, DEPTH_OF_WHITE_PLAYER, false);
//                StrategoMove move = MiniMaxUtils.getRandomMove(nextComputerMove);
//                game.makeMoveWithNotification(move.getRow(), move.getColumn());

                System.out.println("Where do you want to put next piece? row and column.");
                int row = s.nextInt();
                int column = s.nextInt();
                game.makeMoveWithNotification(row, column);
            } else {
                System.out.println("Computer is calculating move.");
                minimax(game, DEPTH_OF_BLACK_PLAYER, true);

                StrategoMove move = MiniMaxUtils.getRandomMove(nextComputerMove);
                game.makeMoveWithNotification(move.getRow(), move.getColumn());
            }

        }

        game.printBoardAndScore();
        game.printAtEnd();
    }

    private static int minimax(StrategoGame game, int depth, boolean maximizingPlayer) {
        if (depth == 0 || game.isOver()) {
            return MiniMaxUtils.evaluate(game);
        }

        if (maximizingPlayer) {
            int maxEvaluation = Integer.MIN_VALUE;

            for (StrategoGame nextBoard : MiniMaxUtils.getNextMoves(game)) {
                int evaluation = minimax(nextBoard, depth - 1, false);
                if (evaluation >= maxEvaluation && depth == DEPTH_OF_BLACK_PLAYER) {
                    if(evaluation == maxEvaluation){
                        nextComputerMove.add(nextBoard.getLastMove());
                    } else {
                        nextComputerMove = new ArrayList<>();
                        nextComputerMove.add(nextBoard.getLastMove());
                    }
                }
                maxEvaluation = Math.max(maxEvaluation, evaluation);
            }
            return maxEvaluation;
        } else {
            int minEvaluation = Integer.MAX_VALUE;
            for (StrategoGame nextBoard : MiniMaxUtils.getNextMoves(game)) {
                int evaluation = minimax(nextBoard, depth - 1, true);
                if (evaluation <= minEvaluation && depth == DEPTH_OF_WHITE_PLAYER) {
                    if(evaluation == minEvaluation){
                        nextComputerMove.add(nextBoard.getLastMove());
                    } else {
                        nextComputerMove = new ArrayList<>();
                        nextComputerMove.add(nextBoard.getLastMove());
                    }
                }
                minEvaluation = Math.min(minEvaluation, evaluation);
            }
            return minEvaluation;
        }


    }

}

