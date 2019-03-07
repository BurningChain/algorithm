package com.leetcoe.solution.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Palindrome {


    public boolean isPalindrome(int x) {
        if (x < 0 ||(x != 0 && x % 10 == 0)) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        List<Integer> list = intToArr(x);
        for (int i = 0; i < list.size(); i++) {
            if (i > list.size() / 2) {
                return true;
            }
            if (!Objects.equals(list.get(i), list.get(list.size() - 1 - i))) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> intToArr(int x) {
        List<Integer> list = new ArrayList<>();
        while (x > 0) {
            list.add(x % 10);
            x = x/10;
        }
        return list;
    }


    public static void main(String[] args){
        boolean palindrome = new Palindrome().isPalindrome(1000021);

    }
}
