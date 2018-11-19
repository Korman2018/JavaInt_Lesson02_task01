package task01.repository;

import task01.model.Curriculum;

public interface CurriculumDAO {
    Curriculum getByName(String name);
}
