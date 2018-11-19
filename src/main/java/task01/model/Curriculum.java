package task01.model;

import java.util.List;

public class Curriculum {
    private String name;
    private List<Course> courses;

    public Curriculum(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // продолжительность учебной программы в часах
    public int getCurriculumDuration() {
        return courses.stream().mapToInt(Course::getDuration).sum();
    }
}
