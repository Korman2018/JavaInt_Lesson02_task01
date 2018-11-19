package task01.service;

import task01.model.Student;

public interface StudentService {

    void printAllData();

    Student getByName(String name, String surname);

    void printStudentsSortedByAvgMarks();

    void printStudentsSortedByHoursLeft();

    void printStudentsFilteredByCanStudy();

    void putDataToFile();
}
