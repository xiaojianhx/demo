package com.xiaojianhx.demo.restructure;

public class RegularPrice extends Price {

    public int getPriceCode() {
        return Movie.REGULAR;
    }

    protected double getCharge(int daysRented) {

        double result = 2;

        if (daysRented > 2) {
            result += (daysRented - 2) * 1.5;
        }

        return result;
    }
}