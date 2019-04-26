package zadanie3;

import zadanie3.algorithm.Algorithm;
import zadanie3.algorithm.Minimax;
import zadanie3.algorithm.MinimaxAlphaBeta;
import zadanie3.evaluation.Evaluation;
import zadanie3.evaluation.NoEvaluation;
import zadanie3.evaluation.PointsEvaluation;
import zadanie3.order.FirstFirstOrder;
import zadanie3.order.OrderOfSearch;
import zadanie3.order.RandomOrder;
import zadanie3.player.ComputerPlayer;
import zadanie3.player.HumanPlayer;
import zadanie3.player.Player;

import java.util.ArrayList;

public class Main {

    private static ArrayList<StrategoMove> nextComputerMove;

    //should be even
    private final static int DEPTH_OF_WHITE_PLAYER = 2;
    private final static int DEPTH_OF_BLACK_PLAYER = 2;

    public static void main(String[] args) {
        //⚪, ⚫, ⃞

        int n = 8;
        StrategoGame game = StrategoGame.create(n);


        Evaluation eval = PointsEvaluation.create(PlayerColor.BLACK);
//        Evaluation eval = NoEvaluation.create(PlayerColor.BLACK);

//        OrderOfSearch order = FirstFirstOrder.create();
        OrderOfSearch order = RandomOrder.create();

        Algorithm alg = Minimax.create(eval, order, 2);
//        Algorithm alg = MinimaxAlphaBeta.create(pointsEvaluation, firstFirst, 2);


        Player firstPlayer = HumanPlayer.create("Lukasz");
        Player secondPlayer = ComputerPlayer.create("Minimax-points-random Bot", alg);

        while (!game.isOver()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            game.printBoardAndScore();

            if (game.getWhoseTurn() == PlayerColor.WHITE) {
                StrategoMove move = firstPlayer.nextMove(game);
                game.makeMoveWithNotification(move.getRow(), move.getColumn());
            } else {
                StrategoMove move = secondPlayer.nextMove(game);
                game.makeMoveWithNotification(move.getRow(), move.getColumn());
            }

        }

        game.printBoardAndScore();
        game.printAtEnd();
    }

}

