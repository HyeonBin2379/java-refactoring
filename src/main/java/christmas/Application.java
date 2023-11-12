package christmas;

import christmas.controller.DecemberEventController;

public class Application {

    public static void main(String[] args) {
        DecemberEventController event = new DecemberEventController();
        event.run();
    }
}
