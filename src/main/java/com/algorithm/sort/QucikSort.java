package com.algorithm.sort;

import java.util.Arrays;

public class QucikSort {

    private static int[] arr = {1, 3, 15, 2, 8, 19, 4};

    public static void main(String[] args){

        int[] newarr = mainSort(arr);
        System.out.println(Arrays.toString(newarr));

    }

    public static int[] mainSort(int[] arr) {

        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int sl = left, sr = right, pivot = arr[left];

        while (sl < sr) {
            while (arr[sr] > pivot && sl < sr) {
                sr--;
            }

            while (arr[sl] < pivot && sl <sr) {
                sl++;
            }

            if (sl < sr) {
                int temp = arr[sl];
                arr[sl] = arr[sr];
                arr[sr] = temp;
            }
        }
        quickSort(arr, left, sl - 1);
        quickSort(arr, sl + 1, right);
    }
}
