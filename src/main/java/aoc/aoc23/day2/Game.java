package aoc.aoc23.day2;

public class Game {
    int id;
    public int minRed;
    public int minGreen;
    public int minBlue;

    public Game(int id) {
        this.id = id;
        minRed = minGreen = minBlue = Integer.MAX_VALUE;
    }

    public void updateMinCubes(int red, int green, int blue) {
        minRed = Math.min(minRed, red);
        minGreen = Math.min(minGreen, green);
        minBlue = Math.min(minBlue, blue);
    }

    public void updateMaxCubes(int red, int green, int blue) {
        minRed = Math.max(minRed, red);
        minGreen = Math.max(minGreen, green);
        minBlue = Math.max(minBlue, blue);
    }

    public long power() {
        return (long) minRed * minGreen * minBlue;
    }
}

