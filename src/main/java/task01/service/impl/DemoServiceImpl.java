package task01.service.impl;

import task01.model.Student;
import task01.service.DemoService;
import task01.service.StudentService;

public class DemoServiceImpl implements DemoService {
    public void start() {
        StudentService studentService = new StudentServiceImpl();

        Student student = studentService.getByName("Dmitry", "Goi");
        System.out.println(student + "\n");

        studentService.printStudentsSortedByAvgMarks();
        System.out.println();

        studentService.printStudentsSortedByHoursLeft();
        System.out.println();

        studentService.printStudentsFilteredByCanStudy();
    }
}
