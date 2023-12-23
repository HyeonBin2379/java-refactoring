package oncall.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class OnCall {
    private final MonthCalender monthCalender;
    private final Worker worker;
    private final String[] schedule;
    private int weekdayIndex, holidayIndex;
    public OnCall(MonthCalender monthCalender, Worker worker) {
        Month month = monthCalender.getMonth();
        this.monthCalender = monthCalender;
        this.worker = worker;
        this.schedule = new String[month.getDaysCount()+1];
    }

    public void setOnCall(Map<String, String> result) {
        Month month = monthCalender.getMonth();
        DaysOfWeek[] calender = monthCalender.getDaysOnCall();
        for (int day = 1; day < calender.length+1; day++) {
            String date = getDate(month, calender, day);
            setWorker(month, calender, day);
            result.put(date, schedule[day]);
        }
    }
    public String getDate(Month month, DaysOfWeek[] calender, int day) {
        String date = String.format("%d월 %d일 %s", month.getMonth(), day, calender[day-1].getDaysOfWeek());
        if (Month.isStationaryHoliday(month, day) && !(calender[day-1].isWeekend())) {
            date += "(휴일)";
        }
        return date;
    }
    public void setWorker(Month month, DaysOfWeek[] calender, int day) {
        setHolidayWorker(month, calender, day);
        setWeekdayWorker(month, calender, day);
    }
    private void setWeekdayWorker(Month month, DaysOfWeek[] calender, int day) {
        if (isHoliday(month, calender, day)) {
            if (day > 1 && schedule[day-1].equals(worker.getHolidayWorker(holidayIndex))) {
                worker.swapHoliday(holidayIndex, holidayIndex+1);
            }
            schedule[day] = worker.getHolidayWorker(holidayIndex++);
        }
    }
    private void setHolidayWorker(Month month, DaysOfWeek[] calender, int day) {
        if (!isHoliday(month, calender, day)) {
            if (day > 1 && schedule[day-1].equals(worker.getWeekdayWorker(weekdayIndex))) {
                worker.swapWeekDay(weekdayIndex, weekdayIndex+1);
            }
            schedule[day] =  worker.getWeekdayWorker(weekdayIndex++);
        }
    }
    private boolean isHoliday(Month month, DaysOfWeek[] calender, int day) {
        return Month.isStationaryHoliday(month, day) || calender[day-1].isWeekend();
    }

    public Map<String, String> getResult() {
        Map<String, String> result = new LinkedHashMap<>();
        setOnCall(result);
        return result;
    }
}
