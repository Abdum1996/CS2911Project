package com.Model;

import java.util.Random;

public class Template {
	
	/**
	 * WALL = 0
	 * FLOOR = 1
	 * EMPTY = 2
	 */

	private int array[][];
	private final Random rand;
	
	public Template(int[][] array) {
		this.array = array;
		this.rand = new Random();
		
	}
	
	public Template(Template tem) {
		this.array = tem.array;
		this.rand = tem.rand;
	}
	
	private int[][] rotate() {
		int newArray[][] = new int[5][5];
		int i,j = 0;
		for (i = 0; i < 5; i++) {
			for (j = 0; j < 5; j++) {
				newArray[i][j] = this.array[4-j][i];
			}
		}
		return newArray;
	}
	
	private int[][] reflect() {
		int newArray[][] = new int[5][5];
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
