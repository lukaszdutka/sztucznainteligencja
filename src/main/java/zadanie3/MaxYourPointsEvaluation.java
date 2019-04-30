package zadanie3;

import zadanie3.evaluation.Evaluation;

public class MaxYourPointsEvaluation implements Evaluation {

    private MaxYourPointsEvaluation() {
    }

    public static MaxYourPointsEvaluation create() {
        return new MaxYourPointsEvaluation();
    }

    @Override
    public int evaluate(StrategoGame game, PlayerColor color) {
        return color == PlayerColor.WHITE ? game.getWhiteScore() : game.getBlackScore();
    }

    @Override
    public String getName() {
        return "maxYourPoints";
    }
}
