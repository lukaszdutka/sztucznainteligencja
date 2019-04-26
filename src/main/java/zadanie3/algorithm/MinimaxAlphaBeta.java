package zadanie3.algorithm;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import zadanie3.StrategoGame;
import zadanie3.StrategoMove;
import zadanie3.evaluation.Evaluation;
import zadanie3.order.OrderOfSearch;

public class MinimaxAlphaBeta implements Algorithm {

    private final int DEPTH;

    private Evaluation evaluation;
    private OrderOfSearch order;

    private MinimaxAlphaBeta(int depth, Evaluation evaluation, OrderOfSearch order) {
        this.DEPTH = depth;
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
    public StrategoMove nextMove(StrategoGame game) {
        throw new NotImplementedException();
    }

    @Override
    public String getName() {
        return "Algorithm(alg=alphabeta, eval=" + evaluation.getName() + ", order=" + order.getName() + ")";
    }
}
