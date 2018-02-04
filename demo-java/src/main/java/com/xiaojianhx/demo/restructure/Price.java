package com.xiaojianhx.demo.restructure;

public abstract class Price {

    protected abstract int getPriceCode();

    protected abstract double getCharge(int daysRented);

    protected int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
