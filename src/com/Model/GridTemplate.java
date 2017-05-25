package com.Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 3x3 tile grid used for procedural board generation.
 * @author Thomas Daniell
 */
public class GridTemplate {
	private static final int NCOLS  = 3;
	private static final int NROWS  = 3;
	private static final int SIZE   = NCOLS*NROWS;
	
	private final List<Tile> tiles;
	
	public GridTemplate(Iterable<Tile> it) {
		tiles = new ArrayList<>(SIZE);
		
		
		int count = 0;
		while (it.hasNext() && (count < SIZE)) {
			tiles.add(it.next());
			count++;
		}
	}
	
	private GridTemplate(List<Tile> tiles) {
		this.tiles = new ArrayList<>(tiles);
	}
	
	public int rotate(int times) {
		
	}
	
	public static int getWidth() {
		return NCOLS;
	}
	
	public static int getHeight() {
		return NROWS;
	}
}
