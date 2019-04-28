package zadanie3.order;

import zadanie3.StrategoGame;
import zadanie3.evaluation.Evaluation;

import java.util.ArrayList;
import java.util.Collections;

public class PointsOrder implements OrderOfSearch {

    private final Evaluation evaluation;

    private PointsOrder(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public static PointsOrder create(Evaluation evaluation) {
        return new PointsOrder(evaluation);
    }

    @Override
    public ArrayList<StrategoGame> getOrderedMoves(ArrayList<StrategoGame> moves) {
        moves.sort((game1, game2) -> {

            if (!canBeComparable(game1, game2)) {
                throw new IllegalArgumentException();
            }

            int gamePointsOne = evaluation.evaluate(game1, game1.getWhoseTurn());
            int gamePointsTwo = evaluation.evaluate(game2, game2.getWhoseTurn());

            return gamePointsOne - gamePointsTwo;
        });
        return moves;
    }

    @Override
    public ArrayList<StrategoGame> getAntiOrderedMoves(ArrayList<StrategoGame> moves) {
        ArrayList<StrategoGame> ordered = getOrderedMoves(moves);
        Collections.reverse(ordered); //antiordered
        return ordered;
    }

    private boolean canBeComparable(StrategoGame game1, StrategoGame game2) {
        return game1.getWhoseTurn() == game2.getWhoseTurn();
    }

    @Override
    public String getName() {
        return "ptsOrder";
    }
}
