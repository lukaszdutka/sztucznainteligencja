package zadanie3.evaluation;

import zadanie3.PlayerColor;
import zadanie3.StrategoGame;

public class NoEvaluation implements Evaluation {

    private NoEvaluation() {
    }

    public static NoEvaluation create() {
        return new NoEvaluation();
    }

    @Override
    public int evaluate(StrategoGame game, PlayerColor color) {
        return 0;
    }

    @Override
    public String getName() {
        return "noEval";
    }

}
