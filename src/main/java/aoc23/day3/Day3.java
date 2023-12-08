package aoc23.day3;

import java.util.HashSet;
import java.util.Set;

import java.util.*;


public class Day3 {

    public static void main(String[] args) {
        String input = "";
        // Split the input string into lines
        String[] lines = input.split("\n");
        char[][] G = Arrays.stream(lines).map(String::toCharArray).toArray(char[][]::new);
        int R = G.length;
        int C = G[0].length;

        int p1 = 0;
        Map<Pair, List<Integer>> nums = new HashMap<>();
        for (int r = 0; r < R; r++) {
            Set<Pair> gears = new HashSet<>(); // positions of '*' characters next to the current number
            int n = 0;
            boolean hasPart = false;
            for (int c = 0; c <= G[r].length; c++) {
                if (c < C && Character.isDigit(G[r][c])) {
                    n = n * 10 + (G[r][c] - '0');
                    for (int rr = -1; rr <= 1; rr++) {
                        for (int cc = -1; cc <= 1; cc++) {
                            if (0 <= r + rr && r + rr < R && 0 <= c + cc && c + cc < C) {
                                char ch = G[r + rr][c + cc];
                                if (!Character.isDigit(ch) && ch != '.') {
                                    hasPart = true;
                                }
                                if (ch == '*') {
                                    gears.add(new Pair(r + rr, c + cc));
                                }
                            }
                        }
                    }
                } else if (n > 0) {
                    for (Pair gear : gears) {
                        nums.computeIfAbsent(gear, k -> new ArrayList<>()).add(n);
                    }
                    if (hasPart) {
                        p1 += n;
                    }
                    n = 0;
                    hasPart = false;
                    gears.clear();
                }
            }
        }

        System.out.println(p1);
        int p2 = 0;
        for (Map.Entry<Pair, List<Integer>> entry : nums.entrySet()) {
            if (entry.getValue().size() == 2) {
                p2 += entry.getValue().get(0) * entry.getValue().get(1);
            }
        }
        System.out.println(p2);
    }

    private static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return r == pair.r && c == pair.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
}
