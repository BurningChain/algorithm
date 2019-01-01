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
        System.out.println(Arrays.toString(quickSort(arr, 0, arr.length - 1)));
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

    /**
     * 插入排序算法
     *
     * @param arr
     * @return
     */
    public static int[] insertSortRepeat(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int preIndex = i - 1;
            int current = arr[i];

            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }

            arr[preIndex] = current;
        }
        return arr;
    }


    /**
     * 快速排序算法
     *
     * @param arr
     * @return
     */
    public static int[] quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return arr;
        }
        int low = left, high = right;
        int pivot = arr[low];
        while (low < high) {
            while (low < high && arr[low] < pivot) {
                low++;
            }

            while (low < high && arr[high] > pivot) {
                high--;
            }

            if (low < high) {
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
            }
        }

        arr[left] = arr[low];
        arr[low] = pivot;
        quickSort(arr, left, low - 1);
        quickSort(arr, low + 1, right);
        return arr;
    }







    public static class QuickSort{


        /**
         * 快速排序算法
         *
         * @param arr
         * @return
         */
        public static int[] quickSort(int[] arr, int left, int right) {
            int low = 0, high = arr.length;
            int pivot = arr[low];
            while (low < high) {
                while (low < high && arr[low] < pivot) {
                    low++;
                }

                while (low < high && arr[high] > pivot) {
                    high--;
                }

                if (low < high) {
                    int temp = arr[low];
                    arr[low] = arr[high];
                    arr[high] = temp;
                }
            }

            arr[left] = arr[low];
            arr[low] = pivot;
            quickSort(arr, left, low - 1);
            quickSort(arr, low + 1, right);
            return arr;
        }

    }



}
