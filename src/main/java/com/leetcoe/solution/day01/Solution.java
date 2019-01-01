package com.leetcoe.solution.day01;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * leetcode solution3
 *
 * @author Miracle~
 * @date 2018-06-25
 */
public class Solution {


    /**
     * 滑动窗口, set含有某个字符时，左侧向右活动， 不含有时，右侧向右滑动
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {


        Set<Character> set = new HashSet<>();
        int result = 0, i = 0, j = 0;
        while (i < s.length() && j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                result = Math.max(result, j - i);
            } else {
                set.remove(s.charAt(i++));
            }

        }

        return result;
    }


    public int lengthOfLongestSubstringByHash(String s) {

        Map<Character, Integer> map = new HashMap<>();
        int result = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {

            if (map.containsKey(s.charAt(i))) {
                j = Math.max(map.get(s.charAt(i)), j);
            }

            map.put(s.charAt(i), i + 1);
            result = Math.max(result, i - j + 1);
        }
        return result;
    }


    public static void main(String[] args) {
        int abcabcbb = new Solution().lengthOfLongestSubstring("sdfghflk");
        System.out.println(abcabcbb);
    }
}
