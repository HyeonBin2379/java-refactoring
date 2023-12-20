package oncall.view;

import static oncall.util.InputStringValidator.validateInputString;
import static oncall.util.MonthAndDayOfWeekValidator.validateDayOfWeek;
import static oncall.util.MonthAndDayOfWeekValidator.validateMonth;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import oncall.model.DaysOfWeek;
import oncall.model.Month;
import oncall.model.MonthCalender;

public class InputView {
    public MonthCalender inputMonthAndDayOfWeek() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요>");
        String[] token = validateInputString(Console.readLine());
        Month month = validateMonth(token[0]);
        DaysOfWeek startDay = validateDayOfWeek(token[1]);
        return new MonthCalender(month, startDay);
    }

    public void inputWorkersOnCall() {
        List<String> weekday = inputWorkersInWeekday();
        List<String> holiday = inputWorkersInHoliday();
    }
    public List<String> inputWorkersInWeekday() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요>");
        String weekdayWorker = Console.readLine();
        validateInputString(weekdayWorker);
        return new ArrayList<>(List.of(weekdayWorker.split(",")));
    }

    public List<String> inputWorkersInHoliday() {
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요>");
        String holidayWorker = Console.readLine();
        validateInputString(holidayWorker);
        return new ArrayList<>(List.of(holidayWorker.split(",")));
    }
}