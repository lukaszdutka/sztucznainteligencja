package zadanie3;


import java.util.ArrayList;
import java.util.Random;

public class MiniMaxUtils {

    public static int evaluate(StrategoGame game) {
        return game.getBlackScore() - game.getWhiteScore();
    }

    public static ArrayList<StrategoGame> getNextMoves(StrategoGame game) {
        ArrayList<StrategoGame> games = new ArrayList<>();

        for (int i = 0; i < game.getBoard().length; i++){
            for(int j = 0; j < game.getBoard().length; j++){
                if(game.isCellFree(i, j)){
                    StrategoGame newGame = game.copy();
                    newGame.makeMoveSilent(i, j);
                    games.add(newGame);
                }

            }
        }

        return games;
    }

    public static StrategoMove getRandomMove(ArrayList<StrategoMove> nextComputerMove) {
        if(nextComputerMove == null || nextComputerMove.size() == 0){
            throw new IllegalArgumentException();
        }
        if(nextComputerMove.size() == 1){
            return nextComputerMove.get(0);
        }
        Random r = new Random();
        return nextComputerMove.get(r.nextInt(nextComputerMove.size()));
    }
}
