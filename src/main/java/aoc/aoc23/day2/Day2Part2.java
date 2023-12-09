package aoc.aoc23.day2;

import aoc.base.DayBase;

public class Day2Part2 {
    public static void main(String[] args) {

        String input = getFileInput();

        long sumOfPowers = processInput(input);
        System.out.println("Sum of powers: " + sumOfPowers);
    }

    static String getFileInput() {
        String filePath = "src/main/resources/2023/d2_23input.txt";
       return DayBase.getInput(filePath);
    }

    public static long processInput(String input) {
        String[] games = input.split("\n");
        long sum = 0;

        for (String gameStr : games) {
            Game game = parseGame(gameStr);
            sum += game.power();
        }

        return sum;
    }

    public static Game parseGame(String gameStr) {
        // Split the string on ": " to separate the game ID
        String[] parts = gameStr.split(": ");
        int gameId = Integer.parseInt(parts[0].split(" ")[1]); // Extracting the game ID

        Game game = new Game(gameId);

        // Initializing minimum counts to zero
        int minRed = 0, minGreen = 0, minBlue = 0;

        // Splitting the subsets
        String[] subsets = parts[1].split("; ");

        for (String subset : subsets) {
            // Initialize counts for each subset
            int redCount = 0, greenCount = 0, blueCount = 0;

            // Splitting the subset into individual cube counts
            String[] cubeCounts = subset.split(", ");
            for (String count : cubeCounts) {
                // Splitting each count into the number and color
                String[] details = count.split(" ");
                int numCubes = Integer.parseInt(details[0]);
                String color = details[1];

                // Updating the counts based on the color
                switch (color) {
                    case "red" -> redCount += numCubes;
                    case "green" -> greenCount += numCubes;
                    case "blue" -> blueCount += numCubes;
                    default -> throw new IllegalStateException("Unexpected value: " + color);
                }
            }

            // Updating the minimum count if current subset has more cubes of that color
            minRed = Math.max(minRed, redCount);
            minGreen = Math.max(minGreen, greenCount);
            minBlue = Math.max(minBlue, blueCount);
        }

        // Update the Game object with minimum required cubes for each color
        game.minRed = minRed;
        game.minGreen = minGreen;
        game.minBlue = minBlue;

        return game;
    }

}
