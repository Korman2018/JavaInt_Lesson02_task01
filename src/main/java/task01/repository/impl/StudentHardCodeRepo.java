package task01.repository.impl;

import org.apache.commons.lang3.StringUtils;
import task01.exception.StudentNotFoundException;
import task01.model.Student;
import task01.repository.StudentDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentHardCodeRepo implements StudentDAO {
    private List<Student> students;

    private StudentHardCodeRepo() {
        students = new ArrayList<>();
        init();
    }

    private static class StudentsHolder {
        private static final StudentHardCodeRepo HOLDER_INSTANCE = new StudentHardCodeRepo();
    }

    public static StudentHardCodeRepo getInstance() {
        return StudentsHolder.HOLDER_INSTANCE;
    }

    private void init() {
        students.add(new Student("Ivan", "Ivanov", CurriculumHardCodeRepo.getInstance().getByName("J2EE Developer")
                , Arrays.asList(3, 4, 3, 5, 3), LocalDate.of(2018, 11, 13)));
        students.add(new Student("Petr", "Petrov", CurriculumHardCodeRepo.getInstance().getByName("Java Developer")
                , Arrays.asList(4, 5, 3, 2, 3, 3, 5, 5), LocalDate.of(2018, 11, 13)));
        students.add(new Student("Sidor", "Sidorov", CurriculumHardCodeRepo.getInstance().getByName("J2EE Developer")
                , Arrays.asList(4, 5, 4, 5, 5, 4), LocalDate.of(2018, 11, 13)));
        students.add(new Student("Dmitry", "Goi", CurriculumHardCodeRepo.getInstance().getByName("Java Developer")
                , Arrays.asList(2, 3, 2, 5, 4, 5), LocalDate.of(2018, 11, 13)));
    }

    @Override
    public Student getByName(String name, String surname) throws StudentNotFoundException {
        for (Student student : students) {
            if (StringUtils.equals(name, student.getName()) && StringUtils.equals(surname, student.getSurname())) {
                return student;
            }
        }
        throw new StudentNotFoundException(name, surname);
    }

    @Override
    public List<Student> getAll() {
        return students;
    }
}
