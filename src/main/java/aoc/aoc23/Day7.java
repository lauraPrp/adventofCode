package aoc.aoc23;

import aoc.base.DayBase;

import java.util.*;

public class Day7 extends DayBase {
    private static final String CARDS = "23456789TQKA";

    public static void main(String[] args) {
        String input = DayBase.getInput("src/main/resources/2023/d7_23input.txt");

        List<HandBidPair> scored = new ArrayList<>();
        for (String line : input.split("\n")) {
            String[] parts = line.split(" ");
            scored.add(new HandBidPair(parts[0], Integer.parseInt(parts[1])));
        }

        scored.sort((a, b) -> compareHands(a.hand, b.hand));

        int total = 0;
        for (int i = 0; i < scored.size(); i++) {
            total += (scored.size() - i) * scored.get(i).bid;
        }

        formatOutput("tot: " + total);

    }

    private static int compareHands(String hand1, String hand2) {
        return compareScores(bestScore(hand1), bestScore(hand2));
    }

    private static int compareScores(List<Integer> score1, List<Integer> score2) {
        for (int i = 0; i < Math.min(score1.size(), score2.size()); i++) {
            int comp = score2.get(i).compareTo(score1.get(i));
            if (comp != 0) {
                return comp;
            }
        }
        return 0;
    }

    private static List<Integer> bestScore(String hand) {
        if (hand.indexOf('J') >= 0) {
            List<Integer> best = null;
            for (char c : CARDS.toCharArray()) {
                List<Integer> current = score(hand.replaceFirst("J", String.valueOf(c)));
                if (best == null || compareScores(current, best) > 0) {
                    best = current;
                }
            }
            return best;
        }
        return score(hand);
    }

    private static List<Integer> score(String hand) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : hand.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        List<Integer> score = new ArrayList<>(counts.values());
        Collections.sort(score, Collections.reverseOrder());
        List<Character> sortedHand = hand.chars().mapToObj(c -> (char) c)
                .sorted((c1, c2) -> counts.get(c2).equals(counts.get(c1)) ?
                        Integer.compare(value(c2), value(c1)) : Integer.compare(counts.get(c2), counts.get(c1)))
                .toList();
        for (char c : sortedHand) {
            score.add(value(c));
        }
        return score;
    }

    private static int value(char card) {
        return CARDS.indexOf(card);
    }

    private static class HandBidPair {
        String hand;
        int bid;

        HandBidPair(String hand, int bid) {
            this.hand = hand;
            this.bid = bid;
        }
    }
}
