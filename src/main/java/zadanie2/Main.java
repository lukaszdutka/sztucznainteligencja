package zadanie2;

public class Main {

    public static void main(String[] args) {

        System.out.println("\tQ-BT\tQ-FC\tLS-BT\tLS-FC");
        for (int n = 4; n <= 15; n++) {

            System.out.print(n+"\t");
            Solver queenSolver = new QueenSolver(n);
            Solver latinSquareSolver = new LatinSquareSolver(n);

//            System.out.println("--- N-QUEEN ---");

            queenSolver.solve(Method.BACK_TRACKING);
            queenSolver.solve(Method.FORWARD_CHECKING);

//            System.out.println("--- LATIN SQUARE ---");

            latinSquareSolver.solve(Method.BACK_TRACKING);
            latinSquareSolver.solve(Method.FORWARD_CHECKING);
            System.out.println();
        }

    }
}