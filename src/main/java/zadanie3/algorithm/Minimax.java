package zadanie3.algorithm;

import zadanie3.MiniMaxUtils;
import zadanie3.PlayerColor;
import zadanie3.StrategoGame;
import zadanie3.StrategoMove;
import zadanie3.evaluation.Evaluation;
import zadanie3.order.OrderOfSearch;

import java.util.ArrayList;

public class Minimax implements Algorithm {

    private final int DEPTH;

    private Evaluation evaluation;
    private OrderOfSearch order;

    private Minimax(int depth, Evaluation evaluation, OrderOfSearch order) {
        this.DEPTH = 2 * depth;
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
        return minimax(game, DEPTH);
    }

    private StrategoMove minimax(StrategoGame game, int depth) {
        long before = System.currentTimeMillis();
        ArrayList<StrategoGame> games = new ArrayList<>();

        minimaxRec(game, depth, true, games);

        long after = System.currentTimeMillis();
        System.out.println((after - before));
        return games.get(0).getLastMove();
    }

    private int minimaxRec(StrategoGame game, int depth, boolean maximizingPlayer, ArrayList<StrategoGame> bestGames) {
        if (depth == 0 || game.isOver()) {
            return evaluation.evaluate(game, game.getWhoseTurn());
        }

        if (maximizingPlayer) {

            int maxEvaluation = Integer.MIN_VALUE;
            ArrayList<StrategoGame> moves = MiniMaxUtils.getNextMoves(game);
            moves = order.getOrderedMoves(moves);

            for (StrategoGame nextBoard : moves) {

                int evaluation = minimaxRec(nextBoard, depth - 1, false, null);
                //update possible games list
                if (depth == DEPTH && evaluation >= maxEvaluation) {
                    if (evaluation > maxEvaluation) {
                        bestGames.clear();
                    }
                    bestGames.add(nextBoard);
                }
                maxEvaluation = Math.max(maxEvaluation, evaluation);
            }
            return maxEvaluation;

        } else {

            int minEvaluation = Integer.MAX_VALUE;

            ArrayList<StrategoGame> moves = MiniMaxUtils.getNextMoves(game);
            moves = order.getAntiOrderedMoves(moves);

            for (StrategoGame nextBoard : moves) {
                int evaluation = minimaxRec(nextBoard, depth - 1, true, null);

                if (depth == DEPTH && evaluation <= minEvaluation) {
                    if (evaluation < minEvaluation) {
                        bestGames.clear();
                    }
                    bestGames.add(nextBoard);
                }
                minEvaluation = Math.min(minEvaluation, evaluation);
            }
            return minEvaluation;
        }

    }


    @Override
    public String getName() {
        return "Algorithm(alg=minimax, eval=" + evaluation.getName() + ", order=" + order.getName() + ")";
    }
}
