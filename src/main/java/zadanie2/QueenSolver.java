package zadanie2;

class QueenSolver extends Solver {

    private int forwardCheckingRecursionCounter = 0;
    private int backTrackingRecursionCounter = 0;
    private long time;


    QueenSolver(int n) {
        super(n);
    }

    @Override
    void startForwardChecking() {
        time = System.nanoTime();
        forwardCheckingRecursionCounter = 0;

        boolean[][] domain = fillDomain();
        forwardChecking(0, domain);

        printAtEnd(forwardCheckingRecursionCounter, "forwardChecking");
    }

    private void printAtEnd(int counter, String methodName) {
//        System.out.print(counter + "\t");
        System.out.print((System.nanoTime()-time)*1.0/1000000+"\t");
//        System.out.println("Recursion calls: " + counter);
//        System.out.println("QueenSolver: "+methodName+"() ends.\n\n");
    }

    private Result forwardChecking(int currentRow, boolean[][] originalDomains) {
        forwardCheckingRecursionCounter++;

        if (currentRow == n) {
            printSolution(originalDomains);
            return Result.SOLUTION_FOUND;
        }

        boolean[] row = originalDomains[currentRow];
        for (int i = 0; i < row.length; i++) {
            if (queenCantBePlaced(row[i])) {
                continue;
            }

            boolean[][] domains = clone(originalDomains);

            fillRestOfCurrentRowWithCant(domains, currentRow, i);
            updateDomainsBasedOnPlacedQueen(domains, currentRow, i);

            if (atLeastOneDomainIsEmpty(domains)) {
                return Result.INVALID_EMPTY_DOMAIN;
            }

            Result result = forwardChecking(currentRow + 1, domains);

            if (result == Result.INVALID_EMPTY_DOMAIN) {
                continue;
            }
            return result;
        }

        return Result.INVALID_EMPTY_DOMAIN;
    }

    private boolean[][] clone(boolean[][] originalDomains) {
        boolean[][] newDomain = new boolean[originalDomains.length][originalDomains[0].length];
        for (int i = 0; i < originalDomains.length; i++) {
            for (int j = 0; j < originalDomains[i].length; j++) {
                newDomain[i][j] = originalDomains[i][j];
            }
        }

        return newDomain;
    }

    private void updateDomainsBasedOnPlacedQueen(boolean[][] domains, int row, int column) {
        updateDomainsColumn(domains, row, column);
        updateDomainsDiagonalUpDown(domains, row, column);
        updateDomainsDiagonalDownUp(domains, row, column);
    }

    private void updateDomainsDiagonalDownUp(boolean[][] domains, int row, int column) {
        row += 1;
        column -= 1;
        for (; row < domains.length && column >= 0; row++, column--) {
            domains[row][column] = false;
        }
    }

    private void updateDomainsDiagonalUpDown(boolean[][] domains, int row, int column) {
        row += 1;
        column += 1;
        for (; row < domains.length && column < domains.length; row++, column++) {
            domains[row][column] = false;
        }
    }

    private void updateDomainsColumn(boolean[][] domains, int row, int column) {
        for (int i = row + 1; i < domains.length; i++) {
            domains[i][column] = false;
        }
    }

    private void fillRestOfCurrentRowWithCant(boolean[][] domains, int rowIndex, int canPlaceHere) {
        boolean[] row = domains[rowIndex];
        for (int i = 0; i < row.length; i++) {
            row[i] = i == canPlaceHere; //set (all but expected) to false
        }
    }

    private boolean queenCantBePlaced(boolean queenCanBePlaced) {
        return !queenCanBePlaced;
    }

    private boolean atLeastOneDomainIsEmpty(boolean[][] domain) {
        int[] numbersOfTrue = new int[domain.length];
        for (int i = 0; i < numbersOfTrue.length; i++) {
            for (boolean queenCanBePlaced : domain[i]) {
                if (queenCanBePlaced) {
                    numbersOfTrue[i]++;
                }
            }
        }
        for (int number : numbersOfTrue) {
            if (number == 0) {
                return true;
            }
        }
        return false;

    }

    private void printSolution(boolean[][] solution) {
//        System.out.println("---------- SOLUTION ----------");
//        for (boolean[] rows : solution) {
//            for (boolean canBePlacedHere : rows) {
//                if (canBePlacedHere) {
//                    System.out.print("Q ");
//                } else {
//                    System.out.print("□ ");
//                }
//            }
//            System.out.println();
//        }
    }

    private boolean[][] fillDomain() {
        boolean[][] domain = new boolean[n][n];
        for (int i = 0; i < domain.length; i++) {
            for (int j = 0; j < domain[i].length; j++) {
                domain[i][j] = true;
            }
        }
        return domain;
    }

    @Override
    void startBackTracking() {
        time = System.nanoTime();
        backTrackingRecursionCounter= 0;

        boolean[][] solution = new boolean[n][n];
        backTracking(0, solution);

        printAtEnd(backTrackingRecursionCounter, "backTracking");
    }

    private Result backTracking(int currentRow, boolean[][] previousSolution) {
        backTrackingRecursionCounter++;

        if (currentRow == n) {
            printSolution(previousSolution);
            return Result.SOLUTION_FOUND;
        }


        boolean[] row = previousSolution[currentRow];
        for (int i = 0; i < row.length; i++) {
            boolean[][] solution = clone(previousSolution);

            solution[currentRow][i] = true; //put queen

            if (violatesConstraints(solution)) {
                continue;
            }

            Result result = backTracking(currentRow + 1, solution);

            if (result == Result.INVALID_VIOLATES_CONSTRAINTS) {
                continue;
            }
            return result;
        }

        return Result.INVALID_VIOLATES_CONSTRAINTS;
    }

    private boolean violatesConstraints(boolean[][] solution) {
        for(int row = 0; row < solution.length; row++){
            for(int column = 0; column < solution.length; column++){
                if(solution[row][column]){ //hasQueen
                    if(checkConstraints(solution, row, column)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkConstraints(boolean[][] solution, int row, int column) {
        boolean inColumn = checkConstraintColumn(solution, row, column);
        boolean inDiagonalUpDown = checkConstraintDiagonalUpDown(solution, row, column);
        boolean inDiagonalDownUp = checkConstraintDiagonalDownUp(solution, row, column);
        return inColumn || inDiagonalUpDown || inDiagonalDownUp;
    }

    private boolean checkConstraintDiagonalDownUp(boolean[][] solution, int row, int column) {
        row--;
        column++;
        for(; row>=0 && column < solution.length; row--, column++){
            if(solution[row][column]){ //hasQueen
                return true; //violates
            }
        }
        return false;
    }

    private boolean checkConstraintDiagonalUpDown(boolean[][] solution, int row, int column) {
        row--;
        column--;
        for(; row>=0 && column >= 0; row--, column--){
            if(solution[row][column]){ //hasQueen
                return true; //violates
            }
        }
        return false;
    }

    private boolean checkConstraintColumn(boolean[][] solution, int row, int column) {
        row--;
        for(; row >= 0; row--){
            if(solution[row][column]){ //hasQueen
                return true; //violates
            }
        }
        return false;
    }
}
