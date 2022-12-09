import java.io.*;
import aoc22.Day6;

public class AdventOfCode2022 {

    public static void main(String[] args) throws IOException {
        String day = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            day = reader.readLine().concat(".txt");
            // System.out.println(day);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        //  List.of(new Day6(Integer.valueOf(day)) ).forEach(Day::getBothStarts);
        Day6 day6 = new Day6();
        System.out.println(day6.getSecondStar());
    }
}
