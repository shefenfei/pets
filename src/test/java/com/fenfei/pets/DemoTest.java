package com.fenfei.pets;

import org.junit.Test;

import java.util.Arrays;
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
}
