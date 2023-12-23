package oncall.model;

public class MonthCalender {
    private final Month month;
    private final DaysOfWeek startDay;
    private final DaysOfWeek[] daysOnCall;

    public MonthCalender(Month month, DaysOfWeek startDay) {
        this.month = month;
        this.startDay = startDay;
        this.daysOnCall = new DaysOfWeek[month.getDaysCount()];
        setDaysOnCall();
    }

    public void setDaysOnCall() {
        int start = startDay.getIndex();
        for (int i = start; i < start + daysOnCall.length; i++) {
            daysOnCall[i-start] = DaysOfWeek.findDaysOfWeekByIndex(i);
        }
    }

    public Month getMonth() {
        return month;
    }

    public DaysOfWeek[] getDaysOnCall() {
        return daysOnCall;
    }
}
