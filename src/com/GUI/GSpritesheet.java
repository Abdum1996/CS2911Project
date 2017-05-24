package com.GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.Model.Direction;

import com.Model.GameConstants;
import com.Model.Point;

/**
 * A class to manage/represent a player sprite sheet
 */
public class GSpritesheet {
	/**
	 * The entire sprite sheet image
	 */
	private BufferedImage spritesheet;
	
	/**
	 * The number of different states this player can be showed in
	 */
	private final int numStates = 3;
	
	/**
	 * keeps the coordinates (pixel) where the sprite sequence starts
	 */
	private HashMap<Direction, Point> positions = new HashMap<>();
	
	/**
	 * The path for the player spritesheet
	 */
	private final String sheetPath = "./resources/32px/sheet.png";
	
	/**
	 * keeps track of directions accessed
	 */
	private Direction currDirection = Direction.DOWN;
	
	/**
	 * keeps track of motion, always less than numStates
	 */
	private int currState;
	
	public GSpritesheet() {
		
		positions.put(Direction.UP, Point.at(3*GameConstants.IMAGE_DIMENSION, 5*GameConstants.IMAGE_DIMENSION));
		positions.put(Direction.RIGHT, Point.at(0, 7*GameConstants.IMAGE_DIMENSION));
		positions.put(Direction.LEFT, Point.at(3*GameConstants.IMAGE_DIMENSION, 7*GameConstants.IMAGE_DIMENSION));
		positions.put(Direction.DOWN, Point.at(0, 5*GameConstants.IMAGE_DIMENSION));
		
		try {
			spritesheet = ImageIO.read(new File(sheetPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getNextSprite(Direction dir) {
		
		if (currDirection != dir) {
			currDirection = dir;
			currState = 0;
		}
		
		int x = positions.get(dir).getX();
		int y = positions.get(dir).getY();
		int xshift = (currState)*GameConstants.IMAGE_DIMENSION;
		
		currState = (currState + 1) % numStates;
		
		return spritesheet.getSubimage(x + xshift, y, GameConstants.IMAGE_DIMENSION, GameConstants.IMAGE_DIMENSION);
	}
}
