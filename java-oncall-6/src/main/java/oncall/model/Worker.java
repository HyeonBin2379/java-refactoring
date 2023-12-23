package oncall.model;

public class Worker {
    private final String[] weekdayWorker;
    private final String[] holidayWorker;

    public Worker(String[] weekdayWorker, String[] holidayWorker) {
        this.weekdayWorker = weekdayWorker;
        this.holidayWorker = holidayWorker;
    }

    public String getWeekdayWorker(int index) {
        return weekdayWorker[index % weekdayWorker.length];
    }

    public String getHolidayWorker(int index) {
        return holidayWorker[index % holidayWorker.length];
    }
}
