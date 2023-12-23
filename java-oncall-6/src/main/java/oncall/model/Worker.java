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

    public void swapWeekDay(int index1, int index2) {
        String temp = weekdayWorker[index1 % weekdayWorker.length];
        weekdayWorker[index1 % weekdayWorker.length] = weekdayWorker[index2 % weekdayWorker.length];
        weekdayWorker[index2 % weekdayWorker.length] = temp;
    }
    public void swapHoliday(int index1, int index2) {
        String temp = holidayWorker[index1 % weekdayWorker.length];
        holidayWorker[index1 % weekdayWorker.length] = holidayWorker[index2 % weekdayWorker.length];
        holidayWorker[index2 % weekdayWorker.length] = temp;
    }
}
