package aoc23;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Day8 {

    public static void main(String[] args) throws IOException {
        String filePath = "src/main/resources/2023/d8_23input.txt";
        String D = Files.readString(Path.of(filePath)).trim();
        String[] parts = D.split("\n\n");
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

        System.out.println(solve(steps, leftMap, rightMap, false));
        System.out.println(solve(steps, leftMap, rightMap, true));
    }

    private static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    private static long solve(String steps, Map<String, String> leftMap, Map<String, String> rightMap, boolean part2) {
        List<String> POS = new ArrayList<>();

        // Loop through each key in the leftMap
        for (String key : leftMap.keySet()) {
            // Check condition based on the part2 flag and add to POS list if condition is met
            if ((part2 && key.endsWith("A")) || (!part2 && key.endsWith("AAA"))) {
                POS.add(key);
            }
        }

        // Initialize a map to track the times
        Map<Integer, Long> T = new HashMap<>();
        long t = 0;

        // Infinite loop to find the solution
        while (true) {
            // Create a new list to store the next positions
            List<String> NP = new ArrayList<>();

            // Iterate over the current positions
            for (int i = 0; i < POS.size(); i++) {
                String currentPos = POS.get(i);

                // Determine the next position based on the current step
                String nextPos;
                if (steps.charAt((int) (t % steps.length())) == 'L') {
                    nextPos = leftMap.get(currentPos);
                } else {
                    nextPos = rightMap.get(currentPos);
                }

                // Check if the next position ends with 'Z'
                if (nextPos.endsWith("Z")) {
                    // Update the time map
                    T.put(i, t + 1);

                    // Check if all positions are accounted for
                    if (T.size() == POS.size()) {
                        // Calculate and return the least common multiple of the times
                        long lcmValue = 1;
                        for (long time : T.values()) {
                            lcmValue = lcm(lcmValue, time);
                        }
                        return lcmValue;
                    }
                }

                // Add the next position to the NP list
                NP.add(nextPos);
            }

            // Update the current positions with the next positions
            POS = NP;
            // Increment the time
            t++;
        }
    }


//    private static long solve(String steps, Map<String, String> leftMap, Map<String, String> rightMap, boolean part2) {
//        List<String> POS = leftMap.keySet().stream()
//                .filter(s -> s.endsWith(part2 ? "A" : "AAA"))
//                .toList();
//        Map<Integer, Long> T = new HashMap<>();
//        long t = 0;
//        while (true) {
//            List<String> NP = new ArrayList<>();
//            for (int i = 0; i < POS.size(); i++) {
//                String p = (steps.charAt((int) (t % steps.length())) == 'L' ? leftMap : rightMap).get(POS.get(i));
//                if (p.endsWith("Z")) {
//                    T.put(i, t + 1);
//                    if (T.size() == POS.size()) {
//                        return T.values().stream().reduce(1L, Day8::lcm);
//                    }
//                }
//                NP.add(p);
//            }
//            POS = NP;
//            t++;
//        }
//    }
}

