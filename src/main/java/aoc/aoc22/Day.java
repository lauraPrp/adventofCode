package aoc.aoc22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.List;

import java.util.stream.Stream;

public abstract class Day<Integer> {
    String BASE_PATH="src/main/resources/2022/";

//    public static List<String> loadFile(String path) throws IOException {
//        InputLoader inputLoader = new InputLoader(path);
//        return inputLoader.readToStringList();
//    }

    public List<String> input;
    public String baseDay = "";

    protected Day(Integer daynum) {
        this.input = getInput();
        this.baseDay = String.valueOf(daynum);

    }

    public List<String> getInput() {

        String dayInput = "2022/"+baseDay + ".txt";
//        List<String> input = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(dayInput))) {
            input = lines.toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    public abstract Object getFirstStar();

    public abstract Object getSecondStar();

    public String getBothStarts() {
        return getFirstStar() + " " + getSecondStar();
    }
}
