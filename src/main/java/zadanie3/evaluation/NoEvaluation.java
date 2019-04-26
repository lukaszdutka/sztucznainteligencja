package zadanie3.evaluation;

import zadanie3.PlayerColor;
import zadanie3.StrategoGame;

public class NoEvaluation implements Evaluation {

    private PlayerColor playerColor;

    private NoEvaluation(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public static NoEvaluation create(PlayerColor playerColor) {
        return new NoEvaluation(playerColor);
    }

    @Override
    public int evaluate(StrategoGame game) {
        return 0;
    }

    @Override
    public String getName() {
        return "noEval";
    }

}
