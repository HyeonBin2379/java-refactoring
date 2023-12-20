package oncall.model;

import static oncall.util.WorkerValidator.validateWorker;

import java.util.List;

public class HolidayWorker {
    private final List<String> workers;
    public HolidayWorker(List<String> workers) {
        validateWorker(workers);
        this.workers = workers;
    }

    public List<String> getWorkers() {
        return workers;
    }
}
