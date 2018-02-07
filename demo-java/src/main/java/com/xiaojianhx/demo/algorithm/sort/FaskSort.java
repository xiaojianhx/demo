package com.xiaojianhx.demo.algorithm.sort;

public class FaskSort {

    private int count = 0;

    public int middle(int[] arr, int low, int hight) {

        int base_idx = low;

        while (low < hight) {

            while (low < hight && arr[hight] > arr[base_idx]) {
                hight--;
            }

            swap(arr, base_idx, hight);
            base_idx = hight;

            show(arr);

            while (low < hight && arr[low] < arr[base_idx]) {
                low++;
            }

            swap(arr, base_idx, low);
            base_idx = low;
            show(arr);
        }

        arr[low] = arr[base_idx];

        show(arr);
        return low;
    }

    public void sort(int[] arr, int low, int hight) {

        if (low < hight) {
            int middle = middle(arr, low, hight);
            sort(arr, low, middle - 1);
            sort(arr, middle + 1, hight);
        }
    }

    private void swap(int[] arr, int i, int j) {

        if (i == j) {
            return;
        }
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;

        count++;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {

        int[] arr = { 6, 1, 2, 7, 9, 3, 4, 5, 10, 8 };
        show(arr);

        FaskSort fs = new FaskSort();
        fs.sort(arr, 0, arr.length - 1);
        show(arr);
        System.out.println("交换了：" + fs.getCount());
    }

    private static void show(int[] arr) {
        for (int i : arr) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }
}
