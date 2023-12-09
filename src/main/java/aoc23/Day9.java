package aoc23;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public non-sealed class Day9 extends DayBase {

    public static void main(String[] args) {
        int p1 = 0;
        int p2 = 0;
        try {
            List<int[]> sequences = readSequencesFromFile("src/main/resources/2023/d9_23input.txt");

            for (int[] sequence : sequences) {
                int nextValue = predictNextValue(sequence);
//                System.out.println("Next value: " + nextValue);
                p1 = p1 + nextValue;

                int previousValue = predictPreviousValue(sequence);
//                System.out.println("Previous value: " + previousValue);
                p2 = p2 + previousValue;
            }
            System.out.println("results p1 : " + p1 + " p2:" + p2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<int[]> readSequencesFromFile(String filePath) throws IOException {
        return getInts(filePath);
    }
    private static int predictPreviousValue(int[] sequence) {
        List<int[]> differencesList = getInts(sequence);

        // Work backwards to find the previous value
        for (int i = differencesList.size() - 2; i >= 0; i--) {
            int[] currentSequence = differencesList.get(i);
            int[] nextSequence = differencesList.get(i + 1);
            int previousValue = currentSequence[0] - nextSequence[0];
            currentSequence = prependValue(currentSequence, previousValue);
            differencesList.set(i, currentSequence);
        }

        // Return the first element of the first sequence, which is the extrapolated previous value
        return differencesList.get(0)[0];
    }

    private static List<int[]> getInts(int[] sequence) {
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
        return differencesList;
    }

    private static int[] prependValue(int[] array, int value) {
        int[] newArray = new int[array.length + 1];
        newArray[0] = value;
        System.arraycopy(array, 0, newArray, 1, array.length);
        return newArray;
    }

    private static int predictNextValue(int[] sequence) {
        // Create a list of arrays to store each level of differences
        List<int[]> differencesList = getInts(sequence);

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
