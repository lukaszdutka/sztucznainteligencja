package zadanie3;


import java.util.ArrayList;

public class MiniMaxUtils {

    public static ArrayList<StrategoGame> getNextMoves(StrategoGame game) {
        ArrayList<StrategoGame> games = new ArrayList<>();

        for (int i = 0; i < game.getBoard().length; i++) {
            for (int j = 0; j < game.getBoard().length; j++) {
                if (game.isCellFree(i, j)) {
                    StrategoGame newGame = game.copy();
                    newGame.makeMoveSilent(i, j);
                    games.add(newGame);
                }

            }
        }

        return games;
    }

}
