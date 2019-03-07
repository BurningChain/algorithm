package com.algorithm.sort;

import java.util.Arrays;

/**
 * 十大经典排序算法
 *
 * @author wwj55
 */
public class tensort {

    private static int[] arr = {1, 3, 15, 2, 8, 19, 4};

    public static void main(String[] args) {
        //System.out.println(Arrays.toString(selectSort(arr)));

        // System.out.println(Arrays.toString(bubbleSort(arr)));

        // System.out.println(Arrays.toString(shellSort(arr)));

        // System.out.println(Arrays.toString(mergeSort(arr)));

        System.out.println(Arrays.toString(quickSort(arr)));
    }


    public static int[] selectSort(int[] arr) {
        if (null == arr || arr.length == 0) {
            return arr;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        return arr;
    }

    public static int[] bubbleSort(int[] arr) {

        if (null == arr || arr.length == 0) {
            return arr;
        }
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


    public static int[] insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int current = arr[i];
            while (j >= 0 && current < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
        return arr;
    }

    /**
     * 希尔排序
     *
     * @param arr
     * @return
     */
    public static int[] shellSort(int[] arr) {

        int gap = arr.length / 2, length = arr.length, temp;
        // 动态分配gap
        /*while (gap < length / 3) {
            gap = gap * 3 + 1;
        }*/
        int j = 0;
        for (; gap > 0; gap = gap / 2) {
            for (int i = gap; i < arr.length; i++) {
                temp = arr[i];
                for (j = i - gap; j > 0 && arr[j] > temp; j -= gap) {
                    arr[j + gap] = arr[j];
                }
                arr[j + gap] = temp;
            }
        }
        return arr;
    }

    /**
     * 归并排序
     *
     * @param arr
     * @return
     */
    public static int[] mergeSort(int[] arr) {
        if (null == arr || arr.length <= 1) {
            return arr;
        }

        subMergeSort(arr, 0, arr.length - 1);
        return arr;
    }

    private static void subMergeSort(int[] arr, int start, int end) {
        if (start >= end || start >= arr.length || end >= arr.length || start < 0 || end < 0) {
            return;
        }

        subMergeSort(arr, start, (end + start) / 2);
        subMergeSort(arr, (end + start) / 2 + 1, end);
        merge(arr, start, (start + end) / 2, end);
    }

    private static void merge(int[] arr, int left, int mid, int right) {

        int[] help = new int[right - left + 1];
        int nl = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
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
    }

    /**
     * 快速排序
     *
     * @param arr
     * @return
     */
    public static int[] quickSort(int[] arr) {

        subQuickSort(arr, 0, arr.length - 1);
        return arr;
    }

    private static void subQuickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = arr[left];
        int tl = left;
        int tr = right;
        while (tl < tr) {
            while (arr[tr] > pivot && tl < tr) {
                tr--;
            }
            while (arr[tl] < pivot && tl < tr) {
                tl++;
            }

            if (tl < tr) {
                int temp = arr[tl];
                arr[tl] = arr[tr];
                arr[tr] = temp;
            }
        }

        subQuickSort(arr, left, tl - 1);
        subQuickSort(arr, tl + 1, right);
    }


}

