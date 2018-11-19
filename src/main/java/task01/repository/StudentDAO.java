package task01.repository;

import task01.model.Student;

import java.util.List;

public interface StudentDAO {
    List<Student> getAll();

    Student getByName(String name, String surname);
}
