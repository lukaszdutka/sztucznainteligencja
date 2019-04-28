package zadanie3.order;

import zadanie3.StrategoGame;
import zadanie3.evaluation.Evaluation;

import java.util.ArrayList;

public class DefaultOrder implements OrderOfSearch {

    private final Evaluation evaluation;

    private DefaultOrder(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public static DefaultOrder create(Evaluation evaluation) {
        return new DefaultOrder(evaluation);
    }

    @Override
    public ArrayList<StrategoGame> getOrderedMoves(ArrayList<StrategoGame> moves) {
        return moves;
    }

    @Override
    public ArrayList<StrategoGame> getAntiOrderedMoves(ArrayList<StrategoGame> moves) {
        return getOrderedMoves(moves); //no change needed
    }

    @Override
    public String getName() {
        return "first";
    }
}
