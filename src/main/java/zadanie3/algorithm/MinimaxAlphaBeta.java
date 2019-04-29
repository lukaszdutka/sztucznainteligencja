package zadanie3.algorithm;

import zadanie3.MiniMaxUtils;
import zadanie3.PlayerColor;
import zadanie3.StrategoGame;
import zadanie3.StrategoMove;
import zadanie3.evaluation.Evaluation;
import zadanie3.order.OrderOfSearch;

import java.util.ArrayList;

public class MinimaxAlphaBeta implements Algorithm {

    private final int DEPTH;

    private Evaluation evaluation;
    private OrderOfSearch order;

    private int[] statistics;

    private MinimaxAlphaBeta(int depth, Evaluation evaluation, OrderOfSearch order) {
        this.DEPTH = 2 * depth;
        this.evaluation = evaluation;
        this.order = order;
    }

    public static MinimaxAlphaBeta create(Evaluation evaluation, OrderOfSearch order, int depth) {
        if (depth <= 0 || order == null || evaluation == null) {
            throw new IllegalArgumentException("Evaluation or order is null or depth is not positive.");
        }
        return new MinimaxAlphaBeta(depth, evaluation, order);
    }

    @Override
    public StrategoMove nextMove(StrategoGame game, PlayerColor color) {
        return minimax(game, DEPTH);
    }

    private StrategoMove minimax(StrategoGame game, int depth) {
        statistics = initializeStatistics();

        ArrayList<StrategoGame> games = new ArrayList<>();

        minimaxAlphaBeta(game, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true, games);

        printStatistics(statistics);
        return games.get(0).getLastMove();
    }

    private void printStatistics(int[] statistics) {
        System.out.println("Depth\tnumber of cuts");
        for (int i = 0; i < statistics.length; i++) {
            System.out.println(i + "\t" + statistics[i]);
        }
    }

    private int[] initializeStatistics() {
        return new int[DEPTH + 1];
    }


    private int minimaxAlphaBeta(StrategoGame game, int depth, int alpha, int beta, boolean maximizingPlayer, ArrayList<StrategoGame> bestGames) {
        if (depth == 0 || game.isOver()) {
            return evaluation.evaluate(game, game.getWhoseTurn());
        }
        if (maximizingPlayer) {
            int maxEvaluation = Integer.MIN_VALUE;

            ArrayList<StrategoGame> moves = MiniMaxUtils.getNextMoves(game);
            moves = order.getOrderedMoves(moves);

            for (StrategoGame nextBoard : moves) {

                int evaluation = minimaxAlphaBeta(nextBoard, depth - 1, alpha, beta, false, null);
                //update possible games list
                if (depth == DEPTH && evaluation >= maxEvaluation) {
                    if (evaluation > maxEvaluation) {
                        bestGames.clear();
                    }
                    bestGames.add(nextBoard);
                }

                maxEvaluation = Math.max(maxEvaluation, evaluation);
                alpha = Math.max(alpha, evaluation);

                if (beta <= alpha) {
                    statistics[depth]++;
                    break;
                }

            }
            return maxEvaluation;

        } else {
            int minEvaluation = Integer.MAX_VALUE;
            ArrayList<StrategoGame> moves = MiniMaxUtils.getNextMoves(game);
            moves = order.getAntiOrderedMoves(moves);

            for (StrategoGame nextBoard : moves) {
                int evaluation = minimaxAlphaBeta(nextBoard, depth - 1, alpha, beta, true, null);

                if (depth == DEPTH && evaluation <= minEvaluation) {
                    if (evaluation < minEvaluation) {
                        bestGames.clear();
                    }
                    bestGames.add(nextBoard);
                }
                minEvaluation = Math.min(minEvaluation, evaluation);
                beta = Math.min(beta, evaluation);
                if (beta <= alpha) {
                    statistics[depth]++;
                    break;
                }
            }
            return minEvaluation;

        }

    }

    @Override
    public String getName() {
        return "Algorithm(alg=alphabeta, eval=" + evaluation.getName() + ", order=" + order.getName() + ")";
    }
}
