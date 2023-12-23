package oncall.view;

import java.util.Map;

public class OutputView {
    public void printOnCall(Map<String, String> schedule) {
        for (Map.Entry<String, String> entry : schedule.entrySet()) {
            System.out.printf("%s %s" + System.lineSeparator(), entry.getKey(), entry.getValue());
        }
    }
}
