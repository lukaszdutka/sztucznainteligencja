package zadanie1;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static zadanie1.Configuration.POPULATION_SIZE;

public class Population {

    private List<Individual> population;

    private Population() {
        this.population = new ArrayList<>();
    }

    private Population(List<Individual> population) {
        this.population = population;
    }

    static Population createRandomPopulation(List<Node> nodes) {
        ArrayList<Individual> population = new ArrayList<>();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            ArrayList<Node> individual = new ArrayList<>(nodes);
            Collections.shuffle(individual);
            population.add(new Individual(individual));
        }
        return new Population(population);
    }

    public static Population createNextPopulation(Population previousPopulation) {
        Population pop = new Population();
        for (int j = 0; j < POPULATION_SIZE / 2; j++) {
            Individual father = getParent(previousPopulation);
            Individual mother = getParent(previousPopulation);

            if (father == mother) {
                j--;
                continue;
            }

            Individual[] children = Individuals.getChildrenFor(father, mother);

            pop.addIndividual(children[0]);
            pop.addIndividual(children[1]);
        }

        return pop;
    }

    private static Individual getParent(Population previous) {
        return Individuals.selectByTournament(previous);
    }

    public List<Individual> getPopulation() {
        return population;
    }

    private void addIndividual(Individual individual) {
        population.add(individual);
    }

    public Evaluation evaluatePopulation(Population population, int id) {
        BigDecimal min = population.getPopulation().get(0).evaluate();
        BigDecimal max = min;
        BigDecimal sum = BigDecimal.ZERO;

        for (Individual individual : population.getPopulation()) {
            BigDecimal temp = individual.evaluate();

            if (temp.compareTo(min) < 0) {
                min = temp;
            }
            if (temp.compareTo(max) > 0) {
                max = temp;
            }
            sum = sum.add(temp);
        }

        BigDecimal average = sum.divide(new BigDecimal(POPULATION_SIZE), MathContext.DECIMAL64);
        return new Evaluation(id, max, min, average);
    }
}
