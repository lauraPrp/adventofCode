package aoc23;

import java.io.*;
import java.util.*;

public class Day8 extends DayBase {

    public static void main(String[] args) throws IOException {
        String filePath = "src/main/resources/2023/d8_23input.txt";
        String input = getInput(filePath);


        String[] parts = input.split("\n\n");
        String steps = parts[0];
        String[] rules = parts[1].split("\n");

        Map<String, String> leftMap = new HashMap<>();
        Map<String, String> rightMap = new HashMap<>();

        for (String rule : rules) {
            String[] ruleParts = rule.split("=");
            String st = ruleParts[0].trim();
            String[] lr = ruleParts[1].split(",");
            leftMap.put(st, lr[0].trim().substring(1).trim());
            rightMap.put(st, lr[1].trim().substring(0, lr[1].trim().length() - 1).trim());
        }
        long p1 = solve(steps, leftMap, rightMap, false);
        long p2 = solve(steps, leftMap, rightMap, true);
        formatOutput("Part1: " + p1);
        formatOutput("Part2: " + p2);
    }

    private static long calculateLeastComuneMultiple(long a, long b) {
        return a / calculateGreatestCommonDivisor(a, b) * b;
    }

    private static long calculateGreatestCommonDivisor(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    private static long solve(String steps, Map<String, String> leftMap, Map<String, String> rightMap, boolean part2) {
        List<String> position = new ArrayList<>(leftMap.keySet().stream()
                .filter(key -> (part2 && key.endsWith("A")) || (!part2 && key.endsWith("AAA")))
                .toList());

        Map<Integer, Long> track = new HashMap<>();
        long t = 0;
        int stepsLength = steps.length(); // Pre-calculate the length of the steps string

        while (true) {
            for (int i = 0; i < position.size(); i++) {
                String currentPos = position.get(i);
                String nextPos = steps.charAt((int) (t % stepsLength)) == 'L' ? leftMap.get(currentPos) : rightMap.get(currentPos);

                if (nextPos.endsWith("Z")) {
                    track.put(i, t + 1);
                    if (track.size() == position.size()) {
                        return track.values().stream().reduce(1L, Day8::calculateLeastComuneMultiple); // Calculate LCM using Stream API
                    }
                }

                position.set(i, nextPos); // Update in place
            }
            t++;
        }
    }
}

