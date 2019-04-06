package zadanie2;

class LatinSquareSolver extends Solver {

    LatinSquareSolver(int n) {
        super(n);
    }

    private int forwardCheckingRecursionCounter = 0;
    private int backTrackingRecursionCounter = 0;

    @Override
    void startForwardChecking() {
        forwardCheckingRecursionCounter = 0;

        boolean[][][] domain = fillDomain();
        forwardChecking(0, 0, domain);

        printAtEnd(forwardCheckingRecursionCounter, "forwardChecking");
    }

    private void printAtEnd(int counter, String methodName) {
        System.out.println("Recursion calls: " + counter);
        System.out.println("\nLatinSquare: " + methodName + "() ends.\n\n");
    }

    private Result forwardChecking(int currentRow, int currentColumn, boolean[][][] originalDomains) {
        forwardCheckingRecursionCounter++;

        if (currentRow == n) {
            printSolution(originalDomains);
            return Result.SOLUTION_FOUND;
        }

        boolean[] currentElement = originalDomains[currentRow][currentColumn];
        for (int i = 0; i < currentElement.length; i++) {
            if (numberCantBePlaced(currentElement[i])) {
                continue;
            }

            boolean[][][] domains = clone(originalDomains);

            updateDomains(domains, currentRow, currentColumn, i);

            if (atLeastOneDomainIsEmpty(domains)) {
                return Result.INVALID_EMPTY_DOMAIN;
            }

            if (currentColumn == n - 1) {
                currentColumn = -1;
                currentRow++;
            }

            Result result = forwardChecking(currentRow, currentColumn + 1, domains);

            if (result == Result.INVALID_EMPTY_DOMAIN) {
                continue;
            }
            return result;
        }

        return Result.INVALID_EMPTY_DOMAIN;
    }

    private void updateDomains(boolean[][][] domains, int currentRow, int currentColumn, int number) {
        updateRow(domains, currentRow, currentColumn, number);
        updateColumn(domains, currentRow, currentColumn, number);
        updateThis(domains, currentRow, currentColumn, number);
    }

    private void updateThis(boolean[][][] domains, int currentRow, int currentColumn, int number) {
        for(int i = 0; i < domains.length; i++){
            if(i != number){
                domains[currentRow][currentColumn][i] = false;
            }
        }
    }

    private void updateColumn(boolean[][][] domains, int currentRow, int currentColumn, int number) {
        for (int i = 0; i < domains.length; i++) {
            if (i != currentRow) {
                domains[i][currentColumn][number] = false;
            }
        }
    }

    private void updateRow(boolean[][][] domains, int currentRow, int currentColumn, int number) {
        for (int i = 0; i < domains.length; i++) {
            if (i != currentColumn) {
                domains[currentRow][i][number] = false;
            }
        }
    }

    private boolean numberCantBePlaced(boolean numberCanBePlaced) {
        return !numberCanBePlaced;
    }

    private boolean[][][] clone(boolean[][][] originalDomains) {
        boolean[][][] newDomain = new boolean[originalDomains.length][originalDomains[0].length][originalDomains[0][0].length];
        for (int i = 0; i < originalDomains.length; i++) {
            for (int j = 0; j < originalDomains[i].length; j++) {
                for (int k = 0; k < originalDomains[i][j].length; k++) {
                    newDomain[i][j][k] = originalDomains[i][j][k];
                }
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

    private boolean atLeastOneDomainIsEmpty(boolean[][][] domain) {
        for (boolean[][] aDomain : domain) {
            for (boolean[] oneCell : aDomain) {
                if (isFullOfFalse(oneCell)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isFullOfFalse(boolean[] booleans) {
        for (boolean number : booleans) {
            if (number) {
                return false;
            }
        }
        return true;
    }

    private void printSolution(boolean[][][] solution) {
        System.out.println("---------- SOLUTION ----------");
        for (boolean[][] rows : solution) {
            for (boolean[] domainOfCell : rows) {
                for (int i = 0; i < domainOfCell.length; i++) {
                    if (domainOfCell[i]) {
                        System.out.print(i + " ");
                        break;
                    }
                }
            }
            System.out.println();
        }
    }

    private boolean[][][] fillDomain() {
        boolean[][][] domain = new boolean[n][n][n];
        for (int i = 0; i < domain.length; i++) {
            for (int j = 0; j < domain[i].length; j++) {
                for (int k = 0; k < domain[i][j].length; k++) {
                    domain[i][j][k] = true;
                }
            }
        }
        return domain;
    }
//
    @Override
    void startBackTracking() {
//        backTrackingRecursionCounter = 0;
//
//        boolean[][] solution = new boolean[n][n];
//        backTracking(0, solution);
//
//        printAtEnd(backTrackingRecursionCounter, "backTracking");
    }
//
//    private Result backTracking(int currentRow, boolean[][] previousSolution) {
//        backTrackingRecursionCounter++;
//
//        if (currentRow == n) {
//            printSolution(previousSolution);
//            return Result.SOLUTION_FOUND;
//        }
//
//
//        boolean[] row = previousSolution[currentRow];
//        for (int i = 0; i < row.length; i++) {
//            boolean[][] solution = clone(previousSolution);
//
//            solution[currentRow][i] = true; //put queen
//
//            if (violatesConstraints(solution)) {
//                continue;
//            }
//
//            Result result = backTracking(currentRow + 1, solution);
//
//            if (result == Result.INVALID_VIOLATES_CONSTRAINTS) {
//                continue;
//            }
//            return result;
//        }
//
//        return Result.INVALID_VIOLATES_CONSTRAINTS;
//    }

    private boolean violatesConstraints(boolean[][] solution) {
        for (int row = 0; row < solution.length; row++) {
            for (int column = 0; column < solution.length; column++) {
                if (solution[row][column]) { //hasQueen
                    if (checkConstraints(solution, row, column)) {
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
        for (; row >= 0 && column < solution.length; row--, column++) {
            if (solution[row][column]) { //hasQueen
                return true; //violates
            }
        }
        return false;
    }

    private boolean checkConstraintDiagonalUpDown(boolean[][] solution, int row, int column) {
        row--;
        column--;
        for (; row >= 0 && column >= 0; row--, column--) {
            if (solution[row][column]) { //hasQueen
                return true; //violates
            }
        }
        return false;
    }

    private boolean checkConstraintColumn(boolean[][] solution, int row, int column) {
        row--;
        for (; row >= 0; row--) {
            if (solution[row][column]) { //hasQueen
                return true; //violates
            }
        }
        return false;
    }
}
