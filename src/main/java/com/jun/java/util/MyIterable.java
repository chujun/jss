package com.jun.java.util;

import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

/**
 * @param <T> the type of elements returned by the iterator
 * @author chujun
 * @date 2020-06-03
 * @see Iterable
 */
public interface MyIterable<T> extends Iterable<T> {
    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    MyIterator<T> iterator();

    /**
     * jfm
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     * @since 1.8
     */
    @Override
    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        //必须实现Iterable接口的类才能用for-each循环
        for (T t : this) {
            action.accept(t);
        }
    }

    /**
     * @return a {@code Spliterator} over the elements described by this
     * {@code Iterable}.
     * @since 1.8
     */
    @Override
    default Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }
}
