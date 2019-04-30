package zadanie3.order;

import zadanie3.StrategoGame;

import java.util.ArrayList;

public interface OrderOfSearch {
    ArrayList<StrategoGame> getOrderedMoves(ArrayList<StrategoGame> moves);

    ArrayList<StrategoGame> getAntiOrderedMoves(ArrayList<StrategoGame> moves);

    String getName();

}
