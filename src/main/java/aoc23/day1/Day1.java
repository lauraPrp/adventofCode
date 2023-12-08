package aoc23.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {
    static File input = new File("src/main/resources/2023/d1_23input.txt");

    public static int sumFirstAndLastDigit() {
        int res = 0;
        char firstDigit = '0';
        char lastDigit = '0';

        try {
            try (Scanner scanner = new Scanner(input)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    firstDigit = findFirst(line, firstDigit);
                    lastDigit = findLast(line, lastDigit);
                    res += Integer.parseInt(firstDigit + "" + lastDigit);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    private static char findLast(String line, char secondDigit) {
        for (int i = line.length() - 1; i >= 0; i--) {
            if (Character.isDigit(line.charAt(i))) {
                secondDigit = line.charAt(i);
                break;
            }
        }
        return secondDigit;
    }

    private static char findFirst(String line, char firstDigit) {
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                firstDigit = line.charAt(i);
                break;
            }
        }
        return firstDigit;
    }

    public static void main(String[] args) {
        System.out.println(Day1.sumFirstAndLastDigit());
    }
}

