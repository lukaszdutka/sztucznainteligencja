package zadanie1;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


/**
 * This class represents Item (most important thing of item is it's profit and weight)
 */

@Data
@NoArgsConstructor
class Item {
    String id;
    BigDecimal profit;
    BigDecimal weight;
    String nodeId;
}
