package com.fenfei.pets;

import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DemoTest {


    @Test
    public void testArray() {
        Integer[] labels = {1,5};
        List<Integer> localLabel = Arrays.asList(labels);

        Integer[] memberLabels = {21, 2, 22, 10, 11,1};
        List<Integer> targets = Arrays.asList(memberLabels);

        for (Integer label : targets) {
            if (localLabel.contains(label)) {
                System.out.println("满足条件的是 : " + label);
            }
        }
    }


    @Test
    public void testDate() {
        for (int i=0 ;i < 10 ; i++) {
            long l = new Date().getTime() / 1000;
            String timestamp = String.valueOf(new Date().getTime() / 1000);
            System.out.println(l + 1 + "....." + timestamp);
        }
    }
}
