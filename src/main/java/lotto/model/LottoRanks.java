package lotto.model;

import java.util.EnumMap;
import java.util.Map;

public enum LottoRanks {
    NONE(0, false, 0),
    FIFTH(3, false, 5000),
    FOURTH(4, false, 50000),
    THIRD(5, false, 1500000),
    SECOND(5, true, 30000000),
    FIRST(6, false, 2000000000);

    private final int sameNumber;
    private final boolean sameBonus;
    private final int winnings;

    LottoRanks(int sameNumber, boolean sameBonus, int winnings) {
        this.sameNumber = sameNumber;
        this.sameBonus = sameBonus;
        this.winnings = winnings;
    }

    public int getSameNumber() {
        return sameNumber;
    }
    public boolean isSameBonus() {
        return sameBonus;
    }
    public int getWinnings() {
        return winnings;
    }

    public static LottoRanks findKey(int sameNumber, boolean isSameBonus) {
        for (LottoRanks lottoRanks : LottoRanks.values()) {
            if (lottoRanks.getSameNumber() == sameNumber
                    && lottoRanks.isSameBonus() == isSameBonus) {
                return lottoRanks;
            }
        }
        return NONE;
    }
    public static Map<LottoRanks, Integer> getEnumMap() {
        Map<LottoRanks, Integer> enumMap = new EnumMap<>(LottoRanks.class);
        for (LottoRanks lottoRanks : LottoRanks.values()) {
            enumMap.put(lottoRanks, 0);
        }
        return enumMap;
    }
}
