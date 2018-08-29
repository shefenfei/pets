package com.fenfei.pets;

import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingQueue;

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



    @Test
    public void testDrools() {
        ConcurrentLinkedDeque<String> linkedDeque = new ConcurrentLinkedDeque<>();
        linkedDeque.add("a");
        linkedDeque.offer("b");
        linkedDeque.offer("c");
        linkedDeque.offer("d");
        linkedDeque.offer("e");

        System.out.println(linkedDeque.size());
        linkedDeque.poll();
        System.out.println(linkedDeque.size());
        linkedDeque.peek();
        System.out.println(linkedDeque.size());

        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(1000);
    }
}
