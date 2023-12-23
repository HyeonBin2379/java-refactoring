package oncall.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WorkerValidator {
    public static void validateWorker(String[] workers) {
        validateWorkerNameLength(workers);
        validateWorkerDuplicated(workers);
    }
    private static void validateWorkerNameLength(String[] workers) {
        for (String name : workers) {
            if (name.length() > 5) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
            }
        }
    }
    private static void validateWorkerDuplicated(String[] workers) {
        Set<String> tempSet = new HashSet<>(Arrays.asList(workers));
        if (tempSet.size() != workers.length) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateWorkerCount(int size) {
        validateMinLimit(size);
        validateMaxLimit(size);
    }
    private static void validateMinLimit(int size) {
        if (size < 5) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }
    private static void validateMaxLimit(int size) {
        if (size > 35) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }
}
