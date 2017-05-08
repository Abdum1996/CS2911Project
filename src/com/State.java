package com;

import java.util.List;

public interface State<T> {
	public int getStepCost(State<T> prev);
	public State<T> applyAction(T action);
	public List<T> getValidActions();
	public boolean isGoalState();
}
