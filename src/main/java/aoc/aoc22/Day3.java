package aoc.aoc22;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day3 {
    public List<String> loadFile(String path) {
        InputLoader inputLoader = new InputLoader(path);
        return inputLoader.readToStringList();
    }

    public String[][] splitString(List<String> inputList) {

        String[][] containers = new String[inputList.size()][2];
        for (int i = 0; i < inputList.size(); i++) {
            String singleStr = inputList.get(i);
            containers[i][0] = singleStr.substring(0, singleStr.length() / 2);
            containers[i][1] = singleStr.substring(singleStr.length() / 2);
        }
        return containers;
    }

    public String extractDuplicates(String[][] entries) {
        StringBuilder dupes = new StringBuilder();
        for (String[] entry : entries) {
            dupes.append(findDuplicates(entry[0], entry[1]));
        }
        return dupes.toString();
    }

    public int calculatePrioritiesValue(String duplicates) {
        int value = 0;
        final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < duplicates.length(); i++) {
            value += alphabet.indexOf(duplicates.charAt(i)) + 1;
        }
        return value;
    }


    private String findDuplicates(String s1, String s2) {
        String res = "";
        res = s1.chars()
                .distinct()
                .mapToObj(ch -> String.valueOf((char) ch))
                .filter(s2::contains)
                .collect(Collectors.joining());
        return res;
    }

    public int part2() throws IOException {

        int total = 0;

        List<String> input = loadFile("2022/3.txt");

        String[] lines = input.toArray(new String[0]);
        for (int p = 0; p < lines.length; p += 3) {
            String lineOne = lines[p];
            String lineTwo = lines[p + 1];
            String lineThree = lines[p + 2];

            String sameStr = "";
            for (int i = 0; i < lineOne.length(); i++) {
                if (lineTwo.contains(lineOne.substring(i, i + 1)) && lineThree.contains(lineOne.substring(i, i + 1))) {
                    sameStr = lineOne.substring(i, i + 1);
                    break;
                }
            }
            int value;
            if (sameStr.toUpperCase().equals(sameStr))
                value = sameStr.charAt(0) - 64 + 26;
            else
                value = sameStr.charAt(0) - 96;
            total += value;

        }
        System.out.printf("Total: %d%n", total);
        return total;
    }

    public String getInput() {
        try {
            File f = new File("2022/3.txt");
            Scanner scanner = new Scanner(f);
            return scanner.useDelimiter("\\Z").next();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}