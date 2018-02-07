package com.xiaojianhx.demo.algorithm;

public class Hanoi {

    private int count = 1;

    public void hanoi(char a, char b, char c, int n) {

        if (n < 1) {
            return;
        }

        if (n == 1) {
            move(a, c);
            return;
        }

        hanoi(a, c, b, n - 1);
        move(a, c);
        hanoi(b, a, c, n - 1);
    }

    public void move(char a, char b) {
        System.out.println("第" + (count++) + "步：\t" + a + " --> " + b);
    }

    public static void main(String[] args) {
        new Hanoi().hanoi('A', 'B', 'C', 30);
    }
}
