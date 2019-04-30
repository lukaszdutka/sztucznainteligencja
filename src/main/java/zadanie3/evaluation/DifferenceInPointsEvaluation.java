package zadanie3.evaluation;

import zadanie3.PlayerColor;
import zadanie3.StrategoGame;

public class DifferenceInPointsEvaluation implements Evaluation {

    private DifferenceInPointsEvaluation() {
    }

    public static DifferenceInPointsEvaluation create() {
        return new DifferenceInPointsEvaluation();
    }

    @Override
    public int evaluate(StrategoGame game, PlayerColor color) {
        int white = game.getWhiteScore();
        int black = game.getBlackScore();

        if (color == PlayerColor.WHITE) {
            return white - black;
        } else if (color == PlayerColor.BLACK) {
            return black - white;
        }

        throw new IllegalArgumentException("Bad player color");
    }

    @Override
    public String getName() {
        return "pointsDiff";
    }
}
