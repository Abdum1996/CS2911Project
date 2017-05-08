package com;

/**
 * Generic weighted edge class.
 * @invariant (source != null) and (target != null)
 * @param <T>
 */
public class Edge<T> implements Comparable<Edge<T>> {
	private final T   source;
	private final T   target;
	private final int weight;

	/**
	 * Produces a new edge.
	 * @param  source - source location
	 * @param  target - target location
	 * @param  weight - associated edge weight
	 * @return edge between source and target
	 */
	public static <T> Edge<T> of(T source, T target, int weight) {
		return new Edge<>(source, target, weight);
	}
	
	/**
	 * Construct a new edge.
	 * @param source - source location
	 * @param target - target location
	 * @param weight - edge weight
	 */
	public Edge(T source, T target, int weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
	}
	
	/**
	 * Get start of the edge.
	 * @return start of edge
	 */
	public T getSource() {
		return source;
	}
	
	/**
	 * Get end of edge.
	 * @return end of edge
	 */
	public T getTarget() {
		return target;
	}

	/**
	 * Get weight of edge.
	 * @return edge weight
	 */
	public int getWeight() {
		return weight;
	}
	
	@Override
	public int compareTo(Edge<T> other) {
		return Integer.compare(weight, other.weight);
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		
		result = prime*result + source.hashCode();
		result = prime*result + target.hashCode();
		result = prime*result + weight;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Edge<?> other = (Edge<?>)obj;
		return source.equals(other.source) &&
				target.equals(other.target) &&
				(weight == other.weight);
	}
}
