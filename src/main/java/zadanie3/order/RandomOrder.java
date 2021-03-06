package zadanie3.order;

import zadanie3.StrategoGame;

import java.util.ArrayList;
import java.util.Collections;

public class RandomOrder implements OrderOfSearch {

    private RandomOrder() {
    }

    public static RandomOrder create() {
        return new RandomOrder();
    }

    @Override
    public ArrayList<StrategoGame> getOrderedMoves(ArrayList<StrategoGame> moves) {
        Collections.shuffle(moves);
        return moves;
    }

    @Override
    public ArrayList<StrategoGame> getAntiOrderedMoves(ArrayList<StrategoGame> moves) {
        return getOrderedMoves(moves); //no change needed
    }

    @Override
    public String getName() {
        return "random";
    }
}
