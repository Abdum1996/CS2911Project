package com.Graph;

import java.util.Set;

/**
 * Interface for weighted simple graph.
 * @param <T> - node type
 */
public interface WeightedGraph<T> {
	public void addEdge(T source, T target, int weight);
	public void addEdge(Edge<T> edge);
	public void addNode(T node);
	
	public boolean hasEdge(T source, T target);
	public boolean hasEdge(Edge<T> edge);
	public boolean hasNode(T node);
	
	public int getEdgeWeight(T source, T target);
	public Set<Edge<T>> edgesOf(T source);
	public Set<Edge<T>> getEdgeSet();

	public Set<T> getNeighbours(T source);
	public Set<T> getNodeSet();	
}

// i will implement this tomorrow - Thomas
