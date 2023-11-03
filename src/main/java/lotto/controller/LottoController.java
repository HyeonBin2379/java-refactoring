package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.model.BonusNum;
import lotto.model.BuyingCost;
import lotto.model.Lotto;
import lotto.model.WinningNumbers;
import lotto.view.InputView;

public class LottoController {

    private final InputView inputView;
    private final BuyingCost buyingCost;
    private final WinningNumbers winningNum;
    private final BonusNum bonusNum;

    public LottoController() {
        this.inputView = new InputView();
        this.buyingCost = new BuyingCost();
        this.winningNum = new WinningNumbers();
        this.bonusNum = new BonusNum();
    }
    public void run() {
        int cost = getValidBuyingCost(inputView);
        // 구입한 로또 수량 및 번호 출력
        List<List<Integer>> manyLotto = Lotto.getManyLotto(cost/1000);
        System.out.println();
        System.out.println(cost/1000 + "개를 구매했습니다.");
        for (List<Integer> oneLotto : manyLotto) {
            System.out.print("[" + oneLotto.get(0));
            for (int i = 1; i < oneLotto.size(); i++) {
                System.out.print(", "+oneLotto.get(i));
            }
            System.out.println("]");
        }
        Lotto winning = getValidWinningNum(inputView);
        getValidBonusNum(inputView, winning);

        /*
        // 각 로또마다 당첨번호와 일치하는 숫자 개수, 보너스 숫자와의 일치 여부 구하기
        List<Integer> matchedNumber = new ArrayList<>();
        List<Boolean> matchedBonusNum = new ArrayList<>();
        for (List<Integer> oneLotto : manyLotto) {
            int matched = 0;
            for (Integer num : oneLotto) {
                if (winningNum.contains(num)) {
                    matched++;
                }
            }
            matchedNumber.add(matched);
            if (oneLotto.contains(bonusNum)) {
                matchedBonusNum.add(true);
            }
            if (!oneLotto.contains(bonusNum)) {
                matchedBonusNum.add(false);
            }
        }

        // 각 로또의 당첨금별 일치하는 숫자 개수 구하기
        Map<Integer, Integer> winnerCount = new LinkedHashMap<>();
        winnerCount.put(5000, 0);
        winnerCount.put(50000, 0);
        winnerCount.put(1500000, 0);
        winnerCount.put(30000000, 0);
        winnerCount.put(2000000000, 0);

        for (int i = 0; i < matchedNumber.size(); i++) {
            int num = matchedNumber.get(i);
            int lottoCounts;
            if (num == 3) {
                winnerCount.put(5000, winnerCount.get(5000)+1);
            }
            else if (num == 4) {
                winnerCount.put(50000, winnerCount.get(50000)+1);
            }
            else if (num == 5 && !matchedBonusNum.get(i)) {
                winnerCount.put(1500000, winnerCount.get(1500000)+1);
            }
            else if (num == 5 && matchedBonusNum.get(i)) {
                winnerCount.put(30000000, winnerCount.get(30000000)+1);
            }
            else if (num == 6) {
                winnerCount.put(2000000000, winnerCount.get(2000000000)+1);
            }
        }

        // 당첨 통계 산출 결과 출력하기
        System.out.println();
        System.out.println("당첨 통계\n---");
        DecimalFormat prize = new DecimalFormat("###,###");
        for (Integer key : winnerCount.keySet()) {
            System.out.println(key + "원 - " + winnerCount.get(key) + "개");
        }

        // 수익률 산출 및 출력하기
        long totalPrize = 0;
        for (Integer key : winnerCount.keySet()) {
            totalPrize += (long)key * winnerCount.get(key);
        }
        double returnRates = (double)totalPrize*100/cost;
        System.out.println("총 수익률은 " + String.format("%.1f", returnRates) + "%입니다.");
        */
        Console.close();
    }

    public int getValidBuyingCost(InputView inputView) {
        int validCost = 0;
        while (true) {
            try {
                validCost = buyingCost.getCost(inputView.inputPurchaseCost());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return validCost;
    }
    public Lotto getValidWinningNum(InputView inputView) {
        Lotto result;
        while (true) {
            try {
                result = winningNum.getLotto(inputView.inputWinnerNumbers());
                break;
            } catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
                winningNum.clearList();
            }
        }
        return result;
    }
    public void getValidBonusNum(InputView inputview, Lotto lotto) {
        while (true) {
            try {
                int validBonusNum = bonusNum.getBonusNum(inputview.inputBonusNumber());
                lotto.setBonusNum(validBonusNum);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
