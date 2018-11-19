package task01;

import task01.service.DemoService;
import task01.service.impl.DemoServiceImpl;

public class Runner {
    public static void main(String[] args) {
        DemoService demoService = new DemoServiceImpl();
        demoService.start();
    }
}
