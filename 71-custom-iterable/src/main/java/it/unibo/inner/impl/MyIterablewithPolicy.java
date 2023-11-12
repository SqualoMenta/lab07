package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class MyIterablewithPolicy<T> implements IterableWithPolicy<T> {

    private List<T> elements;
    private Predicate<T> predicate;

    public MyIterablewithPolicy(T[] elements, Predicate<T> predicate) {
        this.elements = new ArrayList<T>(Arrays.asList(elements));
        this.predicate = predicate;
    }

    public MyIterablewithPolicy(T[] elements) {
        this(elements, new Predicate<T>() {

            @Override
            public boolean test(T elem) {
                return true;
            }

        });
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.predicate = filter;
    }

    private class MyIterator implements Iterator<T> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            while (index < elements.size()) {
                if (predicate.test(elements.get(index))) {
                    return true;
                } else
                    index++;
            }
            return false;
        }

        @Override
        public T next() {
            if (hasNext()) {
                return elements.get(index++);
            } else {
                return null;
            }
        }

    }
}
