package zadanie3.evaluation;

import zadanie3.PlayerColor;
import zadanie3.StrategoGame;

public class PointsEvaluation implements Evaluation {

    private PointsEvaluation() {
    }

    public static PointsEvaluation create() {
        return new PointsEvaluation();
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
