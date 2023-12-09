package aoc23;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

    static List<int[]> getInts(String filePath) throws IOException {
        List<int[]> list = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] numberStrings = line.trim().split("\\s+");
                int[] numbers = new int[numberStrings.length];
                for (int i = 0; i < numberStrings.length; i++) {
                    numbers[i] = Integer.parseInt(numberStrings[i]);
                }
                list.add(numbers);
            }


            return list;

        } catch (Exception e) {
        } finally {
            assert reader != null;
            reader.close();
        }
        return null;
    }

    public static void formatOutput(String result){
        final String ANSI_COLOUR_PURPLE = "\u001B[35m";
        System.out.print(ANSI_COLOUR_PURPLE  + result + "\n");
    }
}