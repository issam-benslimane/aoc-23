package org.example.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        String content = Files.readString(Paths.get("src/main/java/org/example/day1/input.txt"));
        System.out.println(calibration(content));
    }

    public static int calibration(String document){
        String[] lines = document.split("\n");
        int[] calibrations = new int[lines.length];
        int current = 0;
        for (String line: lines){
            line = replaceWordNumbers(line);
            Integer[] digits = new Integer[2];
            int i = 0;
            while (i < line.length()){
                char a = line.charAt(i);
                char b = line.charAt(line.length() - 1 - i);
                if (digits[0] == null && Character.isDigit(a)) digits[0] = Character.getNumericValue(a);
                if (digits[1] == null && Character.isDigit(b)) digits[1] = Character.getNumericValue(b);
                if (digits[0] != null && digits[1] != null) break;
                i++;
            }
            calibrations[current++] = digits[1] + 10 * digits[0];
        }
        int total = 0;
        for (int c: calibrations){
            total += c;
        }
        return total;
    }

    public static String replaceWordNumbers(String input) {
        Map<String, String> wordToDigitMap = new HashMap<>();
        wordToDigitMap.put("one", "o1e");
        wordToDigitMap.put("two", "t2o");
        wordToDigitMap.put("three", "t3e");
        wordToDigitMap.put("four", "f4r");
        wordToDigitMap.put("five", "f5e");
        wordToDigitMap.put("six", "s6x");
        wordToDigitMap.put("seven", "s7n");
        wordToDigitMap.put("eight", "e8t");
        wordToDigitMap.put("nine", "n9e");
        wordToDigitMap.put("zero", "z0o");

        for (String key: wordToDigitMap.keySet()){
            input = input.replace(key, wordToDigitMap.get(key));
        }

        return input;
    }
}
