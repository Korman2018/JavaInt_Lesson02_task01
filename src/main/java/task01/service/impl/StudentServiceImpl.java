package task01.service.impl;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import task01.model.Student;
import task01.repository.StudentDAO;
import task01.repository.impl.StudentHardCodeRepo;
import task01.service.StudentService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {
    private StudentDAO studentDAO;

    public StudentServiceImpl() {
        // проинициализировать захардкоженные данные
        studentDAO = StudentHardCodeRepo.getInstance();

        // прочитать данные из файла
//        studentDAO = new StudentFileRepo();
    }

    public void printAllData() {
        studentDAO.getAll().forEach(x -> System.out.println(x.fullInfo()));
    }

    public Student getByName(String name, String surname) {
        return studentDAO.getByName(name, surname);
    }

    private List<Student> sortStudents(Comparator<Student> comparator) {
        return studentDAO.getAll()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public void printStudentsSortedByAvgMarks() {
        System.out.println("Список студентов отсортированный по средней оценке:");
        sortStudents(Comparator.comparing(Student::averageRating)).forEach(System.out::println);
    }

    public void printStudentsSortedByHoursLeft() {
        System.out.println("Список студентов отсортированный по времени окончания обучения:");
        sortStudents(Comparator.comparing(Student::hoursLeft)).forEach(System.out::println);
    }

    public void printStudentsFilteredByCanStudy() {
        System.out.println("Список студентов по условию «Есть вероятность, что не будет отчислен» :");
        studentDAO.getAll()
                .stream()
                .filter(Student::isStudentCanStudy)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public void putDataToFile() {
        Gson gson = new Gson();

        try {
            FileUtils.writeStringToFile(new File("studentDAO.json")
                    , gson.toJson(studentDAO.getAll())
                    , "UTF-8"
                    , false);
            FileUtils.writeStringToFile(new File("curriculums.json")
                    , gson.toJson(studentDAO.getAll())
                    , "UTF-8"
                    , false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
