package com.algorithm.sort;

import java.util.Arrays;

/**
 * @author miracle
 * @date 2019-01-01
 */
public class SortUtils {

    private static int[] arr = new int[]{1, 6, 2, 10, 8, 5, 7, 9};

    public static void main(String[] args) {

        System.out.println(Arrays.toString(bubbleSort(arr)));
        System.out.println(Arrays.toString(insertSort(arr)));
    }


    /**
     * 选择排序
     * 时间复杂度总是o(n^2)
     *
     * @param arr
     * @return
     */
    public static int[] selectSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        return arr;
    }


    public static int[] bubbleSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        return arr;
    }

    /**
     * 插入排序
     *
     * @param arr
     * @return
     */
    public static int[] insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int preIndex = i - 1;
            for (; preIndex >= 0 && arr[preIndex] > current; preIndex--) {
                arr[preIndex + 1] = arr[preIndex];
            }
            arr[preIndex + 1] = current;
        }

        return arr;
    }
}
