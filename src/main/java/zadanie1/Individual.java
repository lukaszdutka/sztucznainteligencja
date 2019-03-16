package zadanie1;

import lombok.Data;

import java.math.BigDecimal;
import java.util.*;

import static zadanie1.Configuration.*;

/**
 * represents one solution
 */

@Data
class Individual {

    BigDecimal evaluation;
    BigDecimal distance;
    BigDecimal value;
    BigDecimal totalTimeStatic;
    List<Node> nodes; //only for order
    Map<String, List<Item>> takenItems; //nodeid -> items

    final BigDecimal MULTIPLIER = BigDecimal.TEN.pow(6);

    public Individual(List<Node> nodes) {
        this.nodes = nodes;
        this.takenItems = new HashMap<>();
    }

    BigDecimal evaluate() {
        if (evaluation == null) {
            BigDecimal valueOfTakenItems = calculateWhatItems(); // g(y)
            BigDecimal totalTime = calculateTime(); // f(x,y)
            value = valueOfTakenItems;
            totalTimeStatic = totalTime;
            evaluation = valueOfTakenItems.subtract(totalTime); // G(x,y) = g(y) - f(x,y);
        }
        return evaluation;
    }

    private BigDecimal calculateTime() {
        BigDecimal capacity = BigDecimal.ZERO;
        BigDecimal time = BigDecimal.ZERO;
        distance = BigDecimal.ZERO;

        for (int i = 0; i < nodes.size() - 1; i++) {
            Node curr = nodes.get(i);
            Node next = nodes.get(i + 1);

            capacity = capacity.add(itemsFrom(curr));
            BigDecimal d = distance(curr, next);
            BigDecimal v = calculateVelocity(capacity);
            distance = distance.add(d);
            time = time.add(calculateOneTime(v, d));
        }

        //last step
        capacity = capacity.add(itemsFrom(nodes.get(nodes.size() - 1)));
        BigDecimal d = distance(nodes.get(nodes.size() - 1), nodes.get(0));
        BigDecimal v = calculateVelocity(capacity);
        distance = distance.add(d);

        time = time.add(calculateOneTime(v, d));

        return time;
    }

    private BigDecimal calculateOneTime(BigDecimal v, BigDecimal d) {
        return d.divide(v, MC);
    }

    private BigDecimal distance(Node curr, Node next) {
        double x = curr.getX() - next.getX();
        double y = curr.getY() - next.getY();
        return new BigDecimal("" + Math.sqrt(x * x + y * y));
    }

    private BigDecimal itemsFrom(Node node) {
        List<Item> items = takenItems.get(node.getId());
        if (items == null || items.isEmpty()) {
            return BigDecimal.ZERO;
        }
        BigDecimal sum = BigDecimal.ZERO;
        for (Item item : items) {
            sum = sum.add(item.getWeight());
        }
        return sum;
    }

    private BigDecimal calculateWhatItems() {
        BigDecimal capacity = BigDecimal.ZERO;
        BigDecimal value = BigDecimal.ZERO;

        for (Item item : ITEMS) {
            if (capacity.add(item.getWeight()).compareTo(CAPACITY) > 0) {
                continue;
            }
            capacity = capacity.add(item.getWeight());
            value = value.add(item.getProfit());

            if (!takenItems.containsKey(item.getNodeId())) {
                takenItems.put(item.getNodeId(), new ArrayList<>());
            }
            takenItems.get(item.getNodeId()).add(item);
        }

        return value;
    }

    private BigDecimal calculateVelocity(BigDecimal capacityTaken) {
        BigDecimal percentOfCapacity = capacityTaken.divide(CAPACITY, MC);
        BigDecimal differenceOfVelocities = MAX_SPEED.subtract(MIN_SPEED);
        return MAX_SPEED.subtract(percentOfCapacity.multiply(differenceOfVelocities));
    }

    public boolean isBetter(Individual other) {
        return evaluation.compareTo(other.getEvaluation()) > 0;
    }
}
