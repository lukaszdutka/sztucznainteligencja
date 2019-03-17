package zadanie1;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Evaluation {

    int generation;
    BigDecimal best;
    BigDecimal worst;
    BigDecimal average;

    public void update(Evaluation evaluatePopulation) {
        if(evaluatePopulation.getBest().compareTo(best) > 0){
            best = evaluatePopulation.getBest();
        }
    }

    @Override
    public String toString() {
        return generation + "\t" + worst.toBigInteger() + "\t" + average.toBigInteger() + "\t" + best.toBigInteger();
    }

    public BigDecimal getBest() {
        return best;
    }

}
