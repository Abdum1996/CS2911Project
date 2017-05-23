package com.Model;

import java.util.Stack;

/**
 * A limited sized stack that holds the n most recently pushed items as specified by the user
 * @author Abdulrahman Alhomayany
 *
 * @param <T> - The type of items this stack holds
 */
@SuppressWarnings("serial")
public class SizedStack<T> extends Stack<T> {
	/**
	 * The maximum number of items this stack holds
	 */
    private int limit;

    public SizedStack(int size) {
        super();
        this.limit = size;
    }

    @Override
    public T push(T object) {
        while (this.size() >= limit) {
            this.remove(0);
        }
        return super.push(object);
    }
}
