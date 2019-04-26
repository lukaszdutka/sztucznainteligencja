package zadanie3.evaluation;

import zadanie3.PlayerColor;
import zadanie3.StrategoGame;

public class PointsEvaluation implements Evaluation {

    private PlayerColor playerColor;

    private PointsEvaluation(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public static PointsEvaluation create(PlayerColor playerColor) {
        if (playerColor != PlayerColor.BLACK && playerColor != PlayerColor.WHITE) {
            throw new IllegalArgumentException("PlayerColor should be black or white");
        }
        return new PointsEvaluation(playerColor);
    }

    @Override
    public int evaluate(StrategoGame game) {
        int evaluation = game.getWhiteScore() - game.getBlackScore();

        if (playerColor == PlayerColor.WHITE) {
            return evaluation;
        } else {
            return -evaluation;
        }
    }

    @Override
    public String getName() {
        return "pointsDiff";
    }
}
