package zadanie3.player;

import zadanie3.StrategoGame;
import zadanie3.StrategoMove;

public interface Player {
    StrategoMove nextMove(StrategoGame game);

    String getName();
}
