package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day4 extends DayBase{


        public static void main(String[] args) {
            try {

                int[][] cards = readCardsFromFile("src/main/resources/2023/d4_23input.txt");
                int totalPoints = Arrays.stream(cards)
                        .mapToInt(Day4::calculatePoints)
                        .sum();
                System.out.println("Total points: " + totalPoints);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public static int[][] readCardsFromFile(String filename) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(filename))) {
            List<int[]> cardsList = lines
                    .filter(line -> line.contains(":") && line.contains("|"))
                    .map(line -> {
                        String[] parts = line.split("\\|");
                        String[] winningNumbers = parts[0].substring(parts[0].indexOf(':') + 1).trim().split("\\s+");
                        String[] playerNumbers = parts[1].trim().split("\\s+");
                        return Stream.concat(Arrays.stream(winningNumbers), Arrays.stream(playerNumbers))
                                .mapToInt(Integer::parseInt)
                                .toArray();
                    })
                    .toList();
            return cardsList.toArray(new int[0][]);
        }
    }

        private static int calculatePoints(int[] card) {
            Set<Integer> winningNumbers = new HashSet<>();
            for (int i = 0; i < 10; i++) {
                winningNumbers.add(card[i]);
            }

            int points = 0;
            boolean isFirstMatch = true;
            for (int i = 10; i < card.length; i++) {
                if (winningNumbers.contains(card[i])) {
                    if (isFirstMatch) {
                        points = 1;
                        isFirstMatch = false;
                    } else {
                        points *= 2;
                    }
                }
            }
            return points;
        }
    }
