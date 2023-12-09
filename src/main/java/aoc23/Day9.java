package aoc23;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day9 {


    public static void main(String[] args) {
        int resultP1=0;
        try {
            List<int[]> sequences = readSequencesFromFile("src/main/resources/2023/d9_23input.txt");

            for (int[] sequence : sequences) {
                int nextValue = predictNextValue(sequence);
                resultP1=resultP1+nextValue;
                System.out.println("Next value: " + nextValue);
            }
            System.out.println("p1: " + resultP1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<int[]> readSequencesFromFile(String filePath) throws IOException {
        List<int[]> sequences = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] numberStrings = line.trim().split("\\s+");
            int[] numbers = new int[numberStrings.length];
            for (int i = 0; i < numberStrings.length; i++) {
                numbers[i] = Integer.parseInt(numberStrings[i]);
            }
            sequences.add(numbers);
        }

        reader.close();
        return sequences;
    }

    private static int predictNextValue(int[] sequence) {
        // Create a list of arrays to store each level of differences
        List<int[]> differencesList = new ArrayList<>();
        differencesList.add(sequence);

        // Generate differences until an all-zeroes sequence is found
        while (true) {
            int[] lastSequence = differencesList.get(differencesList.size() - 1);
            int[] differences = calculateDifferences(lastSequence);
            differencesList.add(differences);
            if (allZeros(differences)) {
                break;
            }
        }

        // Work backwards to find the next value
        for (int i = differencesList.size() - 2; i >= 0; i--) {
            int[] currentSequence = differencesList.get(i);
            int[] nextSequence = differencesList.get(i + 1);
            int nextValue = currentSequence[currentSequence.length - 1] + nextSequence[nextSequence.length - 1];
            currentSequence = Arrays.copyOf(currentSequence, currentSequence.length + 1);
            currentSequence[currentSequence.length - 1] = nextValue;
            differencesList.set(i, currentSequence);
        }

        // Return the last element of the first sequence, which is the extrapolated next value
        return differencesList.get(0)[differencesList.get(0).length - 1];
    }

    private static int[] calculateDifferences(int[] sequence) {
        int[] differences = new int[sequence.length - 1];
        for (int i = 0; i < sequence.length - 1; i++) {
            differences[i] = sequence[i + 1] - sequence[i];
        }
        return differences;
    }

    private static boolean allZeros(int[] array) {
        for (int value : array) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }
}
