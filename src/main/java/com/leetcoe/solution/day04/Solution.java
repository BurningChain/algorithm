package com.leetcoe.solution.day04;

public class Solution {

    public int strStr(String haystack, String needle) {
        if (null == needle || "".equals(needle.trim())) {
            return 0;
        }
        return haystack.indexOf(needle);
    }

    public static void main(String[] args){
        int i = new Solution().strStr("hello", "ll");
        System.out.println(i);

    }
}
