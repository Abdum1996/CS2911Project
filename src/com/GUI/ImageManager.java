package com.GUI;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.io.File;

import javax.imageio.ImageIO;

import com.Direction;
import com.EntityTypes;
import com.Tile;


/**
*   ImageManager manages the image loading for all the graphics
 */
public class ImageManager {
    /**
    * hash map of all images for tiles
     */
    private HashMap<Tile, BufferedImage> tileImgs;
    /**
    * hash map of all images for boxes
     */
    private HashMap<Integer, BufferedImage> boxImgs;
    
    /**
     * hash map of all images for the player 
     */
    private HashMap<Direction, BufferedImage> playerImgs;

    private final int imgWidth = 16;
    private final int imgHeight = 16;

    /**
    * folder directory of tile, entity and default image
    * @param tileImageSrc location of tile images
    * @param entityImageSrc location of entity images
    * @param defaultImageSrc location of the default image
     */
    public ImageManager () {
        this.tileImgs = new HashMap<>();
        this.boxImgs = new HashMap<>();
        this.playerImgs = new HashMap<>();
    }

    public void loadTileImg (Tile type, String path) {
        BufferedImage tileImage;
        try {
            tileImage = ImageIO.read(new File(path));
            //System.out.println("loaded tile image");

            tileImgs.put(type, tileImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadBoxImg (int boxId, String path) {
        BufferedImage boxImage;
        try {
            boxImage = ImageIO.read(new File(path));
            boxImgs.put(boxId, boxImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @param type The entity type of the image
     * @param dir The direction the entity is facing
     * @param path the path to the image file
     */
    public void loadPlayerImg (Direction dir, String path) {
        BufferedImage pImage;
        try {
            pImage = ImageIO.read(new File(path));
            playerImgs.put(dir, pImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getTileImg (Tile type) {
        return this.tileImgs.get(type);
    }
    public BufferedImage getBoxImg (int id) {
        return this.boxImgs.get(id);
    }

    public BufferedImage getPlayerImg (Direction dir) {
        return this.playerImgs.get(dir);
    }
    
    public int getImgHeight () {
        return this.imgHeight;
    }
    public int getImgWidth() {
        return this.imgWidth;
    }
}
