package lotto.model;

import static lotto.constants.MarksAndConstants.COMMA;
import static lotto.util.Validation.validateInteger;
import static lotto.util.Validation.validateNumberRange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WinningNumbers {

    private final List<Integer> numbers;

    public WinningNumbers() {
        this.numbers = new ArrayList<>();
    }

    public Lotto getLotto(String input) {
        for (String token : input.split(COMMA)) {
            int oneNum = validate(token);
            numbers.add(oneNum);
        }
        Collections.sort(numbers);
        return new Lotto(numbers);
    }

    public int validate(String input) {
        int oneNum = validateInteger(input);
        validateNumberRange(oneNum);
        return oneNum;
    }

    public void clearList() {
        numbers.clear();
    }
}