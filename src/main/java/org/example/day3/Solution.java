package org.example.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String content = Files.readString(Paths.get("src/main/java/org/example/day3/input.txt"));
        System.out.println(solution(content));
    }

    public static int solution(String s){
        String[] parts = s.split("\n");
        int total = 0;
        char[] schema = String.join("", parts).toCharArray();
        int w = parts[0].length(), h = parts.length;
        int dimension  = w*h;
        for (int col = 0; col < dimension; col++) {
            char c = schema[col];
            if (c == '*'){
                int[] adjacents = {col-1, col+1, col-w, col+w, col-w-1, col-w+1, col+w-1, col+w+1};
                Set<Integer> set = new HashSet<>();
                int partNumbers = 0;
                int currentTotal = 1;
                for (int j: adjacents){
                    int row = j / w;
                    int maxLeft = w*row, maxRight = maxLeft+w;
                    if (j >= maxLeft && j < maxRight && Character.isDigit(schema[j]) && !set.contains(j)){
                        set.add(j);
                        int left = j-1, right = j+1;
                        while (left >= maxLeft && Character.isDigit(schema[left])) set.add(left--);
                        while (right < maxRight && Character.isDigit(schema[right])) set.add(right++);
                        char[] slice = Arrays.copyOfRange(schema, left+1, right);
                        if (partNumbers++ > 2) break;
                        currentTotal *= Integer.parseInt(new String(slice));
                    }
                }
                if (partNumbers == 2) total += currentTotal;
            }
        }
        return total;
    }
}
