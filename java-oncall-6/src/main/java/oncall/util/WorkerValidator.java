package oncall.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorkerValidator {
    public static void validateWorker(List<String> workers) {
        validateWorkerNameLength(workers);
        validateWorkerDuplicated(workers);
    }
    private static void validateWorkerNameLength(List<String> workers) {
        for (String name : workers) {
            if (name.length() > 5) {
                workers.clear();
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
            }
        }
    }
    private static void validateWorkerDuplicated(List<String> workers) {
        Set<String> tempSet = new HashSet<>(workers);
        if (tempSet.size() != workers.size()) {
            workers.clear();
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateWorkerCount(List<String> weekday, List<String> holiday) {
        validateMinLimit(weekday, holiday);
        validateMaxLimit(weekday, holiday);
    }
    private static void validateMinLimit(List<String> weekday, List<String> holiday) {
        if (weekday.size() + holiday.size() < 5) {
            weekday.clear();
            holiday.clear();
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }
    private static void validateMaxLimit(List<String> weekday, List<String> holiday) {
        if (weekday.size() + holiday.size() > 35) {
            weekday.clear();
            holiday.clear();
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }
}
