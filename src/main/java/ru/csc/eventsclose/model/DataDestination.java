package ru.csc.eventsclose.model;

/**
 * author Vladiev Aleksey (avladiev2@gmail.com)
 */
public interface DataDestination<E> {

    void put(final E result);

}
