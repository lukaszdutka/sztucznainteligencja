package zadanie3.evaluation;

import zadanie3.StrategoGame;

public interface Evaluation {
    int evaluate(StrategoGame game);

    String getName();
}
