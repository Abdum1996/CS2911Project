package com.Model;

import java.util.Random;

public class Template {
	
	/**
	 * WALL = W
	 * FLOOR = F
	 * EMPTY = E
	 */

	private char array[][];
	private final Random rand;
	
	public Template(char[][] array) {
		this.array = array;
		this.rand = new Random();
		
	}
	
	public Template(Template tem) {
		this.array = tem.array;
		this.rand = tem.rand;
	}
	
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
	public Template modifyTemplate() {
		Template temp = new Template(this);
		int i = temp.rand.nextInt(3);
		while (i > 0) {
			if (i == 1) temp.array = temp.rotate();
			else if (i == 2) temp.array = temp.reflect();
		}
		return temp;
	}
	
}
