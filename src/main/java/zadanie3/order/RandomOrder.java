package zadanie3.order;

import zadanie3.StrategoMove;

import java.util.ArrayList;
import java.util.Random;

public class RandomOrder implements OrderOfSearch {

    private final Random R = new Random();

    private RandomOrder() {
    }

    public static RandomOrder create() {
        return new RandomOrder();
    }

    @Override
    public StrategoMove getNextMove(ArrayList<StrategoMove> moves) {
        int size = moves.size();
        return size > 0 ? moves.get(R.nextInt(size)) : null;
    }

    @Override
    public String getName() {
        return "random";
    }
}
