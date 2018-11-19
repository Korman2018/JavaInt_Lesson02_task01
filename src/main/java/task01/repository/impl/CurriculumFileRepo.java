package task01.repository.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import task01.exception.CurriculumNotFoundException;
import task01.model.Curriculum;
import task01.repository.CurriculumDAO;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CurriculumFileRepo implements CurriculumDAO {
    private String file;

    public CurriculumFileRepo() {
        file = "curriculums.json";
    }

    @Override
    public Curriculum getByName(String name) {
        try {
            return getAll().stream().filter(x -> x.getName().equals(name)).collect(Collectors.toList()).get(0);
        } catch (NullPointerException e) {
            throw new CurriculumNotFoundException(name);
        }
    }

    private List<Curriculum> getAll() {
        try (Stream<String> lines = Files.lines(Paths.get(file), Charset.defaultCharset())) {

            Type listType = new TypeToken<ArrayList<Curriculum>>() {
            }.getType();

            String curriculumListFromFile = lines.collect(Collectors.joining());
            return new Gson().fromJson(curriculumListFromFile, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
