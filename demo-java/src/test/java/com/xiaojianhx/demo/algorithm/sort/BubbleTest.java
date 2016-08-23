package com.xiaojianhx.demo.algorithm.sort;

import org.junit.Assert;
import org.junit.Test;

public class BubbleTest {

    @Test
    public void testBubble() {

        int[] expecteds = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int[] arr = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };

        new Bubble().sort(arr);

        Assert.assertArrayEquals(expecteds, arr);
    }
}
