package com.Model;

import java.util.Random;

public class Template {

	private int array[][];
	
	public Template() {
		this.array = new int[5][5];
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
		Template temp = new Template();
		Random r = new Random();
		int i = r.nextInt(3);
		while (i > 0) {
			if (i == 1) temp.array = temp.rotate();
			else if (i == 2) temp.array = temp.reflect();
		}
		return temp;
	}
	
}
