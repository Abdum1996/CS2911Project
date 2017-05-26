package com.Model;

public enum Difficulty {
	EASY(5, Integer.MAX_VALUE), NORMAL(3, 3), HARD(0, 0);
	
	private final int undoDepth;
	private final int maxUndos;
	
	private Difficulty(int undoDepth, int maxUndos) {
		this.undoDepth = undoDepth;
		this.maxUndos  = maxUndos;
	}
	
	public int getUndoDepth() {
		return undoDepth;
	}
	
	public int getMaxUndos() {
		return maxUndos;
	}
}
