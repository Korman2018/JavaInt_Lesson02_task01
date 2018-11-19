package task01.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String name, String surname) {
        super("Student '" + surname + " " + name + "' not found");
    }
}
