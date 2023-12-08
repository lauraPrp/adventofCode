package aoc23.day2;

import aoc23.Game;

public class Day2 {

    public static void main(String[] args) {

        String input = Day2Part2.getFileInput();
        int sumOfPossibleGameIds = processInput(input);
        System.out.println("Sum of possible game IDs: " + sumOfPossibleGameIds);
    }

    private static int processInput(String input) {
        // Split input into games
        String[] games = input.split("\n");
        int sum = 0;

        for (String gameStr : games) {
            Game game = parseGame(gameStr);
//            if (game.isPossible()) {
//                sum += game.id;
//            }
        }

        return sum;
    }

    private static Game parseGame(String gameStr) {
        // Splitting the string on ": " to separate the game ID from the subsets
        String[] parts = gameStr.split(": ");
        int gameId = Integer.parseInt(parts[0].split(" ")[1]); // Extracting the game ID

        Game game = new Game(gameId);

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
                    case "red":
                        redCount += numCubes;
                        break;
                    case "green":
                        greenCount += numCubes;
                        break;
                    case "blue":
                        blueCount += numCubes;
                        break;
                }
            }

            // Updating the maximum count in the game object
            game.updateMinCubes(redCount, greenCount, blueCount);
        }

        return game;
    }
}

