package aoc22;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;

public class Day6 {
    private static List<String> loadFile() throws IOException {
        try {
            return Files.readAllLines(Paths.get("src/main/resources/2022/6.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int solve(int marker) throws IOException {
        String s = Objects.requireNonNull(loadFile()).get(0);

        int bound = s.length() - 1;
        for (int i = 0; i < bound; i++) {
            if (s.substring(i, i + marker)
                    .chars()
                    .distinct().count() == marker) {
                return OptionalInt.of(i).getAsInt() + marker;
            }
        }
        return OptionalInt.empty().getAsInt() + marker;

    }

    public static void main(String[] args) {
        try {
            System.out.println( "part 1 : "+ solve(4));
            System.out.println( "part 2 : "+ solve(14));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
