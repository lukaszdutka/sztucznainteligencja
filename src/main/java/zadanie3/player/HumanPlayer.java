package zadanie3.player;

import zadanie3.PlayerColor;
import zadanie3.StrategoGame;
import zadanie3.StrategoMove;

import java.util.Scanner;

public class HumanPlayer implements Player {

    private static final Scanner s = new Scanner(System.in);

    private String name;
    private PlayerColor color;

    private HumanPlayer(String name, PlayerColor color) {
        this.name = name;
        this.color = color;
    }

    public static HumanPlayer create(String name, PlayerColor color) {
        return new HumanPlayer(name, color);
    }


    @Override
    public StrategoMove nextMove(StrategoGame game) {
        System.out.println("Where do you want to put next piece? row and column.");

        int row = s.nextInt();
        int column = s.nextInt();

        return new StrategoMove(row, column);
    }

    @Override
    public String getName() {
        return name;
    }
}
