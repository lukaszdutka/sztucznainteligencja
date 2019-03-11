package zadanie1;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents Node (in example city. One city can contain many Items)
 */

@Data
class Node {
    String id;
    double x;
    double y;
    List<Item> items;

    Node() { items = new ArrayList<>(); }
}
