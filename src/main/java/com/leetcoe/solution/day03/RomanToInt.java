package com.leetcoe.solution.day03;

import java.util.HashMap;
import java.util.Map;

public class RomanToInt {


    public int romanToInt(String s) {
        if (null == s || "".equals(s.trim())) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int length = s.length();
        int result = map.get(s.charAt(length - 1));
        for (int i = length - 2; i >= 0; i--) {
            if (map.get(s.charAt(i)) >= map.get(s.charAt(i + 1))) {
                result += map.get(s.charAt(i));
            } else {
                result -= map.get(s.charAt(i));
            }
        }
        return result;
    }


    public static void main(String[] args){
        System.out.println(new RomanToInt().romanToInt("III"));
    }

    private int stringToInt(String s) {
        switch (s) {
            case "I":
                return 1;
            case "V":
                return 5;
            case "X":
                return 10;
            case "L":
                return 50;
            case "C":
                return 100;
            case "D":
                return 500;
            case "M":
                return 1000;
            case "IV":
                return 4;
            case "IX":
                return 9;
            case "XL":
                return 40;
            case "XC":
                return 90;
            case "CD":
                return 400;
            case "CM":
                return 900;
            default:
                return 0;
        }
    }
}
