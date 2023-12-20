package oncall.view;

import oncall.model.DaysOfWeek;
import oncall.model.Month;
import oncall.model.MonthCalender;
import oncall.model.StationaryHolidays;

public class OutputView {
    public void printOnCall(MonthCalender monthCalender) {
        System.out.println();
        Month month = monthCalender.getMonth();
        DaysOfWeek[] result = monthCalender.getDaysOnCall();
        for (int i = 1; i < result.length+1; i++) {
            System.out.printf("%d월 %d일 %s", month.getMonth(), i, result[i-1].getDaysOfWeek());
            if (isStationHoliday(month, i) && isWeekday(result[i-1])) {
                System.out.print("(휴일)");
            }
            System.out.println();
        }
    }
    private boolean isStationHoliday(Month month, int day) {
        return StationaryHolidays.findHoliday(month, day) != StationaryHolidays.NONE;
    }
    private boolean isWeekday(DaysOfWeek daysOfWeek) {
        return daysOfWeek != DaysOfWeek.SAT && daysOfWeek != DaysOfWeek.SUN;
    }
}
