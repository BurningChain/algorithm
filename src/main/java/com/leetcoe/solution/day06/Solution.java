package com.leetcoe.solution.day06;

public class Solution {

    public static int solution(int dividend, int divisor) {
        int current = dividend;
        int result = 0;
        while (Math.abs(current) > Math.abs(divisor)) {
            if (dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0) {
                current -= divisor;
            } else {
                current += divisor;
            }
            result += 1;
        }
        if (result > (int)Math.pow(2, 32)) return (int) Math.pow(2, 32) - 1;
        return (dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0) ? result : 0 - result;
    }

    public static void main(String[] args){

        int solution = solution(-2147483648, -1);
        System.out.println(solution);

    }

}
