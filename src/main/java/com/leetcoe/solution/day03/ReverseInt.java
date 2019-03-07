package com.leetcoe.solution.day03;

public class ReverseInt {


    public int reverseInt(int x) {
        if ((x > Math.pow(2.0, 32) - 1) || x < 0 - Math.pow(2.0, 32)) return 0;
        System.out.println( 1534236469 > Math.pow(2.0, 32));
        int temp = Math.abs(x);
        int result = 0;
        while (temp > 0) {
            result = result * 10 + temp % 10;
            temp = temp / 10;
        }
        if (x < 0) {
            return 0 - result;
        }
        return result;
    }

    public static void main(String[] args){
        new ReverseInt().reverseInt(1534236469);
    }
}
