package com.jun.chu.java.util;

import java.util.*;
import java.util.function.UnaryOperator;

/**
 * @author chujun
 * @date 2020-06-07
 * @see java.util.List
 */
public interface MyList<E> extends MyCollection<E> {

    /**
     * jfd
     *
     * @return int
     */
    @Override
    int size();

    /**
     * jfd
     *
     * @return boolean
     */
    @Override
    boolean isEmpty();

    /**
     * jfd
     *
     * @param o o
     * @return boolean
     */
    @Override
    boolean contains(Object o);

    /**
     * jfd
     *
     * @return MyIterator
     */
    @Override
    MyIterator<E> iterator();

    /**
     * jfd
     *
     * @return Object[]
     */
    @Override
    Object[] toArray();

    /**
     * jfd
     *
     * @param a   a
     * @param <T> T
     * @return T[]
     */
    @Override
    <T> T[] toArray(T[] a);

    /**
     * jfd
     *
     * @param e e
     * @return boolean
     */
    @Override
    boolean add(E e);

    /**
     * jfd
     *
     * @param o o
     * @return boolean
     */
    @Override
    boolean remove(Object o);

    /**
     * jfd
     *
     * @param c c
     * @return boolean
     */
    @Override
    boolean containsAll(Collection<?> c);

    /**
     * jfd
     *
     * @param c c
     * @return boolean
     */
    @Override
    boolean addAll(Collection<? extends E> c);

    /**
     * jfd
     *
     * @param c c
     * @return boolean
     */
    @Override
    boolean removeAll(Collection<?> c);

    /**
     * jdf
     *
     * @param c c
     * @return boolean
     */
    @Override
    boolean retainAll(Collection<?> c);

    /**
     * tbs read ListIterator#set方法
     *
     * @param operator operator
     */
    default void replaceAll(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        final ListIterator<E> li = this.listIterator();
        while (li.hasNext()) {
            li.set(operator.apply(li.next()));
        }
    }

    /**
     * tbs
     *
     * @param c c
     */
    default void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        MyArrays.sort(a, (Comparator) c);
        ListIterator<E> i = this.listIterator();
        for (Object e : a) {
            i.next();
            i.set((E) e);
        }
    }

    /**
     * jfd
     */
    @Override
    void clear();

    /**
     * jfd
     *
     * @param o o
     * @return
     */
    @Override
    boolean equals(Object o);

    /**
     * jfd
     *
     * @return int
     */
    @Override
    int hashCode();

    E get(int index);

    E set(int index, E element);

    void add(int index, E element);

    E remove(int index);

    int indexOf(Object o);

    int lastIndexOf(Object o);

    /**
     * tbs
     *
     * @return ListIterator
     */
    ListIterator<E> listIterator();

    /**
     * tbs
     *
     * @param index index
     * @return ListIterator
     */
    ListIterator<E> listIterator(int index);

    List<E> subList(int fromIndex, int toIndex);

    @Override
    default Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, Spliterator.ORDERED);
    }
}

