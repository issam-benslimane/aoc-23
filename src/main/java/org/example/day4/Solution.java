package org.example.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String content = Files.readString(Paths.get("src/main/java/org/example/day4/input.txt"));
        System.out.println(solution(content));
    }

    public static int solution(String s){
        List<int[][]> cards = parseCards(s);
        int[] copies = new int[cards.size()];
        int total = 0;
        for (int i = 0; i < cards.size(); i++) {
            int[][] card = cards.get(i);
            int[] winning = card[0];
            int[] available = card[1];
            Set<Integer> set = new HashSet<>();
            copies[i]++;
            int v = 0;
            for(int n: winning){
                set.add(n);
            }
            for(int n: available){
                if (set.contains(n)){
                    v++;
                    set.remove(n);
                }
            }
            for (int j = 0; j < copies[i]; j++) {
                for (int k = i + 1; k <= i + v && k <= copies.length; k++) {
                    copies[k]++;
                }
            }
        }
        for (int n: copies) total+=n;
        return total;
    }

    private static List<int[][]> parseCards(String s){
        String[] cards = s.split("\n");

        List<int[][]> cardList = new ArrayList<>();

        for (String card : cards) {
            String[] parts = card.split(": ")[1].split(" \\| ");
            String[] part1 = parts[0].trim().split("\\s+");
            String[] part2 = parts[1].trim().split("\\s+");

            int[][] cardArray = new int[2][];
            cardArray[0] = new int[part1.length];
            cardArray[1] = new int[part2.length];

            for (int i = 0; i < part1.length; i++) {
                cardArray[0][i] = Integer.parseInt(part1[i]);
            }
            for (int i = 0; i < part2.length; i++) {
                cardArray[1][i] = Integer.parseInt(part2[i]);
            }

            cardList.add(cardArray);
        }
        return cardList;
    }
}
