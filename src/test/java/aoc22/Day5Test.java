package aoc22;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day5Test {
@Test
    void shouldLoadFile(){

    String file = "5.txt";
    Day5 day5 = null;
    List<String> input = day5.loadFile(file);
assertEquals(input.size(),514);
}

}
