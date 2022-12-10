package aoc22;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class Day6 {

    public List<String> loadFile(String path) throws IOException {
        InputLoader inputLoader = new InputLoader(path);
        return inputLoader.readToStringList();
    }

    public int findStartOfMessage(int lenMarker) throws IOException {

        List<String> input = loadFile("6.txt");
        String datastream = input.get(0);
        return IntStream.range(0, datastream.length() - 1)
                .filter(i -> datastream.substring(i, i + lenMarker).chars().distinct().count() == lenMarker)
                .findFirst()
                .getAsInt() + lenMarker;

    }


    public Integer getFirstStar() throws IOException {
        return findStartOfMessage(4);
    }


    public Integer getSecondStar() throws IOException {
        return findStartOfMessage(14);
    }

}
