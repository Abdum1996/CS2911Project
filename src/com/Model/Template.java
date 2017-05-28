package com.Model;

import java.util.Random;

/**
 * Template class for random empty board generation
 * @author Samir Mustavi
 */
public class Template {
	
	/*
	 * WALL = W
	 * FLOOR = F
	 * EMPTY = E
	 */

	/**
	 * Stores the char matrix containing the template
	 */
	private char array[][];
	
	/**
	 * Constructor for an instance of the Template class
	 * @param array - the matrix containing template information
	 */
	public Template(char[][] array) {
		this.array = array;
	}
	
	/**
	 * Gets the 2d char array for the given template
	 * @return the char matrix array
	 */
	public char[][] getTemplateMap() {
		return array;
	}

	/**
	 * Rotates the template clock-wise by 90 degrees
	 * @return the rotated template array
	 */
	private char[][] rotate() {
		char newArray[][] = new char[5][5];
		int i,j = 0;
		for (i = 0; i < 5; i++) {
			for (j = 0; j < 5; j++) {
				newArray[i][j] = this.array[4-j][i];
			}
		}
		return newArray;
	}
	
	/**
	 * Reflects the template along the main diagonal
	 * @return the transpose of the given template array
	 */
	private char[][] reflect() {
		char newArray[][] = new char[5][5];
		int i,j = 0;
		for (i = 0; i < 5; i++) {
			for (j = 0; j < 5; j++) {
				newArray[i][j] = this.array[j][i];
			}
		}
		return newArray;
	}
	
	/**
	 * Modifies the template array by randomly rotating and reflecting
	 * @param r - the Random type that calls randomly on reflect() and rotate() to modify the template array
	 * @return the modified template array
	 */
	public Template modifyTemplate(Random r) {
		for (int i = r.nextInt(3); i > 0; i--) {
			if (i == 1) this.array = this.rotate();
			else if (i == 2) this.array = this.reflect();
		}
		
		return this;
	}
}
