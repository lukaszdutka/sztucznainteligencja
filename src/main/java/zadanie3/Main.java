package zadanie3;

import zadanie3.algorithm.Algorithm;
import zadanie3.algorithm.Minimax;
import zadanie3.algorithm.MinimaxAlphaBeta;
import zadanie3.evaluation.Evaluation;
import zadanie3.evaluation.PointsEvaluation;
import zadanie3.order.OrderOfSearch;
import zadanie3.order.PointsOrder;
import zadanie3.player.ComputerPlayer;
import zadanie3.player.Player;

public class Main {

    public static void main(String[] args) {
        //⚪, ⚫, ⃞

        int n = 8;
        StrategoGame game = StrategoGame.create(n);


        Evaluation eval = PointsEvaluation.create();
//        Evaluation eval = NoEvaluation.create();

//        OrderOfSearch order = DefaultOrder.create();
//        OrderOfSearch order = RandomOrder.create();
        OrderOfSearch order = PointsOrder.create(eval);

//        Algorithm alg = Minimax.create(eval, order, 3);
//        Algorithm alg2 = Minimax.create(eval, order, 2);
        Algorithm alg = MinimaxAlphaBeta.create(eval, order, 2);
        Algorithm alg2 = MinimaxAlphaBeta.create(eval, order, 3);

        //62:34 dla algorytmów takich samych
        //55:41 dla algorytmów 1 i 2

        //56:84 dla white depth 1, black depth 1
        //61:79 dla white depth 2, black depth 1
        //65:75 dla white depth 3, black depth 1


        //130:122 dla minimaxów alpha-beta dla głębokiści 3:3
        //122:130 dla drugiego, dla czarnych. najsuuu jak bedzie dla 2:3?

//        Player firstPlayer = HumanPlayer.create("Lukasz", PlayerColor.WHITE);
        Player firstPlayer = ComputerPlayer.create("Minimax-points-random Bot", PlayerColor.WHITE, alg);
        Player secondPlayer = ComputerPlayer.create("Minimax-points-random Bot", PlayerColor.BLACK, alg2);
//        Player secondPlayer = HumanPlayer.create("Mateusz", PlayerColor.BLACK);

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

