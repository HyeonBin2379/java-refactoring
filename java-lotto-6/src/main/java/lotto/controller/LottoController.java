package lotto.controller;

import static lotto.constants.MarksAndConstants.SINGLE_LOTTO_PRICE;

import java.util.List;
import java.util.Map;
import lotto.constants.LottoRanks;
import lotto.model.BonusNum;
import lotto.model.BuyingCost;
import lotto.model.Calculating;
import lotto.model.Comparing;
import lotto.model.Lotto;
import lotto.model.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        int totalCost = getValidBuyingCost(new BuyingCost());
        List<List<Integer>> purchased = purchaseLotto(totalCost);
        Lotto winningNum = getValidWinningNum(new WinningNumbers());
        outputView.makeCompartment();

        int bonusNum = getValidBonusNum(winningNum, new BonusNum());
        inputView.finishInput();

        Map<LottoRanks, Integer> lottoResult = compareLotto(purchased, winningNum, bonusNum);
        double returnRate = getReturnRate(lottoResult, totalCost);
        outputView.printTotalResult(lottoResult, returnRate);
    }

    public int getValidBuyingCost(BuyingCost buyingCost) {
        while (true) {
            try {
                return buyingCost.getCost(inputView.inputBuyingCost());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Lotto getValidWinningNum(WinningNumbers winningNum) {
        while (true) {
            try {
                return winningNum.getLotto(inputView.inputWinnerNumbers());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                winningNum.clearList();
            }
        }
    }

    public int getValidBonusNum(Lotto lotto, BonusNum bonusNum) {
        while (true) {
            try {
                return bonusNum.getBonusNum(inputView.inputBonusNumber(), lotto);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<List<Integer>> purchaseLotto(int totalPrice) {
        List<List<Integer>> purchased = Lotto.getManyLotto(totalPrice / SINGLE_LOTTO_PRICE);
        outputView.printQuantityAndAllPurchasedLotto(purchased.size(), purchased);
        return purchased;
    }

    private Map<LottoRanks, Integer> compareLotto(List<List<Integer>> purchased, Lotto winningNum, int bonusNum) {
        Comparing nextPhase = new Comparing(winningNum, bonusNum);
        return nextPhase.getResult(purchased);
    }
    public Double getReturnRate(Map<LottoRanks, Integer> enumMap, double cost) {
        Calculating calculating = new Calculating(enumMap, cost);
        return calculating.getRate();
    }
}
