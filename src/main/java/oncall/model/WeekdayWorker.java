package oncall.model;

import static oncall.util.WorkerValidator.validateWorker;

import java.util.List;

public class WeekdayWorker {
    private final List<String> workers;
    public WeekdayWorker(List<String> workers) {
        validateWorker(workers);
        this.workers = workers;
    }

    public List<String> getWorkers() {
        return workers;
    }
}
