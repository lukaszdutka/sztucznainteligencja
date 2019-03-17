package zadanie1;

import java.math.BigDecimal;
import java.util.ArrayList;

import static zadanie1.Configuration.*;


/**
 * author of boilerplate: Łukasz Dutka (share only readConfiguration(), toItem(), toNode() methods and Node, Item, Configuration classes)
 * author of algorithm: Łukasz Dutka
 */

public class Main {

    public static void main(String[] args) {
        new Configuration("hard_1.ttp");
        System.out.println(Configuration.FILE_NAME);

        Population pop = Population.createRandomPopulation(Configuration.NODES);
        Evaluation previous = pop.evaluatePopulation(1);

        System.out.println("id najgorszy sredni najlepszy");
        System.out.println(previous);

        for (int i = 2; i <= Configuration.ITERATIONS; i++) {
            pop = Population.createNextPopulation(pop);

            Evaluation newEvaluation = pop.evaluatePopulation(i);
            newEvaluation.update(previous);
            System.out.println(newEvaluation);
            previous = newEvaluation;
        }

        System.out.println();

//            Configuration conf = new Configuration("medium_2.ttp");
//
//            Population pop = Population.createRandomPopulation(NODES);
//            Evaluation previous = pop.evaluatePopulation(pop, 1);
//            System.out.println(Configuration.FILE_NAME);
//            System.out.println("id najgorszy sredni najlepszy");
//            System.out.println(previous);
//
//            for (int i = 2; i <= ITERATIONS; i++) {
//                pop = Population.createNextPopulation(pop);
//                Evaluation newEvaluation = pop.evaluatePopulation(pop, i);
//                newEvaluation.update(previous);
//                System.out.println(newEvaluation);
//                previous = newEvaluation;
//            }
//        System.out.println();
//        System.out.println();
//        System.out.println();
//
//            Configuration conf = new Configuration("hard_2.ttp");
//
//            Population pop = Population.createRandomPopulation(NODES);
//            Evaluation previous = pop.evaluatePopulation(pop, 1);
//            System.out.println(Configuration.FILE_NAME);
//            System.out.println("id najgorszy sredni najlepszy");
//            System.out.println(previous);
//
//            for (int i = 2; i <= ITERATIONS; i++) {
//                pop = Population.createNextPopulation(pop);
//                Evaluation newEvaluation = pop.evaluatePopulation(pop, i);
//                newEvaluation.update(previous);
//                System.out.println(newEvaluation);
//                previous = newEvaluation;
//            }

//
//        String[] files = new String[]{"trivial_0.ttp", "trivial_1.ttp",
//                "easy_0.ttp", "easy_1.ttp", "easy_2.ttp", "easy_3.ttp", "easy_4.ttp",
//                "medium_0.ttp", "medium_1.ttp", "medium_2.ttp", "medium_3.ttp", "medium_4.ttp",
//                "hard_0.ttp", "hard_1.ttp", "hard_2.ttp", "hard_3.ttp", "hard_4.ttp"
//        };
//
//        System.out.println("Problem, best, avg, deviation");
//        for (String fileName : files) {
//            Configuration config = new Configuration(fileName);
//
//            ArrayList<BigDecimal> bests = new ArrayList<>();
//
//            System.out.print(fileName + "\t");
//            for (int j = 0; j < 10; j++) {
//                Population pop = Population.createRandomPopulation(NODES);
//                Evaluation evaluation = pop.evaluatePopulation(pop, 1);
//
//                for (int i = 2; i < ITERATIONS; i++) {
//                    pop = Population.createNextPopulation(pop);
//                    evaluation.update(pop.evaluatePopulation(pop, i));
//                }
//
//                bests.add(evaluation.getBest());
//            }
//
//            BigDecimal best = getBest(bests);
//            BigDecimal average = getAverage(bests);
//            BigDecimal deviation = getDeviation(bests, average);
//
//            System.out.println(best.toBigInteger() + "\t" + average.toBigInteger() + "\t" + deviation.toBigInteger());
//        }

    }

    private static BigDecimal getDeviation(ArrayList<BigDecimal> bests, BigDecimal average) {
        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal number : bests) {
            sum = sum.add(differenceSquared(number, average));
        }
        return new BigDecimal("" + Math.sqrt(sum.divide(BigDecimal.TEN, MC).doubleValue()));
    }

    private static BigDecimal differenceSquared(BigDecimal number, BigDecimal average) {
        return (number.subtract(average)).pow(2);
    }


    private static BigDecimal getAverage(ArrayList<BigDecimal> bests) {
        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal temp : bests) {
            sum = sum.add(temp);
        }
        return sum.divide(BigDecimal.TEN, MC);
    }

    private static BigDecimal getBest(ArrayList<BigDecimal> bests) {
        BigDecimal best = bests.get(0);
        for (BigDecimal temp : bests) {
            if (temp.compareTo(best) > 0) {
                best = temp;
            }
        }
        return best;
    }
}
