package lotto.model;

import java.util.Map;
import lotto.constants.LottoRanks;

public class Calculating {
    private final Map<LottoRanks, Integer> enumMap;
    private final double cost;

    public Calculating(Map<LottoRanks, Integer> enumMap, double cost) {
        this.enumMap = enumMap;
        this.cost = cost;
    }

    public double getRate() {
        return calculateSum() * 100 / cost;
    }
    private long calculateSum() {
        return enumMap.entrySet()
                .stream()
                .mapToLong(this::calculateTuple)
                .sum();
    }
    private long calculateTuple(Map.Entry<LottoRanks, Integer> tuple) {
        LottoRanks key = tuple.getKey();
        return key.getWinnings() * tuple.getValue();
    }
}
