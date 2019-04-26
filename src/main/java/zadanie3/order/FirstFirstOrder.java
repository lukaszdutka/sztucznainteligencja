package zadanie3.order;

import zadanie3.StrategoMove;

import java.util.ArrayList;

public class FirstFirstOrder implements OrderOfSearch {

    private FirstFirstOrder() {
    }

    public static FirstFirstOrder create() {
        return new FirstFirstOrder();
    }

    @Override
    public StrategoMove getNextMove(ArrayList<StrategoMove> moves) {
        return moves.size() > 0 ? moves.get(0) : null;
    }

    @Override
    public String getName() {
        return "first";
    }
}
