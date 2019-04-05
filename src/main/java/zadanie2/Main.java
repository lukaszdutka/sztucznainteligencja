package zadanie2;

public class Main {

    public static void main(String[] args) {

        int n = 6;

        Solver queenSolver = new QueenSolver(n);
        Solver latinSquareSolver = new LatinSquareSolver(n);

        queenSolver.solve(Method.BACK_TRACKING);
        queenSolver.solve(Method.FORWARD_CHECKING);

        latinSquareSolver.solve(Method.BACK_TRACKING);
        latinSquareSolver.solve(Method.FORWARD_CHECKING);
    }
}