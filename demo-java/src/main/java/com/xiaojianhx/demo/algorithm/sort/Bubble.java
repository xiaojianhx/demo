package com.xiaojianhx.demo.algorithm.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 冒泡排序
 * 
 * @author 高万超
 * @version V1.0.0 $ 2016年8月23日 上午10:04:52
 */
public class Bubble {

    private Logger log = LoggerFactory.getLogger(Bubble.class);

    private int loop_count;
    private int change_count;

    public void sort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j < arr.length - 1; j++) {

                loop_count++;

                if (arr[j] > arr[j + 1]) {

                    change_count++;

                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
                log.debug("    :" + toString(arr));
            }
            log.debug("====:" + toString(arr));
        }

        log.debug("循环次数:" + loop_count);
        log.debug("交换次数:" + change_count);
    }

    private String toString(int[] arr) {

        StringBuffer buf = new StringBuffer();

        buf.append("[");

        for (int i = 0; i < arr.length; i++) {

            buf.append(arr[i]);

            if (i < arr.length - 1) {
                buf.append(", ");
            }
        }
        buf.append("]");

        return buf.toString();
    }
}