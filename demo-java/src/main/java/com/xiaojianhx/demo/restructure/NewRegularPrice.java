package com.xiaojianhx.demo.restructure;

public class NewRegularPrice extends Price {

    public int getPriceCode() {
        return Movie.NEW_REGULAR;
    }

    protected double getCharge(int daysRented) {
        return daysRented * 3;
    }

    protected int getFrequentRenterPoints(int daysRented) {
        return 2;
    }
}