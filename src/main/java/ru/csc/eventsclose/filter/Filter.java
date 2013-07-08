package ru.csc.eventsclose.filter;

/**
 * author Vladiev Aleksey (avladiev2@gmail.com)
 */
public interface Filter<E> {
    boolean filter(final E element);
}
