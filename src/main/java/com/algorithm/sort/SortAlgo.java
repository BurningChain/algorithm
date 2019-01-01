package com.algorithm.sort;


import java.util.Arrays;

/**
 * 经典排序算法：面试常见排序算法
 */
public class SortAlgo {

    private static int[] arr = new int[]{1, 6, 2, 10, 8, 5, 7, 9};


    public static void main(String[] args) {
        /* 选择排序 */
        // System.out.println(Arrays.toString(selectSort(arr)));
        // System.out.println(Arrays.toString(bubbleSort(arr)));
//        System.out.println(Arrays.toString(insertSort(arr)));

//        System.out.println(Arrays.toString(insertSortTwo(arr)));
        System.out.println(Arrays.toString(bubbleSortTwo(arr)));
    }


    /**
     * 选择排序
     *
     * @param arr arr
     * @return arr
     */
    private static int[] selectSort(int[] arr) {

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


    /**
     * 冒泡排序
     * 1. 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     * 2. 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     * 3. 针对所有的元素重复以上的步骤，除了最后一个；
     * 4. 重复步骤1~3，直到排序完成。
     *
     * @param arr
     * @return
     */
    private static int[] bubbleSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) { // 相邻元素比较
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }


    /**
     * 冒泡排序
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSortTwo(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
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
    private static int[] insertSort(int[] arr) {

        // 认为第一个元素已经是排好序的，不再重新排序
        for (int i = 1; i < arr.length; i++) {
            // 遍历之前的所有元素，并对其进行插入
            int j = i;
            int current = arr[i];
            while (j - 1 >= 0 && arr[j - 1] > current) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = current;
        }

        return arr;
    }


    /**
     * 插入排序：复杂算法
     *
     * @param arr
     * @return
     */
    private static int[] insertSortTwo(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j - 1 >= 0 && arr[j] < arr[j - 1]; j--) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }

        }

        return arr;
    }


    /**
     * 快速排序
     *
     * @param arr
     * @return
     */
    private static int[] quickSort(int[] arr) {




        return arr;
    }

}
