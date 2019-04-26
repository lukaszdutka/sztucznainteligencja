package zadanie3.algorithm;

import zadanie3.PlayerColor;
import zadanie3.StrategoGame;
import zadanie3.StrategoMove;

public interface Algorithm {
    StrategoMove nextMove(StrategoGame game, PlayerColor color);

    String getName();
}
