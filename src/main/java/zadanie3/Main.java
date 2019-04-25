package zadanie3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //⚪, ⚫, ⃞

        int n = 8;

        StrategoGame game = StrategoGame.create(n);

        Scanner s = new Scanner(System.in);

        while (!game.isOver()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            game.printBoardAndScore();

            System.out.println("Where do you want to put next piece? row and column.");
            int row = s.nextInt();
            int column = s.nextInt();

            game.makeMove(row, column);
        }

        game.printBoardAndScore();
        game.printAtEnd();
    }

//    private static int minimax(StrategoGameBoard board, int depth, boolean maximizingPlayer){
//        if(depth==0 || board.isGameOver()){
//            return board.getEvaluation();
//        }
//
//        if(maximizingPlayer){
//            int maxEvaluation = Integer.MIN_VALUE;
//
//            for (StrategoGameBoard nextBoard : board.getNextBoards()) {
//
//                int evaluation = minimax(nextBoard, depth-1, false);
//                maxEvaluation = Math.max(maxEvaluation, evaluation);
//            }
//            return maxEvaluation;
//        } else {
//            int minEvaluation = Integer.MAX_VALUE;
//            ArrayList<StrategoGameBoard> boards = board.getNextBoards();
//            for (StrategoGameBoard nextBoard : boards) {
//                int evaluation = minimax(nextBoard, depth-1, true);
//                minEvaluation = Math.min(minEvaluation, evaluation);
//            }
//            return minEvaluation;
//        }
//
//
//    }

}

