package com;

public interface Heuristic<T> {
	public int hcost(State<T> state);
}
