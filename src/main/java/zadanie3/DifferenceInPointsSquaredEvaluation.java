package zadanie3;

import zadanie3.evaluation.Evaluation;

public class DifferenceInPointsSquaredEvaluation implements Evaluation {

    private DifferenceInPointsSquaredEvaluation() {
    }

    public static DifferenceInPointsSquaredEvaluation create() {
        return new DifferenceInPointsSquaredEvaluation();
    }

    @Override
    public int evaluate(StrategoGame game, PlayerColor color) {
        int white = game.getWhiteScore();
        white *= white;

        int black = game.getBlackScore();
        black *= black;

        switch (color) {
            case WHITE:
                return white - black;
            case BLACK:
                return black - white;
            default:
                throw new IllegalArgumentException("Bad player color");
        }
    }

    @Override
    public String getName() {
        return "pointsDiff";
    }
}
