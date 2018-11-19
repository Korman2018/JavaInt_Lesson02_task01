package task01.repository.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import task01.exception.StudentNotFoundException;
import task01.model.Student;
import task01.repository.StudentDAO;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentFileRepo implements StudentDAO {
    private String file;

    public StudentFileRepo() {
        file = "students.json";
    }

    @Override
    public Student getByName(String name, String surname) {
        for (Student student : getAll()) {
            if (StringUtils.equals(name, student.getName()) && StringUtils.equals(surname, student.getSurname())) {
                return student;
            }
        }
        throw new StudentNotFoundException(name, surname);
    }

    @Override
    public List<Student> getAll() {
        try (Stream<String> lines = Files.lines(Paths.get(file), Charset.defaultCharset())) {

            Type listType = new TypeToken<ArrayList<Student>>() {
            }.getType();

            String studentListFromFile = lines.collect(Collectors.joining());
            return new Gson().fromJson(studentListFromFile, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
