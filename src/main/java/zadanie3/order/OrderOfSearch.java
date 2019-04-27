package zadanie3.order;

import zadanie3.StrategoGame;

import java.util.ArrayList;

public interface OrderOfSearch {
    // TODO: 27/04/2019 is there a sense to make copy of array or we can play on referenced array?
    ArrayList<StrategoGame> getOrderedMoves(ArrayList<StrategoGame> moves);

    String getName();
}
