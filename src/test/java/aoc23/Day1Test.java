package aoc23;

import aoc23.day1.Day1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {
  private final String[] input = {"1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet"};

@Test
    void shouldReturnZero(){
    Day1 d1 = new Day1();
    assertEquals(0,d1.sumFirstAndLastDigit());

            }
            }
