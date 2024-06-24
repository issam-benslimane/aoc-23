package org.example.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException {
        String content = Files.readString(Paths.get("src/main/java/org/example/day2/input.txt"));
        System.out.println(solution(content));
    }

    public static int solution(String games){
        String[] lines = games.split("\n");
        int sum = 0;
        for (String line: lines){
            StringBuilder idBuilder = new StringBuilder();
            int i;
            for (i = 5; i < line.length(); i++) {
                if (Character.isDigit(line.charAt(i))) idBuilder.append(line.charAt(i));
                else break;
            }
            int id = Integer.parseInt(idBuilder.toString());
            String[] sets = line.substring(i+2).split("; ");
            Map<String, Integer> cubes = new HashMap<>();
            for (String set: sets){
                String[] setCubes = set.split(", ");
                for (String cube: setCubes){
                    StringBuilder sb = new StringBuilder();
                    int j;
                    for (j = 0; j < cube.length(); j++) {
                        if (Character.isDigit(cube.charAt(j))) sb.append(cube.charAt(j));
                        else break;
                    }
                    int n = Integer.parseInt(sb.toString());
                    String color = cube.substring(j + 1);
                    cubes.merge(color, n, Math::max);
                }
            }
            int power = 1;
            for (int n: cubes.values()){
                power*=n;
            }
            sum+=power;
        }
        return sum;
    }
}
