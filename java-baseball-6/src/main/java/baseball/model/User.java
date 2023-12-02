package baseball.model;

import static baseball.util.Validation.validateDuplicatedNumber;
import static baseball.util.Validation.validateUserNumber;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final List<Integer> user;

    public User() {
        this.user = new ArrayList<>();
    }

    public void setUser(String input) {
        validateUserNumber(input);
        addValidNumberToArray(input);
    }

    private void addValidNumberToArray(String input) {
        for (String str : input.split("")) {
            int num = Integer.parseInt(str);
            validateDuplicatedNumber(user, num);
            user.add(num);
        }
    }

    public void clearUser() {
        user.clear();
    }

    public List<Integer> getUser() {
        return user;
    }
}
