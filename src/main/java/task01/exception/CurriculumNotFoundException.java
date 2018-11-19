package task01.exception;

public class CurriculumNotFoundException extends RuntimeException {
    public CurriculumNotFoundException(String name) {
        super("Curriculum '" + name + "' not found");
    }
}
