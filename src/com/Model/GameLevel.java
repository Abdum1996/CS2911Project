package com.Model;

import java.util.Stack;

public class GameLevel {
	private final Difficulty difficulty;
	private final SokobanBoard board;
	private final int minMoves;
	
	private final Stack<ActionResult> pastActionResults;
	private final Stack<Action> pastActions;
	
	private int undoCount = 0;
	private int moveCount = 0;
	private int score = 0;
	
	public GameLevel(Difficulty difficulty, String map) {
		board = (map == null) ? BoardGenerator.genBoard(difficulty) :
								BoardGenerator.readMap(map);
		
		minMoves = board.getMinMoves();
		this.difficulty = difficulty;
		
		pastActionResults = new Stack<>();
		pastActions = new Stack<>();
	}	

	public void reset() {
		board.reset();
		
		pastActionResults.clear();
		pastActions.clear();
		
		undoCount = 0;
		moveCount = 0;
		score = 0;
	}
	
	public ActionResult getActionResult(Action action) {
		return board.getActionResult(action);
	}
	
	public void applyAction(Action action) {		
		board.applyAction(action);
	}
	
	public void undoLastMove() {
		
		
	}
	

	public Iterable<Tile> getTiles() {
		return board.getTiles();
	}
	
	public Iterable<Box> getBoxes() {
		return board.getBoxes();
	}
	
	public int getMinimumMoves() {
		return minMoves;
	}
	
	public int getScore() {
		return score;
	}
	
	public Player getPlayer() {
		return board.getPlayer();
	}
	
	public int getMapWidth() {
		return board.getMapHeight();
	}

	public int getMapHeight() {
		return board.getMapHeight();
	}
	
	public boolean hasWon() {
		return board.gameWon();
	}
	
	public boolean hasLost() {
		return false;
	}
	
	public void getHint() {
		
	}
}
