package aoc.aoc22;

import aoc.base.DayBase;

import java.io.IOException;
import java.util.List;

public class Day2 extends DayBase {

    public static void main(String[] args) {

        List<String> input = null;
        try {
            input = loadFile("2022/2.txt");
        } catch (IOException e) {
            System.out.println(e);
        }
        int result1 = 0;
        int result2 = 0;

        for (String line : input) {
            String[] plays = line.split(" ");
            String elfPlay = plays[0];
            String myPlay = plays[1];

            result1 += calculateResult(elfPlay, myPlay, true);
            result2 += calculateResult(elfPlay, myPlay, false);
        }

        formatOutput("First puzzle: " + result1);
        formatOutput("Second puzzle: " + result2);
    }


    private static int calculateResult(String elfPlay, String myPlay, boolean isFirstPuzzle) {
        int score = switch (elfPlay) {
            case "A" -> calculateScore(myPlay, isFirstPuzzle, 8, 4, 3);
            case "B" -> calculateScore(myPlay, isFirstPuzzle, 5, 1, 9);
            case "C" -> calculateScore(myPlay, isFirstPuzzle, 2, 7, 6);
            default -> throw new IllegalStateException("Unexpected value of elfplay: " + elfPlay);
        };
        return score;
    }

    private static int calculateScore(String myPlay, boolean isFirstPuzzle, int scoreY, int scoreX, int scoreZ) {
        if (myPlay.equals("Y")) return isFirstPuzzle ? scoreY : scoreX;
        if (myPlay.equals("X")) return isFirstPuzzle ? scoreX : scoreZ;
        return isFirstPuzzle ? scoreZ : scoreY;
    }

}
