package com.Model;

public enum Difficulty {
	EASY(5), NORMAL(0), HARD(0);
	
	private final int maxUndos;
	
	private Difficulty(int maxUndos) {
		this.maxUndos = maxUndos;
	}
	
	public int getMaxUndos() {
		return maxUndos;
	}
}
