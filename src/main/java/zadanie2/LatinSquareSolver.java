package zadanie2;

class LatinSquareSolver extends Solver {

    LatinSquareSolver(int n) {
        super(n);
    }

    private int forwardCheckingRecursionCounter = 0;
    private int backTrackingRecursionCounter = 0;
    private long time;

    @Override
    void startForwardChecking() {
        time = System.nanoTime();
        forwardCheckingRecursionCounter = 0;

        boolean[][][] domain = fillDomain();
        forwardChecking(0, 0, domain);

        printAtEnd(forwardCheckingRecursionCounter, "forwardChecking");
    }

    private void printAtEnd(int counter, String methodName) {
//        System.out.print(counter + "\t");
        System.out.print((System.nanoTime()-time)*1.0/1000000+"\t");
//        System.out.println("Recursion calls: " + counter);
//        System.out.println("\nLatinSquare: " + methodName + "() ends.\n\n");
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
        for (int i = 0; i < domains.length; i++) {
            if (i != number) {
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
//        System.out.println("---------- SOLUTION ----------");
//        for (boolean[][] rows : solution) {
//            for (boolean[] domainOfCell : rows) {
//                for (int i = 0; i < domainOfCell.length; i++) {
//                    if (domainOfCell[i]) {
//                        System.out.print(i + " ");
//                        break;
//                    }
//                }
//            }
//            System.out.println();
//        }
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
        time = System.nanoTime();

        backTrackingRecursionCounter = 0;

        boolean[][][] solution = new boolean[n][n][n];
        backTracking(0, 0, solution);

        printAtEnd(backTrackingRecursionCounter, "backTracking");
    }

    private Result backTracking(int currentRow, int currentColumn, boolean[][][] previousSolution) {
        backTrackingRecursionCounter++;

        if (currentRow == n) {
            printSolution(previousSolution);
            return Result.SOLUTION_FOUND;
        }


        boolean[] cell = previousSolution[currentRow][currentColumn];

        for (int i = 0; i < cell.length; i++) {
            boolean[][][] solution = clone(previousSolution);

            solution[currentRow][currentColumn][i] = true; //put i number

            if (violatesConstraints(solution)) {
                continue;
            }

            if (currentColumn == n - 1) {
                currentColumn = -1;
                currentRow++;
            }

            Result result = backTracking(currentRow, currentColumn + 1, solution);

            if (result == Result.INVALID_VIOLATES_CONSTRAINTS) {
                continue;
            }
            return result;
        }

        return Result.INVALID_VIOLATES_CONSTRAINTS;
    }

    private boolean violatesConstraints(boolean[][][] solution) {
        int[][] flatSolution = getFlatSolution(solution);
        return violatesRowConstraint(flatSolution) || violatesColumnConstraint(flatSolution);
    }

    private boolean violatesColumnConstraint(int[][] flatSolution) {
        for (int i = 0; i < flatSolution.length; i++) {
            if (columnIsBad(flatSolution, i)) {
                return true;
            }
        }
        return false;
    }

    private boolean columnIsBad(int[][] flatSolution, int column) {
        int[] occurences = new int[n];
        for (int[] row : flatSolution) {
            int number = row[column];
            if (number == -1) {
                continue;
            }
            occurences[number]++;
        }

        for (int occurrence : occurences) {
            if (occurrence > 1) {
                return true;
            }
        }
        return false;
    }

    private boolean violatesRowConstraint(int[][] flatSolution) {
        for (int i = 0; i < flatSolution.length; i++) {
            int[] row = flatSolution[i];
            if (rowIsBad(row)) {
                return true;
            }
        }
        return false;
    }

    private boolean rowIsBad(int[] row) {
        int[] occurences = new int[n];
        for (int number : row) {
            if (number == -1) {
                continue;
            }
            occurences[number]++;
        }

        for (int occurrence : occurences) {
            if (occurrence > 1) {
                return true;
            }
        }
        return false;
    }

    private int[][] getFlatSolution(boolean[][][] solution) {
        int[][] numbers = new int[n][n];
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                numbers[i][j] = getFirstOf(solution[i][j]);
            }
        }
        return numbers;
    }

    private int getFirstOf(boolean[] booleans) {
        for (int i = 0; i < booleans.length; i++) {
            if (booleans[i]) {
                return i;
            }
        }
        return -1;
    }


}
