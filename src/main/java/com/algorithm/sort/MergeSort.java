package com.algorithm.sort;

import java.util.Arrays;

public class MergeSort {

    public MergeSort() {

    }

    private static int[] mainSort(int[] arr) {

        mergeSort(arr, 0, arr.length - 1);
        return arr;
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        mergeSort(arr, left, (right + left) / 2);
        mergeSort(arr, (right + left) / 2 + 1, right);

        merge(arr, left, (right + left) / 2, right);

    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int nl = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            help[nl++] = (arr[p1] >= arr[p2] ? arr[p2++] : arr[p1++]);
        }

        while (p1 <= mid) {
            help[nl++] = arr[p1++];
        }

        while (p2 <= right) {
            help[nl++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }
    }


    private static int[] arr = {5, 13, 28, 15, 9, 6, 3, 18};

    public static void main(String[] args) {
        System.out.println(Arrays.toString(mainSort(arr)));
    }
}

class Sumtotal {

    private static int[] newarr = {1, 3, 4, 2, 5};

    public static int getSumFromArr(int[] arr) {
        int res = mergeSort(arr, 0, arr.length - 1);
        System.out.println(res);
        return res;
    }

    private static int mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return 0;
        }

        int sl = mergeSort(arr, left, (left + right) / 2);
        int sr = mergeSort(arr, (left + right) / 2 + 1, right);

        int ss = merge(arr, left, (right + left) / 2, right);
        return sl + sr + ss;
    }

    private static int merge(int[] arr, int left, int mid, int right) {

        int[] help = new int[right - left + 1];
        int nl = 0, p1 = left, p2 = mid + 1;
        int result = 0;

        while (p1 <= mid && p2 <= right) {
            result += arr[p1] <= arr[p2] ? arr[p1] * (right - p2 + 1) : 0;
            help[nl++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= mid) {
            help[nl++] = arr[p1++];
        }
        while (p2 <= right) {
            help[nl++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }

        return result;
    }

    public static void main(String[] args){
        int sumFromArr = getSumFromArr(newarr);
        System.out.println(sumFromArr);
    }
}
