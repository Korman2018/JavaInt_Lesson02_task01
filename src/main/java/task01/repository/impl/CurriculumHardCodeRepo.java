package task01.repository.impl;

import task01.exception.CurriculumNotFoundException;
import task01.model.Course;
import task01.model.Curriculum;
import task01.repository.CurriculumDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CurriculumHardCodeRepo implements CurriculumDAO {
    private List<Curriculum> curriculumList;

    private CurriculumHardCodeRepo() {
        curriculumList = new ArrayList<>();
        init();
    }

    private static class CurriculumHolder {
        private static final CurriculumHardCodeRepo HOLDER_INSTANCE = new CurriculumHardCodeRepo();
    }

    public static CurriculumHardCodeRepo getInstance() {
        return CurriculumHolder.HOLDER_INSTANCE;
    }

    private void init() {
        List<Course> jTwoEE = new ArrayList<>();
        List<Course> javaDev = new ArrayList<>();

        jTwoEE.add(new Course("Java Servlets technology", 16));
        jTwoEE.add(new Course("Struts Framework", 24));
        jTwoEE.add(new Course("Spring Framework", 48));
        jTwoEE.add(new Course("Hibernate", 20));

        javaDev.add(new Course("Java technologies overview", 8));
        javaDev.add(new Course("JFC/Swing library", 16));
        javaDev.add(new Course("JDBC technology", 16));
        javaDev.add(new Course("JAX technology", 52));
        javaDev.add(new Course("Common libraries", 44));

        curriculumList.add(new Curriculum("J2EE Developer", jTwoEE));
        curriculumList.add(new Curriculum("Java Developer", javaDev));
    }

    @Override
    public Curriculum getByName(String name) {
        try {
            return curriculumList.stream().filter(x -> x.getName().equals(name)).collect(Collectors.toList()).get(0);
        } catch (NullPointerException e) {
            throw new CurriculumNotFoundException(name);
        }
    }
}
