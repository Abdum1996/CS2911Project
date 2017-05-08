package com;

public interface State<T> {
	public int getStepCost(State<T> prev);
	
	public State<T> applyAction(T action);
	public boolean isGoalState();
}
