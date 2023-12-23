package oncall.view;

import static oncall.util.InputStringValidator.validateInputString;
import static oncall.util.MonthAndDayOfWeekValidator.validateDayOfWeek;
import static oncall.util.MonthAndDayOfWeekValidator.validateMonth;
import static oncall.util.WorkerValidator.validateWorker;
import static oncall.util.WorkerValidator.validateWorkerCount;

import camp.nextstep.edu.missionutils.Console;
import oncall.model.DaysOfWeek;
import oncall.model.Month;
import oncall.model.MonthCalender;
import oncall.model.Worker;

public class InputView {
    public MonthCalender inputMonthAndDayOfWeek() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        String[] token = validateInputString(Console.readLine());
        Month month = validateMonth(token[0]);
        DaysOfWeek startDay = validateDayOfWeek(token[1]);
        return new MonthCalender(month, startDay);
    }

    public Worker inputWorkersOnCall() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        String[] weekday = inputWorkers(Console.readLine());
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        String[] holiday = inputWorkers(Console.readLine());
        return new Worker(weekday, holiday);
    }

    private String[] inputWorkers(String input) {
        String[] worker = validateInputString(input);
        validateWorker(worker);
        validateWorkerCount(worker.length);
        return worker;
    }

    public void finishInput() {
        Console.close();
    }
}