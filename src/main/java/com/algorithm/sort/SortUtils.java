package com.algorithm.sort;

import java.util.Arrays;

/**
 * @author miracle
 * @date 2019-01-01
 */
public class SortUtils {

    private static int[] arr = new int[]{1, 6, 2, 10, 8, 5, 7, 9};

    public static void main(String[] args) {

//        System.out.println(Arrays.toString(bubbleSort(arr)));
//        System.out.println(Arrays.toString(insertSort(arr)));
//        System.out.println(Arrays.toString(quickSort(arr, 0, arr.length - 1)));
        System.out.println(Arrays.toString(shellSort(arr)));
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
        int pivot = arr[left];//定义基准值为数组第一个数
        int i = left;
        int j = right;

        while (i < j) {
            //从右往左找比基准值小的数
            while (pivot <= arr[j] && i < j) {
                j--;
            }
            //从左往右找比基准值大的数
            while (pivot >= arr[i] && i < j) {
                i++;
            }
            //如果i<j，交换它们
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            System.out.println(Arrays.toString(arr));
        }
        arr[left] = arr[i];
        arr[i] = pivot;//把基准值放到合适的位置
        quickSort(arr, left, i - 1);//对左边的子数组进行快速排序
        quickSort(arr, i + 1, right);//对右边的子数组进行快速排序

        return arr;
    }

    public static int[] shellSort(int[] arr) {
        if (null == arr || arr.length <= 1) {
            return arr;
        }

        for (int gap = arr.length / 2; gap >= 1; gap = gap / 2) {

            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                int j = i - gap;
                while (j >= 0 && temp < arr[j]) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = temp;
            }
        }
        return arr;
    }


    public static int[] shellSortRepeat(int[] arr) {
        if (null == arr || arr.length <= 1) {
            return arr;
        }

        for (int gap = arr.length / 3 + 1; gap >= 1; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                int j = i - gap;
                while (j >= 0 && temp < arr[j]) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = temp;
            }
        }
        return arr;
    }



    public static class QuickSort {

        public static void main(String[] args) {

            int[] ints = quickSort(arr, 0, arr.length - 1);
            System.out.println(Arrays.toString(ints));
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
            int pivot = arr[left];
            while (low < high) {
                while (low < high && arr[high] >= pivot) {
                    high--;
                }
                while (low < high && arr[low] <= pivot) {
                    low++;
                }
                if (low < high) {
                    int temp = arr[low];
                    arr[low] = arr[high];
                    arr[high] = temp;
                }
                System.out.println(Arrays.toString(arr));
            }

            arr[left] = arr[low];
            arr[low] = pivot;
            quickSort(arr, left, low - 1);
            quickSort(arr, low + 1, right);
            return arr;
        }

    }





}
