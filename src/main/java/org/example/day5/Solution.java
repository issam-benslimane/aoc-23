package org.example.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public static void main(String[] args) throws IOException {
        String content = Files.readString(Paths.get("src/main/java/org/example/day5/input.txt"));
        System.out.println(solution(content));
    }

    public static int solution(String s){
        int total = 0;
        return total;
    }
}
