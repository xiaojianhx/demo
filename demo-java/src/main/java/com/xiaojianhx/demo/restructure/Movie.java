package com.xiaojianhx.demo.restructure;

public class Movie {

    public static final int CHILDREN = 2;
    public static final int REGULAR = 0;
    public static final int NEW_REGULAR = 1;

    private String _title;
    private Price price;

    public Movie(String _title, int priceCode) {
        this._title = _title;
        setPriceCode(priceCode);
    }

    public int getPriceCode() {
        return price.getPriceCode();
    }

    public void setPriceCode(int priceCode) {
        switch (priceCode) {
        case REGULAR:
            price = new RegularPrice();
            break;
        case CHILDREN:
            price = new ChildrenPrice();
            break;
        case NEW_REGULAR:
            price = new NewRegularPrice();
            break;

        default:
            throw new RuntimeException("Incorrect Price Code");
        }
    }

    public String get_title() {
        return _title;
    }

    public double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        return price.getFrequentRenterPoints(daysRented);
    }
}