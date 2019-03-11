package zadanie1;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

/**
 * Configuration class represents data from file (in example file 'easy_0.ttp' or 'trivial_1.ttp') as object.
 */

@Data
class Configuration {

    static String FILE_NAME;
    static final String FILE_PATH = "/Users/user/IdeaProjects/sztucznainteligencja/src/main/resources/zadanie1/";

    static final int POPULATION_SIZE = 100; //even
    static final int ITERATIONS = 100;

    static final Random R = new Random();

    static final BigDecimal CROSSOVER_CHANCE = new BigDecimal("0.7");
    static final BigDecimal MUTATION_CHANCE = new BigDecimal("0.05");

    static String PROBLEM_NAME;
    static String DATA_TYPE;
    static int DIMENSION;
    static int NUMBER_OF_ITEMS;
    static BigDecimal CAPACITY;
    static BigDecimal MIN_SPEED;
    static BigDecimal MAX_SPEED;
    static BigDecimal RENTING_RATIO;
    static String EDGE_WEIGHT_TYPE;

    static final MathContext MC = MathContext.DECIMAL64;
    static List<Node> NODES;
    static final List<Item> ITEMS = new ArrayList<>();

    public Configuration(String fileName){
        FILE_NAME = fileName;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    FILE_PATH + FILE_NAME));

            setProblemName(reader.readLine().replace("PROBLEM NAME:", "").trim());
            setDataType(reader.readLine().replace("KNAPSACK DATA TYPE:", "").trim());
            setDimension(Integer.parseInt(reader.readLine().replace("DIMENSION:", "").trim()));
            setNumberOfItems(Integer.parseInt(reader.readLine().replace("NUMBER OF ITEMS:", "").trim()));
            setCapacity(new BigDecimal(reader.readLine().replace("CAPACITY OF KNAPSACK:", "").trim()));
            setMinSpeed(new BigDecimal(reader.readLine().replace("MIN SPEED:", "").trim()));
            setMaxSpeed(new BigDecimal(reader.readLine().replace("MAX SPEED:", "").trim()));
            setRentingRatio(new BigDecimal(reader.readLine().replace("RENTING RATIO:", "").trim()));
            setEdgeWeightType(reader.readLine().replace("EDGE_WEIGHT_TYPE:", "").trim());

            reader.readLine(); //skip line: NODE COORD SECTION
            Map<String, Node> nodes = new HashMap<>();

            for (int i = 0; i < DIMENSION; i++) {
                Node node = toNode(reader.readLine());
                nodes.put(node.getId(), node);
            }

            reader.readLine(); //skip line: Items Section (index, profit, weight, node number)
            for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
                Item item = toItem(reader.readLine());
                nodes.get(item.getNodeId()).getItems().add(item);
                ITEMS.add(item);
            }

            ITEMS.sort((o1, o2) -> Double.compare(o1.profit.divide(o1.weight, MC).subtract(o2.profit.divide(o2.weight, MC)).doubleValue(), 0.0));

            setNodes(new ArrayList<>(nodes.values()));
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Node toNode(String line) {
        String[] values = line.split("\t");

        Node node = new Node();
        node.setId(values[0]);
        node.setX(Double.parseDouble(values[1]));
        node.setY(Double.parseDouble(values[2]));

        return node;
    }

    private static Item toItem(String line) {
        String[] values = line.split("\t");

        Item item = new Item();
        item.setId(values[0]);
        item.setProfit(new BigDecimal(values[1]));
        item.setWeight(new BigDecimal(values[2]));
        item.setNodeId(values[3]);

        return item;
    }

    private static void setProblemName(String problemName) {
        Configuration.PROBLEM_NAME = problemName;
    }

    private static void setDataType(String dataType) {
        Configuration.DATA_TYPE = dataType;
    }

    private static void setDimension(int dimension) {
        Configuration.DIMENSION = dimension;
    }

    private static void setNumberOfItems(int numberOfItems) {
        Configuration.NUMBER_OF_ITEMS = numberOfItems;
    }

    private static void setCapacity(BigDecimal capacity) {
        Configuration.CAPACITY = capacity;
    }

    private static void setMinSpeed(BigDecimal minSpeed) {
        Configuration.MIN_SPEED = minSpeed;
    }

    private static void setMaxSpeed(BigDecimal maxSpeed) {
        Configuration.MAX_SPEED = maxSpeed;
    }

    private static void setRentingRatio(BigDecimal rentingRatio) {
        Configuration.RENTING_RATIO = rentingRatio;
    }

    private static void setEdgeWeightType(String edgeWeightType) {
        Configuration.EDGE_WEIGHT_TYPE = edgeWeightType;
    }

    private static void setNodes(List<Node> nodes) {
        Configuration.NODES = nodes;
    }
}
