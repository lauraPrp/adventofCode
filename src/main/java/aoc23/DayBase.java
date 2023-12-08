package aoc23;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DayBase {
   public static String getInput(String filePath) {
        try {
            return Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
            // System.out.println("File content: \n" + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
