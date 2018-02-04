package com.xiaojianhx.demo.restructure;

import java.util.Enumeration;
import java.util.Vector;

public class Costomer {

    private String _name;
    private Vector _rentals = new Vector<>();

    public Costomer(String _name) {
        this._name = _name;
    }

    public void addRental(Rental arg) {
        _rentals.add(arg);
    }

    public String get_name() {
        return _name;
    }

    public String statement() {

        Enumeration rentals = _rentals.elements();

        String result = "Rental Record for " + get_name() + "\r\n";

        while (rentals.hasMoreElements()) {

            Rental each = (Rental) rentals.nextElement();

            result += "\t" + each.get_movie().get_title() + "\t" + String.valueOf(each.getCharge()) + "\r\n";
        }

        result += "Amount owed is " + getTotalCharge() + "\r\n";
        result += "You earned " + getTotalFrequentRenterPoints() + " frequent renter points ";

        return result;
    }

    private double getTotalCharge() {

        double result = 0;

        Enumeration rentals = _rentals.elements();

        while (rentals.hasMoreElements()) {

            Rental each = (Rental) rentals.nextElement();
            result += each.getCharge();

        }
        return result;
    }

    private int getTotalFrequentRenterPoints() {

        int frequentRenterPoints = 0;

        Enumeration rentals = _rentals.elements();

        while (rentals.hasMoreElements()) {

            Rental each = (Rental) rentals.nextElement();
            frequentRenterPoints += each.getFrequentRenterPoints();
        }

        return frequentRenterPoints;
    }
}