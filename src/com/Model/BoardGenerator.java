package com.Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Factory class responsible for procedurally generating a game board.
 */
public class BoardGenerator {
	private static final int MAX_GOALS = 10;
	
	public GameBoard genBoard(int width, int height) {
		/*GameBoard.Builder builder = new SokobanBoard.Builder(width, height);
		Random generator = new Random();
		int boardSize = width*height;
		
		int numGoals  = generator.nextInt(MAX_GOALS + 1);
		int numWalls  = generator.nextInt(boardSize - MAX_GOALS + 1);
		int numFloors = boardSize - numGoals - numWalls;
		
		//for (int i = 0)
		
		
		//ArrayList<Tile> tiles = new ArrayList<>()
		
		for (int i = 0; i < MAX_BOXES; ++i) {
			int offset = generator.nextInt(MAX_BOXES + 1);
			Point pos = Point.at(offset % width, offset/width);
			builder.addBox(pos);
		}
		
		
		
		
		//Collections.s*/
		
		
		
		
		
		/*  
		Scanner sc = null;
	    try
	    {

	        sc = new Scanner(new FileReader());
		    String curr = null;
		    String[] c = null;
		    int array[5][5];
		    int i,j = 0;
		    
		    while ((sc.hasNextLine()) || (j < 5)) {
		    
		    	curr = sc.nextLine();
		    	c = curr.split(" ");
		    	
		    	for (i = 0; i < 5; i++) {
		    		array[j][i] = Integer.parseInt(c[i]);
		    	}
		    	j++;
		    }
		    
		    Template temp = new Template(array);
		    temp.modifyTemplate();
	    }
	    catch (FileNotFoundException e) {}

	    finally
	    {
	        if (sc != null) sc.close();

	    } 
		 */
		
		return null;
	}
}
