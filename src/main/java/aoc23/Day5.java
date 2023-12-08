package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

public class Day5 {

    static class Pair {
        long start;
        long length;

        public Pair(long start, long length) {
            this.start = start;
            this.length = length;
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> fileContent = Files.readAllLines(Paths.get("src/main/resources/2023/d5_23input.txt"));
        String[] parts = String.join("\n", fileContent).split("\n\n");

        List<Long> seeds = Arrays.stream(parts[0].split("\\s+"))
                .skip(1)
                .map(Long::parseLong)
                .toList();

        List<String> mappings = Arrays.asList(Arrays.copyOfRange(parts, 1, parts.length));

        List<Pair> seedsPairs = seeds.stream()
                .map(s -> new Pair(s, 1))
                .toList();

        List<Pair> pairs = IntStream.range(0, seeds.size() / 2)
                .mapToObj(i -> new Pair(seeds.get(2 * i), seeds.get(2 * i + 1)))
                .toList();

        System.out.println(minValue(seedsPairs, mappings));
        System.out.println(minValue(pairs, mappings));
    }

    private static long minValue(List<Pair> inputs, List<String> mappings) {
        for (String mapping : mappings) {
            inputs = lookup(inputs, mapping);
        }
        return inputs.stream().mapToLong(p -> p.start).min().orElse(Long.MAX_VALUE);
    }

    private static List<Pair> lookup(List<Pair> inputs, String mapping) {
        List<Pair> result = new ArrayList<>();
        String[] lines = mapping.split("\n");

        for (Pair input : inputs) {
            long start = input.start;
            long length = input.length;

            while (length > 0) {
                boolean matched = false;
                for (int i = 1; i < lines.length; i++) {  // Start from the second line
                    String[] parts = lines[i].split("\\s+");
                    if (parts.length < 3) continue;  // Skip if there aren't enough parts

                    try {
                        long dst = Long.parseLong(parts[0]);
                        long src = Long.parseLong(parts[1]);
                        long len = Long.parseLong(parts[2]);

                        long delta = start - src;
                        if (delta >= 0 && delta < len) {
                            len = Math.min(len - delta, length);
                            result.add(new Pair(dst + delta, len));
                            start += len;
                            length -= len;
                            matched = true;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        // Handle or log the exception if needed
                        continue;
                    }
                }

                if (!matched) {
                    result.add(new Pair(start, length));
                    break;
                }
            }
        }
        return result;
    }

}
