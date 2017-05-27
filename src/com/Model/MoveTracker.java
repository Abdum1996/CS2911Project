package com.Model;

import java.util.LinkedList;
import java.util.Deque;

public class MoveTracker {
	private final Deque<Move> moves;
	
	private final int maxPushes;
	private final int undoDepth;
	private final int maxUndos;
	
	private int undoCount = 0;
	private int pushCount = 0;
	
	public MoveTracker(Difficulty difficulty, int minPushes) {
		moves = new LinkedList<>();
		
		maxPushes = difficulty.equals(Difficulty.HARD) ? minPushes*2 : Integer.MAX_VALUE;
		undoDepth = difficulty.getUndoDepth();
		maxUndos = difficulty.getMaxUndos();
	}
	
	public void reset() {
		moves.clear();
		undoCount = 0;
		pushCount = 0;
	}
	
	public void addMove(Move move) {
		if (move.isPush()) pushCount++;
		moves.push(move);
		
		if (moves.size() > undoDepth)
			moves.removeLast();
	}
	
	public Move undoLastMove() {
		if (moves.isEmpty() || (undoCount >= maxUndos)) return null;
		undoCount++;
		
		return moves.pop();
	}
	
	public boolean reachedMaxPushes() {
		return pushCount >= maxPushes;
	}
	
	public int getPushCount() {
		return pushCount;
	}
	
	public int getUndoCount() {
		return undoCount;
	}
}
