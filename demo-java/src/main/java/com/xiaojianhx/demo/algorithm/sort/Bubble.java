package com.xiaojianhx.demo.algorithm.sort;

public class Bubble {

    public void sort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
                System.out.print("====:");
                show(arr);
            }
            System.out.println();
        }
    }

    private void show(int[] arr) {
        for (int i : arr) {
            System.out.print(i + ", ");
        }
    }

    public static void main(String[] args) {

        int[] arr = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };

        Bubble bubble = new Bubble();
        bubble.sort(arr);

        bubble.show(arr);
    }
}