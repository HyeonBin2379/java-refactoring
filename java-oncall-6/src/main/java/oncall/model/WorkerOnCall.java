package oncall.model;

import static oncall.model.StationaryHolidays.NONE;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WorkerOnCall {
    private final List<String> weekdayWorker;
    private final List<String> holidayWorker;
    private final List<String> onCallWorker;

    public WorkerOnCall(List<String> weekdayWorker, List<String> holidayWorker) {
        this.weekdayWorker = weekdayWorker;
        this.holidayWorker = holidayWorker;
        this.onCallWorker = new ArrayList<>();
    }

    public void setOnCallWorker(MonthCalender monthCalender) {
        Month month = monthCalender.getMonth();
        DaysOfWeek[] calender = monthCalender.getDays();
        int weekdayIndex = 0;
        int holidayIndex = 0;
        for (int i = 0; i < calender.length; i++) {
            if (!DaysOfWeek.isWeekend(calender[i])) {
                onCallWorker.add(weekdayWorker.get(weekdayIndex++ % weekdayWorker.size()));
            }
            if (DaysOfWeek.isWeekend(calender[i])) {
                onCallWorker.add(holidayWorker.get(holidayIndex++ % holidayWorker.size()));
            }
        }
    }
    public List<String> getOnCallWorker() {
        return onCallWorker;
    }
}
