package com.Model;

import java.util.LinkedList;

/**
 * A queue that keeps only the last n elements as specified in the constructor
 * @author Abdulrahman Alhomayany
 *
 * @param <T> - The type of object this queue holds
 */
@SuppressWarnings("serial")
public class LimitedQueue<T> extends LinkedList<T> {
	private int limit;
	
	/**
	 * Constructs the queue given an integer limit
	 * @param limit
	 */
	public LimitedQueue(int limit) {
		super();
		this.limit = limit;
	}
	
	@Override
	public boolean add(T t) {
		while (limit >= size())
			poll();
		return super.add(t);
	}
}
