package com.Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A class that manages save games
 * @author Abdulrahman Alhomayany
 *
 */
public class SaveGameManager {
	/**
	 * the directory in which all the save files will be stored
	 */
	private final String savefilesPath = "./saves/";
	
	/**
	 * the extension we will use to store save files
	 */
	private final String savefileExtension = ".savegame";
	
	public SaveGameManager() {
		
	}
	
	/**
	 * creates a save game file of the game given a player and the last level unlocked
	 * @param playerName - the name of the save game
	 * @param level - the last level this player has unlocked
	 */
	public void saveGame(String playerName, int level) {
		try{
		    PrintWriter writer = new PrintWriter(savefilesPath + playerName + savefileExtension, "UTF-8");
		    writer.println(level);
		    writer.close();
		} catch (IOException e) {
		   // do something
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the number of the last level unlocked by specified player
	 * @param playerName
	 * @return the last level unlocked, if no such save exists then return -1
	 */
	public int getSaveGame(String playerName) {
		try{
		    Scanner reader = new Scanner(savefilesPath + playerName + savefileExtension);
		    int lvl = reader.nextInt();
		    reader.close();
		    return lvl;
		} catch (Exception e) {
		   return -1;
		}
	}
	
}