package zadanie3.algorithm;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import zadanie3.PlayerColor;
import zadanie3.StrategoGame;
import zadanie3.StrategoMove;
import zadanie3.evaluation.Evaluation;
import zadanie3.order.OrderOfSearch;

public class Minimax implements Algorithm {

    private final int DEPTH;

    private Evaluation evaluation;
    private OrderOfSearch order;

    private Minimax(int depth, Evaluation evaluation, OrderOfSearch order) {
        this.DEPTH = depth;
        this.evaluation = evaluation;
        this.order = order;
    }

    public static Minimax create(Evaluation evaluation, OrderOfSearch order, int depth) {
        if (depth <= 0 || order == null || evaluation == null) {
            throw new IllegalArgumentException("Evaluation or order is null or depth is not positive.");
        }
        return new Minimax(depth, evaluation, order);
    }

    @Override
    public StrategoMove nextMove(StrategoGame game, PlayerColor color) {

//        private static int minimax(StrategoGame game, int depth, boolean maximizingPlayer) {
//            if (depth == 0 || game.isOver()) {
//                return MiniMaxUtils.evaluate(game);
//            }
//
//            if (maximizingPlayer) {
//                int maxEvaluation = Integer.MIN_VALUE;
//
//                for (StrategoGame nextBoard : MiniMaxUtils.getNextMoves(game)) {
//                    int evaluation = minimax(nextBoard, depth - 1, false);
//                    if (evaluation >= maxEvaluation && depth == DEPTH_OF_BLACK_PLAYER) {
//                        if (evaluation == maxEvaluation) {
//                            nextComputerMove.add(nextBoard.getLastMove());
//                        } else {
//                            nextComputerMove = new ArrayList<>();
//                            nextComputerMove.add(nextBoard.getLastMove());
//                        }
//                    }
//                    maxEvaluation = Math.max(maxEvaluation, evaluation);
//                }
//                return maxEvaluation;
//            } else {
//                int minEvaluation = Integer.MAX_VALUE;
//                for (StrategoGame nextBoard : MiniMaxUtils.getNextMoves(game)) {
//                    int evaluation = minimax(nextBoard, depth - 1, true);
//                    if (evaluation <= minEvaluation && depth == DEPTH_OF_WHITE_PLAYER) {
//                        if (evaluation == minEvaluation) {
//                            nextComputerMove.add(nextBoard.getLastMove());
//                        } else {
//                            nextComputerMove = new ArrayList<>();
//                            nextComputerMove.add(nextBoard.getLastMove());
//                        }
//                    }
//                    minEvaluation = Math.min(minEvaluation, evaluation);
//                }
//                return minEvaluation;
//            }
//
//        }

        throw new NotImplementedException();
    }

    @Override
    public String getName() {
        return "Algorithm(alg=minimax, eval=" + evaluation.getName() + ", order=" + order.getName() + ")";
    }
}
