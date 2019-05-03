package zadanie3;

import zadanie3.algorithm.Algorithm;
import zadanie3.algorithm.Minmax;
import zadanie3.algorithm.MinimaxAlphaBeta;
import zadanie3.evaluation.Evaluation;
import zadanie3.evaluation.DifferenceInPointsEvaluation;
import zadanie3.evaluation.NoEvaluation;
import zadanie3.order.DefaultOrder;
import zadanie3.order.OrderOfSearch;
import zadanie3.order.PointsOrder;
import zadanie3.order.RandomOrder;
import zadanie3.player.ComputerPlayer;
import zadanie3.player.HumanPlayer;
import zadanie3.player.Player;

public class Main {

    public static void main(String[] args) {
        //⚪, ⚫, ⃞

        int n = 7;
        StrategoGame game = StrategoGame.create(n);


        Evaluation eval1 = DifferenceInPointsEvaluation.create();
        Evaluation eval2 = NoEvaluation.create();
        Evaluation eval3 = MaxYourPointsEvaluation.create();
        Evaluation eval4 = DifferenceInPointsSquaredEvaluation.create();

        OrderOfSearch order1 = DefaultOrder.create();
        OrderOfSearch order2 = RandomOrder.create();
        OrderOfSearch order3 = PointsOrder.create(eval1);
        OrderOfSearch order4 = PointsOrder.create(eval4);

        Algorithm minmaxAlphaBeta = MinimaxAlphaBeta.create(eval1, order3, 3);
//        Algorithm minmaxAlphaBeta2 = MinimaxAlphaBeta.create(eval1, order3, 3);
        Algorithm minmaxAlphaBeta2 = MinimaxAlphaBeta.create(eval4, order4, 3);

        //62:34 dla algorytmów takich samych
        //55:41 dla algorytmów 1 i 2

        //56:84 dla white depth 1, black depth 1
        //61:79 dla white depth 2, black depth 1
        //65:75 dla white depth 3, black depth 1

        //130:122 dla minimaxów alpha-beta dla głębokiści 3:3
        //122:130 dla drugiego, dla czarnych. najsuuu jak bedzie dla 2:3?

//        Player firstPlayer = HumanPlayer.create("Lukasz", PlayerColor.WHITE);
        Player firstPlayer = ComputerPlayer.create("Minmax-points-random Bot", PlayerColor.WHITE, minmaxAlphaBeta);
        Player secondPlayer = ComputerPlayer.create("Minmax-points-random Bot", PlayerColor.BLACK, minmaxAlphaBeta2);
//        Player secondPlayer = HumanPlayer.create("Mateusz", PlayerColor.BLACK);

        while (!game.isOver()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            game.printBoardAndScore();

            if (game.getWhoseTurn() == PlayerColor.WHITE) {
                StrategoMove move = firstPlayer.nextMove(game);
                game.makeMoveSilent(move.getRow(), move.getColumn());
            } else {
                StrategoMove move = secondPlayer.nextMove(game);
                game.makeMoveSilent(move.getRow(), move.getColumn());
            }

        }

        game.printBoardAndScore();
        game.printAtEnd();
    }

}

