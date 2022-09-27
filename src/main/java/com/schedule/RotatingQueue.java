package com.schedule;

import java.util.ArrayDeque;

public class RotatingQueue<E> extends ArrayDeque<E> {
    @Override
    public E poll() {
        E item =  super.poll();
        add(item);
        return item;
    }
}
