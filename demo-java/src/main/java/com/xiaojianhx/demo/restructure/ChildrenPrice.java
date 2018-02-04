package com.xiaojianhx.demo.restructure;

public class ChildrenPrice extends Price {

    public int getPriceCode() {
        return Movie.CHILDREN;
    }

    protected double getCharge(int daysRented) {

        double result = 1.5;

        if (daysRented > 3) {
            result += (daysRented - 3) * 1.5;
        }

        return result;
    }
}