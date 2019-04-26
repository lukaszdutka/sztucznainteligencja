package zadanie3.order;

import zadanie3.StrategoMove;

import java.util.ArrayList;

public interface OrderOfSearch {
    StrategoMove getNextMove(ArrayList<StrategoMove> moves);

    String getName();
}
