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
	
	public boolean reachedMaxUndos(int undoCount) {
		return undoCount >= maxUndos;
	}
	
	public boolean reachedMaxMoves(int minMoves, int numMoves) {
		switch (this) {
			case EASY:
			case NORMAL:
				return false;
			case HARD:
				return numMoves >= minMoves*2;
			default:
				return false;
		}
	}
}
