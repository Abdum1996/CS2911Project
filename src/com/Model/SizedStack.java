package com.Model;

import java.util.LinkedList;
import java.util.Deque;

/**
 * Representation of a stack with a given size limit. If the limit
 * is exceeded then the bottom most item is removed from the stack.
 * @param <T> - type of item stored in the stack
 */
public class SizedStack<T>  {
	private final Deque<T> deque;
	private final int limit;
	
	/**
	 * Construct a new sized stack with a given limit.
	 * @param limit - maximum number of items allowed in the stack
	 * @throws IllegalArgumentException if the limit < 0
	 */
	public SizedStack(int limit) {
		if (limit < 0) throw new IllegalArgumentException("Stack limit must be >= 0");
		
		deque = new LinkedList<>();
		this.limit = limit;
	}
	
	/**
	 * Push an item onto the sized stack.
	 * @param item - item to be pushed
	 */
	public void push(T item) {
		deque.push(item);
		
		if (deque.size() > limit)
			deque.removeLast();
		return;
	}
	
	/**
	 * Pop an item from the sized stack stack.
	 * @return item removed from the stack
	 */
	public T pop() {
		return deque.pop();
	}
	
	/**
	 * Clear the sized stack of its contents.
	 */
	public void clear() {
		deque.clear();
	}
	
	/**
	 * Get the size of the stack.
	 * @return number of items in the stack
	 */
	public int size() {
		return deque.size();
	}
	
	/**
	 * Determine if the sized stack is empty.
	 * @return true if it is empty
	 */
	public boolean isEmpty() {
		return deque.isEmpty();
	}
}
