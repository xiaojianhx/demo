package com.xiaojianhx.demo.restructure;

import org.junit.Assert;
import org.junit.Test;

import com.xiaojianhx.utils.IOUtils;

public class CostomerTest {

    @Test
    public void test() {

        Costomer c = new Costomer("xiaojianhx");
        c.addRental(new Rental(new Movie("天王之王1", Movie.REGULAR), 5));
        c.addRental(new Rental(new Movie("天王之王2", Movie.REGULAR), 10));
        c.addRental(new Rental(new Movie("天王之王3", Movie.NEW_REGULAR), 10));
        c.addRental(new Rental(new Movie("天王之王4", Movie.NEW_REGULAR), 20));
        c.addRental(new Rental(new Movie("天王之王5", Movie.CHILDREN), 20));
        c.addRental(new Rental(new Movie("天王之王6", Movie.CHILDREN), 1));

        String result = IOUtils.readAsString("src/test/resources/result.txt");

        System.out.println(c.statement());
        Assert.assertEquals(result, c.statement());
    }
}