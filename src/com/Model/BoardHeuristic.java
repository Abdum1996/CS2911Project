package com.Model;

import com.Graph.Heuristic;
import com.Graph.State;

public class BoardHeuristic implements Heuristic<Action> {
	@Override
	public int hcost(State<Action> state) {
		return 0;
	}
}
