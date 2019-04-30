package zadanie3.player;

import zadanie3.PlayerColor;
import zadanie3.StrategoGame;
import zadanie3.StrategoMove;
import zadanie3.algorithm.Algorithm;

public class ComputerPlayer implements Player {

    private String name;
    private PlayerColor color;

    private Algorithm algorithm;

    private ComputerPlayer(String name, PlayerColor color, Algorithm algorithm) {
        this.name = name;
        this.algorithm = algorithm;
        this.color = color;
    }

    public static ComputerPlayer create(String name, PlayerColor color, Algorithm algorithm) {
        if (algorithm == null) {
            throw new IllegalArgumentException("Algorithm is null");
        }
        return new ComputerPlayer(name, color, algorithm);
    }

    @Override
    public StrategoMove nextMove(StrategoGame game) {
//        System.out.println("Computer is calculating move.");
        return algorithm.nextMove(game, color);
    }

    @Override
    public String getName() {
        return name;
    }
}
