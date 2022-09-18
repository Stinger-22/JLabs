package com.labs.three.util;

import java.util.Iterator;
import java.util.List;

public class ListCircularIterator<T> implements Iterator<T> {
    private final List<T> list;
    private int size;
    private int i;

    public ListCircularIterator(List<T> list) {
        this.list = list;
        this.size = list.size();
    }

    @Override
    public boolean hasNext() {
        return this.size != 0;
    }

    @Override
    public T next() {
        int t = i;
        i++;
        if (i >= size) {
            i = 0;
        }
        return list.get(t);
    }

    @Override
    public void remove() {
        list.remove(i);
        this.size--;
        if (i > size) {
            i = 0;
        }
    }

    public int getI() {
        return i;
    }
}