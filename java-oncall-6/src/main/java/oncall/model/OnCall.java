package oncall.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class OnCall {
    private final Month month;
    private final DaysOfWeek[] calender;
    private final Worker worker;
    private final Map<String, String> schedule;
    public OnCall(MonthCalender monthCalender, Worker worker) {
        this.month = monthCalender.getMonth();
        this.calender = monthCalender.getDaysOnCall();
        this.worker = worker;
        this.schedule = new LinkedHashMap<>();
    }

    public void setOnCall() {
        int weekdayIndex = 0;
        int holidayIndex = 0;
        for (int i = 0; i < calender.length; i++) {
            String date = getDate(month.getMonth(), i+1);
            if (isHoliday(month.getMonth(), i+1)) {
                schedule.put(date, worker.getHolidayWorker(holidayIndex++));
                continue;
            }
            schedule.put(date, worker.getWeekdayWorker(weekdayIndex++));
        }
    }
    public String getDate(int month, int day) {
        String date = String.format("%d월 %d일 %s", month, day, calender[day-1].getDaysOfWeek());
        if (Month.isStationaryHoliday(month, day) && !(calender[day-1].isWeekend())) {
            date += "(휴일)";
        }
        return date;
    }
    private boolean isHoliday(int month, int day) {
        return Month.isStationaryHoliday(month, day) || calender[day-1].isWeekend();
    }
    public Map<String, String> getSchedule() {
        return schedule;
    }
}
