package zadanie3.player;

import zadanie3.StrategoGame;
import zadanie3.StrategoMove;
import zadanie3.algorithm.Algorithm;

public class ComputerPlayer implements Player {

    private String name;
    private Algorithm algorithm;


    private ComputerPlayer(String name, Algorithm algorithm) {
        this.name = name;
        this.algorithm = algorithm;
    }

    public static ComputerPlayer create(String name, Algorithm algorithm) {
        if (algorithm == null) {
            throw new IllegalArgumentException("Algorithm is null");
        }
        return new ComputerPlayer(name, algorithm);
    }

    @Override
    public StrategoMove nextMove(StrategoGame game) {
        System.out.println("Computer is calculating move.");
        return algorithm.nextMove(game);
    }

    @Override
    public String getName() {
        return name;
    }
}
