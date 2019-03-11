package zadanie1;

import java.math.BigDecimal;
import java.util.*;

import static zadanie1.Configuration.CROSSOVER_CHANCE;
import static zadanie1.Configuration.MUTATION_CHANCE;

public class Individuals {

    private static final int TOURNAMENT_SIZE = 5;
    private static final Random R = new Random();

    private static Individual cross(Individual father, Individual mother) {
        List<Node> fatherList = father.getNodes();
        List<Node> motherList = mother.getNodes();

        int maxCut = fatherList.size();

        int cut = R.nextInt(maxCut);

        List<Node> childList = new ArrayList<>();
        for (int i = 0; i < fatherList.size(); i++) {
            if (i < cut) {
                Node node = fatherList.get(i);
                childList.add(node);
            } else {
                Node node = motherList.get(i);
                if (!childList.contains(node)) {
                    childList.add(motherList.get(i));
                }
            }
        }

        if (childList.size() < fatherList.size()) {
            for (Node n : motherList) {
                if (!childList.contains(n)) {
                    childList.add(n);
                }
            }
        }

        return new Individual(childList);
    }

    private static Individual mutate(Individual individual) {
        Node[] nodes = new Node[individual.getNodes().size()];
        for (int i = 0; i < individual.getNodes().size(); i++) {
            nodes[i] = individual.getNodes().get(i);
        }

        int reverseIndex1 = R.nextInt(nodes.length);
        int reverseIndex2 = R.nextInt(nodes.length);

        int min = Math.min(reverseIndex1, reverseIndex2);
        int max = Math.max(reverseIndex1, reverseIndex2);

        for (int i = min; i < (min + max) / 2; i++) {
            int j = max - i;
            Node node = nodes[i];
            Node node2 = nodes[j];

            nodes[i] = node2;
            nodes[j] = node;
        }

        individual.setNodes(Arrays.asList(nodes));
        return individual;
    }

    static Individual selectByTournament(Population pop) {
        List<Individual> population = pop.getPopulation();
        ArrayList<Individual> tournament = new ArrayList<>();
        while (tournament.size() < TOURNAMENT_SIZE) {
            tournament.add(population.get(R.nextInt(population.size())));
        }

        return tournament.stream().max(Comparator.comparing(Individual::getEvaluation)).get();
    }

//    public static Individual selectByRoulette(ArrayList<Individual> population) {
//        BigDecimal sum = BigDecimal.ZERO;
//        for (Individual i : population) {
//            sum = sum.add(i.getEvaluation());
//        }
//        BigDecimal random = sum.multiply(new BigDecimal("" + R.nextDouble()));
//
//        BigDecimal partialSum = BigDecimal.ZERO;
//        for (Individual i : population) {
//            partialSum = partialSum.add(i.getEvaluation());
//            if (partialSum.compareTo(random) > 0) {
//                return i;
//            }
//        }
//        return null;
//    }

    static Individual[] getChildrenFor(Individual father, Individual mother) {
        Individual[] children = new Individual[2];
        if (crossover()) {
            children[0] = Individuals.cross(father, mother);
            children[1] = Individuals.cross(father, mother);
            if (mutate()) {
                Individuals.mutate(children[0]);
                Individuals.mutate(children[1]);
            }
        } else {
            children[0] = father;
            children[1] = mother;
        }
        return children;
    }


    private static boolean crossover() {
        return new BigDecimal("" + R.nextDouble()).compareTo(CROSSOVER_CHANCE) < 0;
    }

    private static boolean mutate() {
        return new BigDecimal("" + R.nextDouble()).compareTo(MUTATION_CHANCE) < 0;
    }

}
