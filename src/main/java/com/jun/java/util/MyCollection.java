package com.jun.java.util;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author chujun
 * @date 2020-06-03
 * @see java.util.Collection
 */
public interface MyCollection<E> extends MyIterable<E>, Collection<E> {
    /**
     * jfm
     *
     * @return the number of elements in this collection
     */
    @Override
    int size();

    /**
     * Returns <tt>true</tt> if this collection contains no elements.
     *
     * @return <tt>true</tt> if this collection contains no elements
     */
    @Override
    boolean isEmpty();


    @Override
    boolean contains(Object o);

    @Override
    MyIterator<E> iterator();

    @Override
    Object[] toArray();

    @Override
    <T> T[] toArray(T[] a);

    @Override
    boolean add(E e);

    @Override
    boolean remove(Object o);

    @Override
    boolean containsAll(Collection<?> c);

    @Override
    boolean addAll(Collection<? extends E> c);

    @Override
    boolean removeAll(Collection<?> c);

    @Override
    default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<E> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }

    @Override
    boolean retainAll(Collection<?> c);

    @Override
    void clear();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    @Override
    default Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, 0);
    }

    @Override
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    default Stream<E> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }
}
