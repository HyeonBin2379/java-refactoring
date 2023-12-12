package lotto.constants;

import java.util.Arrays;
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
    private final boolean isSecond;
    private final long winnings;

    LottoRanks(int sameNumber, boolean isSecond, long winnings) {
        this.sameNumber = sameNumber;
        this.isSecond = isSecond;
        this.winnings = winnings;
    }

    public int getSameNumber() {
        return sameNumber;
    }
    public boolean isSecond() {
        return isSecond;
    }
    public long getWinnings() {
        return winnings;
    }

    public static LottoRanks findRank(int sameNumber, boolean isSecond) {
        return Arrays.stream(LottoRanks.values())
                .filter(lottoRanks -> LottoRanks.isRank(lottoRanks, sameNumber, isSecond))
                .findFirst()
                .orElse(NONE);
    }
    private static boolean isRank(LottoRanks rank, int sameNumber, boolean isSecond) {
        return isSameNum(rank, sameNumber) || isSecondRank(rank, isSecond);
    }
    private static boolean isSameNum(LottoRanks rank, int sameNumber) {
        return rank.sameNumber == sameNumber;
    }
    private static boolean isSecondRank(LottoRanks rank, boolean isSecond) {
        return rank.isSecond == isSecond;
    }

    public static Map<LottoRanks, Integer> getEnumMap() {
        Map<LottoRanks, Integer> lottoResult = new EnumMap<>(LottoRanks.class);
        for (LottoRanks lottoRanks : lotto.constants.LottoRanks.values()) {
            lottoResult.put(lottoRanks, 0);
        }
        return lottoResult;
    }
}
