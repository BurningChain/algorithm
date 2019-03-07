package com.leetcoe.solution.day05;

public class SyncTest {


    private static double a = 1;

    public synchronized void plusNumber() {
        a++;
    }

    public void minusNumber() {
        System.out.println(a);
        synchronized (this) {
            a--;
        }
    }

    public synchronized static void divide() {

        a = a / 0.1;
    }

}
