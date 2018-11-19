package task01.model;

import java.time.LocalDate;
import java.util.List;

import static java.lang.Math.ceil;

public class Student {
    private String name;
    private String surname;
    private Curriculum curriculum;
    private List<Integer> marks;
    private LocalDate startDate;

    public Student(String name, String surname, Curriculum curriculum, List<Integer> marks, LocalDate startDate) {
        this.name = name;
        this.surname = surname;
        this.curriculum = curriculum;
        this.startDate = startDate;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public List<Integer> getMarks() {
        return marks;
    }

    public void setMarks(List<Integer> marks) {
        this.marks = marks;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    // средний балл студента
    public double averageRating() {
        return marks.stream()
                .mapToDouble(Integer::intValue)
                .average()
                .orElse(0);
    }

    // сколько часов осталось учиться студенту
    // (количество пройденных занятий считаем по кол-ву оценок)
    public int hoursLeft() {
        int hoursCurriculumDuration = curriculum.getCurriculumDuration();
        int numberOfMarks = marks.size();

        return hoursCurriculumDuration - numberOfMarks * 8;
    }

    // проверяем вероятность что студента может продолжить учебу
    // (предполагаемый средний балл >= 4.5)
    // считаем количество дней на учебе по количеству оценок
    public boolean isStudentCanStudy() {
        int numberOfMarks = marks.size();
        double curriculumDurationInDays = ceil(curriculum.getCurriculumDuration() / 8.);
        double possibleMarksSum = (curriculumDurationInDays - numberOfMarks) * 5;
        double avg = (possibleMarksSum + marksSum()) / curriculumDurationInDays;

        return avg >= 4.5;
    }

    private int marksSum() {
        return marks.stream().reduce(0, Integer::sum);
    }

    public String fullInfo() {
        StringBuilder studentOutput = new StringBuilder();
        studentOutput.append("STUDENT: ")
                .append(name).append(" ")
                .append(surname)
                .append("\n")
                .append("CURRICULUM: ")
                .append(curriculum.getName())
                .append("\n")
                .append("START_DATE: ")
                .append(startDate)
                .append("\n")
                .append(String.format("%-30s%10s%n", "COURSE", "DURATION"))
                .append("--------------------------\n");

        int i = 1;
        for (Course course : curriculum.getCourses()) {
            studentOutput.append(i++)
                    .append(".")
                    .append(String.format("%-30s%10s%n", course.getName(), course.getDuration()));
        }

        studentOutput.append("MARKS\n");

        for (int mark : marks) {
            studentOutput.append(mark).append(",");
        }

        return studentOutput.deleteCharAt(studentOutput.length() - 1).toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(surname)
                .append(" ")
                .append(name)
                .append(" - До окончания обучения по программе ")
                .append(curriculum.getName())
                .append(" осталось ")
                .append(hoursLeft())
                .append(" ч. Средний балл ")
                .append(averageRating())
                .append(isStudentCanStudy() ? ". Может продолжать обучение." : ". Отчислить.");
        return sb.toString();
    }
}
