package zadanie3.order;

import zadanie3.StrategoGame;

import java.util.ArrayList;

public class DefaultOrder implements OrderOfSearch {


    private DefaultOrder() {
    }

    public static DefaultOrder create() {
        return new DefaultOrder();
    }

    @Override
    public ArrayList<StrategoGame> getOrderedMoves(ArrayList<StrategoGame> moves) {
        return moves;
    }

    @Override
    public ArrayList<StrategoGame> getAntiOrderedMoves(ArrayList<StrategoGame> moves) {
        return getOrderedMoves(moves); //no change needed
    }

    @Override
    public String getName() {
        return "first";
    }
}
