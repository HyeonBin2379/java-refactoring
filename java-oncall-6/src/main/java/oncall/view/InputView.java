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
        String[] token = inputString("비상 근무를 배정할 월과 시작 요일을 입력하세요>", Console.readLine());
        Month month = validateMonth(token[0]);
        DaysOfWeek startDay = validateDayOfWeek(token[1]);
        return new MonthCalender(month, startDay);
    }

    public Worker inputWorkersOnCall() {
        String[] weekday = inputWorkers("평일 비상 근무 순번대로 사원 닉네임을 입력하세요>", Console.readLine());
        String[] holiday = inputWorkers("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요>", Console.readLine());
        return new Worker(weekday, holiday);
    }

    private String[] inputString(String msg, String inputString) {
        System.out.print(msg);
        String[] input = validateInputString(inputString);
        System.out.println();
        return input;
    }
    private String[] inputWorkers(String msg, String input) {
        String[] worker = inputString(msg, input);
        validateWorker(worker);
        validateWorkerCount(worker);
        return worker;
    }

    public void finishInput() {
        Console.close();
    }
}